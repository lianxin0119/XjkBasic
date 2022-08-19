package com.xjk.basic.ui.main

import androidx.activity.viewModels
import com.blankj.utilcode.util.ToastUtils
import com.xjk.base.app.BaseApplication
import com.xjk.base.extand.click
import com.xjk.base.integration.AppManager
import com.xjk.base.ui.activity.BaseTitleActivity
import com.xjk.basic.databinding.ActivityMainBinding
import org.kodein.di.generic.instance
import com.xjk.basic.ui.test.activity.TestActivity
import com.xjk.basic.ui.test.viewmodel.TestViewModel

class MainActivity : BaseTitleActivity<ActivityMainBinding>() {

    override fun initViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val viewModel: TestViewModel by viewModels()
    private val appManager: AppManager by BaseApplication.instance.kodein.instance()

    override fun initView() {
    }

    override fun initData() {
        viewModel.model.name.observe(this) {
            ToastUtils.showShort("值 = $it")
            binding.btn.text = it
        }
//        binding.btn.click {
//            viewModel.requestName(binding.btn.text.toString() + "-a")
//        }
        binding.btn.click {
            ToastUtils.showShort(appManager.getActivityList().size.toString())
            startActivity(TestActivity::class.java)
        }
    }

}