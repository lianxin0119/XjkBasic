package com.xjk.basic.ui.test.activity

import androidx.fragment.app.activityViewModels
import com.blankj.utilcode.util.ToastUtils
import com.xjk.base.extand.click
import com.xjk.base.ui.fragment.BaseFragment
import com.xjk.basic.databinding.ActivityMainBinding
import com.xjk.basic.ui.test.viewmodel.TestViewModel

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/18 20:15
 */
class TestFragment : BaseFragment<ActivityMainBinding>() {

    override fun initViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    companion object {
        fun getInstance(): TestFragment {
            return TestFragment()
        }
    }

    // 这两种方式声明的ViewModel完全不一样。
    private val viewModel: TestViewModel by activityViewModels()
//    private val viewModel: TestViewModel by viewModels()

    override fun initView() {
    }

    override fun initData() {
        viewModel.model.name.observe(this) {
            ToastUtils.showShort("值 = $it")
            binding.btn.text = it
        }
        binding.btn.click {
            viewModel.requestName(binding.btn.text.toString() + "-a")
        }
    }

}