package com.example.hometask.base

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.autoscout.base.ViewState
import com.example.hometask.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<out T : BaseViewModel>(clazz: KClass<T>) : Fragment() {

    private lateinit var contentView: View
    private lateinit var animationLoader: LottieAnimationView
    private lateinit var blockingErrorView: FrameLayout
    private lateinit var rootView: View
    val model: T by viewModel(clazz)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.base_fragment, container, false)
        initRootView()
        return rootView
    }

    private fun initRootView() {
        contentView = rootView.findViewById(R.id.baseContentGv)
        animationLoader = rootView.findViewById(R.id.animationLoader)
        animationLoader.repeatCount = ValueAnimator.INFINITE
        animationLoader.setAnimation(LOADER_FILE_NAME)

        blockingErrorView = rootView.findViewById(R.id.progressBarHolder)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenOnViewState()
    }

    protected fun attachToRootView(content: View): View {
        (contentView as ViewGroup).addView(content)
        return rootView
    }

    private fun listenOnViewState() {
        model.viewState.observe(viewLifecycleOwner, Observer {
            handleViewState(it ?: return@Observer)
        })
    }

    protected open fun handleViewState(viewState: ViewState) {
        when (viewState) {
            is ViewState.Loading -> showLoading()
            is ViewState.Success -> hideLoading()
            is ViewState.Error -> {
                hideLoading()
                showToastEror(viewState.message)
            }
        }
    }

    protected open fun showLoading() {
        blockingErrorView.visibility = View.VISIBLE
        animationLoader.visibility = View.VISIBLE
        animationLoader.playAnimation()
    }

    protected open fun hideLoading() {
        blockingErrorView.visibility = View.GONE
        animationLoader.visibility = View.GONE
        animationLoader.cancelAnimation()
    }

    protected fun showToastEror(message: String?) {
        hideLoading()
        val toast = Toast(context)
        toast.setText(message)
        toast.show()
    }

    protected fun showToastMessage(message: String?) {
        hideLoading()
        val toast = Toast(context)
        toast.setText(message)
        toast.show()
    }

    companion object {
        const val LOADER_FILE_NAME = "loader.json"
    }
}
