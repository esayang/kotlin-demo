package com.easyang.base.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

/**
 * @author SC16004984
 * @date 2018/12/29 0029.
 */
public abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initView()
        initData()
    }

    public abstract fun getLayoutRes(): Int

    public abstract fun initView()

    public abstract fun initData()


    fun showToast(msg: String) {
        showToast(0, msg)
    }

    fun showToast(type: Int, msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showLoadingDialog(msg: String) {
        showLoadingDialog(true, msg)
    }

    fun showLoadingDialog(cancelable: Boolean, msg: String) {

    }

    fun cancelLoadingDialog() {

    }


}





