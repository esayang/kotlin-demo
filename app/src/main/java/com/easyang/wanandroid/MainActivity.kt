package com.easyang.wanandroid

import com.easyang.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        mBtn.setOnClickListener { v -> showToast("哈哈哈，惊不惊喜") }
    }

    override fun initData() {
    }
}
