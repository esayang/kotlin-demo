package com.easyang.base.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * @author SC16004984
 * @date 2018/12/29 0029.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        initView()
        initData()
    }

    abstract fun getLayoutRes(): Int

    abstract fun initView()

    abstract fun initData()


    fun showToast(msg: String): Unit {
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





