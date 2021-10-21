package com.example.hometask.network
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import io.reactivex.rxjava3.core.*
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class RxJavaErrorAdapterFactory constructor(
    private val rxJava2CallAdapterFactory: RxJava3CallAdapterFactory,
    private val errorMapper: NetworkErrorMapper
) :
    CallAdapter.Factory() {

    companion object {
        fun createWithScheduler(
            scheduler: Scheduler,
            errorMapper: NetworkErrorMapper
        ): RxJavaErrorAdapterFactory {
            return RxJavaErrorAdapterFactory(
                RxJava3CallAdapterFactory.createWithScheduler(scheduler),
                errorMapper
            )
        }
    }

    private inner class RxCallAdapterWrapper constructor(
        private val annotations: Array<Annotation>,
        private val retrofit: Retrofit,
        private val callAdapter: CallAdapter<Any, Any>
    ) : CallAdapter<Any, Any> {
        override fun adapt(call: Call<Any>): Any {

            return when (val processedCall = callAdapter.adapt(call)) {
                is Single<*> -> {
                    processedCall.onErrorResumeNext { Single.error(handleError(it)) }
                }
                is Completable -> {
                    processedCall.onErrorResumeNext { Completable.error(handleError(it)) }
                }

                else -> {
                    processedCall
                }
            }
        }

        override fun responseType(): Type {
            return callAdapter.responseType()
        }
    }

    private fun handleError(throwable: Throwable): Throwable {
        return errorMapper.mapThrowable(throwable)
    }

    private fun parseError(
        errorBodyString: String?
    ): NetworkErrorModel? {

        return try {
            Gson().fromJson(errorBodyString, NetworkErrorModel::class.java)
        } catch (e: JsonSyntaxException) {
            null
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        @Suppress("UNCHECKED_CAST")
        val rxJava2CallAdapter: CallAdapter<Any, Any> =
            rxJava2CallAdapterFactory.get(
                returnType,
                annotations,
                retrofit
            ) as CallAdapter<Any, Any>
        return RxCallAdapterWrapper(annotations, retrofit, rxJava2CallAdapter)
    }
}
