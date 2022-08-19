package com.xjk.basic.ui.test.activity

import com.xjk.base.ui.activity.BaseTitleActivity
import com.xjk.basic.R
import com.xjk.basic.databinding.ActivityTestBinding

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/18 19:35
 */
class TestActivity : BaseTitleActivity<ActivityTestBinding>() {

    override fun initViewBinding() = ActivityTestBinding.inflate(layoutInflater)

    override fun initView() {
        val bt = supportFragmentManager.beginTransaction()
        bt.add(R.id.fl_first, TestFragment.getInstance())
        bt.add(R.id.fl_second, TestFragment.getInstance())
        bt.commitAllowingStateLoss()
    }

    override fun initData() {
    }

}