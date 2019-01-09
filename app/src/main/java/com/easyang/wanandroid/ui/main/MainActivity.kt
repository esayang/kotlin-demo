package com.easyang.wanandroid.ui.main

import com.easyang.base.ui.BaseActivity
import com.easyang.wanandroid.R

class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }
    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
    }


}
