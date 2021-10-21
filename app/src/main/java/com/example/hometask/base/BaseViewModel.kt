package com.example.hometask.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.autoscout.base.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlin.Exception

open class BaseViewModel : ViewModel() {
    val viewState = MutableLiveData<ViewState>()

    private val disposableBag = CompositeDisposable()

    infix fun BaseViewModel.autoDispose(disposable: Disposable) {
        disposableBag.add(disposable)
    }

    protected fun Disposable.addToDisposableBag() {
        autoDispose(this)
    }

     fun <T : Any> Single<T>.subscribeWithViewState(
        successCallback: ((T) -> Unit)? = null,
        failureCallback: ((Exception) -> Boolean)? = null
    ) {
        this
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.value = ViewState.Loading }
            .doOnSuccess { viewState.value = ViewState.Success }
            .doOnError { it.printStackTrace() }
            .subscribeBy(
                onSuccess = { successCallback?.invoke(it) },
                onError = { handleError(it, failureCallback) }
            )
            .addToDisposableBag()
    }

    protected fun Completable.subscribeWithViewState(
        completionCallback: (() -> Unit)? = null,
        failureCallback: ((Exception) -> Boolean)? = null
    ) {
        this
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.value = ViewState.Loading }
            .doOnComplete { viewState.value = ViewState.Success }
            .doOnError { it.printStackTrace() }
            .subscribeBy(
                onComplete = { completionCallback?.invoke() },
                onError = { handleError(it, failureCallback) }
            )
            .addToDisposableBag()
    }

    protected fun <T : Any> Flowable<T>.subscribeWithViewState(
        onNextCallback: ((T) -> Unit)? = null,
        completionCallback: (() -> Unit)? = null,
        failureCallback: ((Exception) -> Boolean)? = null
    ) {
        this
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { it.printStackTrace() }
            .subscribeBy(
                onComplete = { completionCallback?.invoke() },
                onNext = { onNextCallback?.invoke(it) },
                onError = { handleError(it, failureCallback) }
            )
            .addToDisposableBag()
    }



    private fun handleError(
        throwable: Throwable,
        failureCallback: ((Exception) -> Boolean)?
    ) {
        val exception = Exception(throwable)
        val errorHandled = failureCallback?.invoke(exception)
        if (errorHandled != true)
            viewState.value = ViewState.Error(throwable.message)
        else
        // Todo this is a workaround because there is no way else to hide the loading indicator at this stage.
            viewState.value = ViewState.Success
    }

    override fun onCleared() {
        super.onCleared()
        disposableBag.dispose()
    }
}