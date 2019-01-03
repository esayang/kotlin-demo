package com.easyang.wanandroid.ui

import com.easyang.base.ui.BaseActivity
import com.easyang.wanandroid.R
import com.easyang.wanandroid.constant.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mBtn.setOnClickListener { showToast("哈哈哈，惊不惊喜") }
    }

    override fun initData() {

    }
}
