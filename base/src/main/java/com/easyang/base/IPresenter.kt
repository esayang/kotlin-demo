package com.easyang.base

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
interface IPresenter<V : IVIew> {
    fun attach(view: IVIew)
    fun destory()
}