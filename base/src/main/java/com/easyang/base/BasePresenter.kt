package com.easyang.base

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
open class BasePresenter<V : IVIew> : IPresenter<V> {
    var view: IVIew? = null

    override fun destory() {
        this.view = null
    }

    override fun attach(view: IVIew) {
        this.view = view
    }


    fun ifViewAttached(onViewAttach: OnViewAttach) {
        if (view != null) {
            onViewAttach.onAttach(view!!)
        }
    }


    interface OnViewAttach {
        fun onAttach(v: IVIew)
    }


}