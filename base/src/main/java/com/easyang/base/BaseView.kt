package com.easyang.base

import android.content.Context
import com.easyang.base.net.exception.ErrorType

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
interface BaseView :IVIew {
    fun showLoadingDialog()
    fun cancelLoadingDialog()
    fun failed(error:ErrorType,msg:String)
    fun getContext():Context
}