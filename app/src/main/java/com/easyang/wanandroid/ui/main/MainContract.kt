package com.easyang.wanandroid.ui.main

import com.easyang.base.BasePresenter
import com.easyang.base.BaseView

/**
 * @author SC16004984
 * @date 2019/1/8 0008.
 */
interface MainContract {
    interface View : BaseView {

    }

    abstract class Presenter : BasePresenter<View>() {
    }

}
