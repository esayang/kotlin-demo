package com.easyang.base.net


import android.content.Context
import com.easyang.base.BaseView
import com.easyang.base.net.exception.ApiException
import com.easyang.base.net.exception.ExceptionEngine
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener

/**
 * Created by SC16004984 on 2018/2/8.
 */

class ResultConsumer {


    /**
     * @return
     */
    val withoutToast: RxErrorHandler
        get() = RxErrorHandler
            .builder()
            .with(null)
            .responseErrorListener { context, t -> }.build()

    fun get(view: BaseView): RxErrorHandler {
        return RxErrorHandler
            .builder()
            .with(view.getContext())
            .responseErrorListener { context, t ->
                val apiException = ExceptionEngine.handleException(t)
                view.failed(apiException.getType(), apiException.msg)
            }.build()
    }

    fun getBy(context: Context): RxErrorHandler {
        return RxErrorHandler
            .builder()
            .with(context)
            .responseErrorListener { context, t ->
                val apiException = ExceptionEngine.handleException(t)
                //SweetToast.show(SweetToast.ERROR, apiException.getMessage());
            }.build()
    }


}
