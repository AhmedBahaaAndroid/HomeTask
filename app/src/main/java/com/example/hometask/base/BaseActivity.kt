package com.example.hometask.base

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.autoscout.base.ViewState
import com.example.hometask.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseActivity<out T : BaseViewModel>(clazz: KClass<T>) : AppCompatActivity() {

    private lateinit var animationLoader: LottieAnimationView
    private lateinit var blockingErrorView: FrameLayout
    val model: T by viewModel(clazz)

    lateinit var contentLayout: View

    override fun setContentView(layoutResID: Int) {
        val rootView = inflateView(R.layout.base_activity)
        contentLayout = inflateView(layoutResID)
        contentLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        super.setContentView(rootView)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        findViewById<LinearLayout>(R.id.baseContentGv)
            .addView(contentLayout)
        initAnimationLoader()
        initErrorView()
        listenOnViewState()
    }

    private fun initErrorView() {
        blockingErrorView = findViewById(R.id.progressBarHolder)
    }

    private fun initAnimationLoader() {
        animationLoader = findViewById(R.id.animationLoader)
        animationLoader.repeatCount = ValueAnimator.INFINITE
        animationLoader.setAnimation(LOADER_FILE_NAME)
    }

    private fun listenOnViewState() {
        model.viewState.observe(this, Observer {
            when (it) {
                is ViewState.Loading -> showLoading()
                is ViewState.Success -> hideLoading()
                is ViewState.Error -> {
                    hideLoading()
                    showToastEror(it.message)
                }
            }
        })
    }

    protected fun showLoading() {
        blockingErrorView.visibility = View.VISIBLE
        animationLoader.visibility = View.VISIBLE
        animationLoader.playAnimation()
    }

    protected fun hideLoading() {
        blockingErrorView.visibility = View.GONE
        animationLoader.visibility = View.GONE
        animationLoader.cancelAnimation()
    }

    private fun inflateView(@LayoutRes layoutId: Int) =
        layoutInflater.inflate(layoutId, null, false)

    protected fun showToastEror(message: String?) {
        val toast: Toast = Toast(this)
        toast.setText(message)
        toast.show()
    }

    protected fun showToastMessage(message: String?) {
        val toast: Toast = Toast(this)
        toast.setText(message)
        toast.show()
    }


    companion object {
        const val LOADER_FILE_NAME = "loader.json"
    }
}
