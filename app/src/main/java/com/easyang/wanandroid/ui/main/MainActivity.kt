package com.easyang.wanandroid.ui.main

import com.easyang.base.ui.BaseActivity
import com.easyang.wanandroid.R
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity<MainContract.View, MainPresenter>(), MainContract.View {
    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        tv_title.text = "首页"
    }

    override fun initData() {
    }


}
