package com.xjk.base.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 16:09
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    /**
     * 这里可以做一些setContentView之前的操作,如全屏、常亮、设置Navigation颜色、状态栏颜色等
     */
    protected open fun onCreateViewBefore() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onCreateViewBefore()
        _binding = initViewBinding()
        initView()
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    protected fun startActivity(cls: Class<*>) {
        requireActivity().startActivity(Intent(requireContext(), cls))
    }

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