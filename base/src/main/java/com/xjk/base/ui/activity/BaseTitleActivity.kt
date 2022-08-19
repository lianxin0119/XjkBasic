package com.xjk.base.ui.activity

import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.xjk.base.databinding.BaseActivityBaseTitleBinding
import com.xjk.base.databinding.BaseLayoutTitleBarBinding
import com.xjk.base.extand.click
import com.xjk.base.extand.visible

/**
 * description : 带TitleBar的Activity基类
 *
 * Create by LianXin on 2022/7/30 13:44
 */
abstract class BaseTitleActivity<T : ViewBinding> : BaseActivity<T>() {

    /**
     * titleBar的ViewBinding实例
     */
    private lateinit var headBinding: BaseLayoutTitleBarBinding

    /**
     * 设置界面标题的文字
     */
    protected var titleBarName: CharSequence? = null
        set(value) {
            if (this::headBinding.isInitialized) {
                headBinding.tvTitleBarTitle.text = value
            }
            field = value
        }

    override fun inflateViewBinding() {
        val viewBinding = BaseActivityBaseTitleBinding.inflate(layoutInflater)
        headBinding = viewBinding.includeTitleBar
        binding = initViewBinding()
        initHeadView()
        viewBinding.flContent.addView(
            binding.root,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        setContentView(viewBinding.root)
    }

    /**
     * 初始化界面的titleBar
     */
    private fun initHeadView() {
        headBinding.tvTitleBarTitle.text = titleBarName
        headBinding.ivTitleBarBack.click(this::onBackPressed)
    }

    // region 设置标题等相关信息

    /**
     * 界面标题设置到界面中间
     */
    protected fun setTitleOnCenter() {
        headBinding.tvTitleBarTitle.gravity = Gravity.CENTER
    }

    /**
     * 设置界面标题的样式
     */
    protected fun setTitleStyle(style: TextView.() -> Unit) {
        headBinding.tvTitleBarTitle.apply(style)
    }

    // endregion

    // region 设置右侧的按钮的回调/样式等

    /**
     * 设置右侧的按钮的回调
     */
    protected fun setRightOptionImg(@DrawableRes resId: Int, onClick: () -> Unit) {
        headBinding.ivTitleBarOption.run {
            setImageResource(resId)
            visible()
            click(onClick)
        }
    }

    /**
     * 设置右侧的按钮的回调
     */
    protected fun setRightOptionTv(@StringRes text: Int, onClick: () -> Unit) {
        val str = getString(text)
        setRightOptionTv(str, onClick)
    }

    /**
     * 设置右侧的按钮的回调
     */
    protected fun setRightOptionTv(text: CharSequence, onClick: () -> Unit) {
        headBinding.tvTitleBarOption.run {
            setText(text)
            visible()
            click(onClick)
        }
    }

    /**
     * 设置右侧的按钮的回调
     */
    protected fun setRightOption(
        @DrawableRes resId: Int,
        text: CharSequence,
        onClick: () -> Unit
    ) {
        setRightOptionImg(resId, onClick)
        setRightOptionTv(text, onClick)
    }

    /**
     * 设置右侧图片样式
     */
    protected fun setRightImgStyle(style: ImageView.() -> Unit) {
        headBinding.ivTitleBarOption.apply(style)
    }

    /**
     * 设置右侧文字样式
     */
    protected fun setRightIvStyle(style: TextView.() -> Unit) {
        headBinding.tvTitleBarOption.apply(style)
    }
    // endregion

}