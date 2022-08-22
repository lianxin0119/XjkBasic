package com.xjk.base.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * description : Activity基类
 *
 * Create by LianXin on 2022/7/30 13:32
 */
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    /**
     * viewBinding
     */
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        onCreateBefore()
        initStatus()
        super.onCreate(savedInstanceState)
        inflateViewBinding()
        initView()
        initData()
    }

    /**
     * 初始化ViewBinding, 并将对象设置到界面的contentView中
     */
    open fun inflateViewBinding() {
        binding = initViewBinding()
        setContentView(binding.root)
    }

    protected fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }

    /**
     * 这里可以做一些setContentView之前的操作,如全屏、常亮、设置Navigation颜色、状态栏颜色等
     */
    protected open fun onCreateBefore() {}

    /**
     * 适配状态栏
     */
    protected open fun initStatus() {}

    /**
     * 获取一个当前界面的ViewBinding实例
     */
    protected abstract fun initViewBinding(): T

    /**
     * 初始化界面
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

}