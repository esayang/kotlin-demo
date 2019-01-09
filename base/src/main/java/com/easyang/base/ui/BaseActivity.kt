package com.easyang.base.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easyang.base.BasePresenter
import com.easyang.base.BaseView
import com.easyang.base.R
import com.easyang.base.net.exception.ErrorType
import com.easyang.base.utils.statusbar.Eyes

/**
 * @author SC16004984
 * @date 2018/12/29 0029.
 */
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : BaseView, AppCompatActivity() {

    lateinit var presenter: P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        Eyes.setStatusBarColor(this, resources.getColor(R.color.colorPrimary))
        presenter = createPresenter()
        presenter.attach(this)
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

    override fun showLoadingDialog() {
        showLoadingDialog("正在加载...")
    }

    fun showLoadingDialog(msg: String) {
        showLoadingDialog(true, msg)
    }

    fun showLoadingDialog(cancelable: Boolean, msg: String) {

    }

    override fun cancelLoadingDialog() {

    }

    abstract fun createPresenter(): P


    override fun failed(error: ErrorType, msg: String) {
        showToast(msg)
    }

    override fun getContext(): Context {
        return this;
    }

    override fun onDestroy() {
        presenter.destory()
        super.onDestroy()
    }
}





