package com.xjk.base.ui.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.xjk.base.extand.dpInt
import com.xjk.base.extand.setBackgroundAlpha

/**
 * description : Dialog基类
 *
 * Create by LianXin on 2022/8/16 20:18
 */
abstract class BaseDialog<T : ViewBinding> : DialogFragment() {

    // region 提供给外部可修改的数据

    /** 宽度(px) */
    var mWidth = ViewGroup.LayoutParams.WRAP_CONTENT
    var mHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    var mGravity = Gravity.CENTER

    /** 偏移量(dp) */
    var mOffsetX = 0
    var mOffsetY = 0
    var mAnimation: Int? = null
    var touchOutside: Boolean = true
    var mSoftInputMode: Int = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

    /** 是否降级背景，例如图片预览的时候不可以降级（设置Activity的透明度） */
    var lowerBackground = false

    /** 点击返回键关闭弹窗 */
    var canPressBackDismiss: Boolean = true
    // endregion

    protected lateinit var mContext: Context
    protected lateinit var binding: T

    /**
     * 消失监听
     */
    private var onDismissListener: (() -> Unit)? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setParams()
        binding = initViewBinding()
        initView()
        return binding.root
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }

    /**
     * 降低背景的Window等级
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (lowerBackground) mContext.setBackgroundAlpha(0.2F)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        if (lowerBackground) mContext.setBackgroundAlpha(1F)
        super.onDestroyView()
    }

    /**
     * 设置dismiss监听
     */
    fun setOnDismissListener(listener: (() -> Unit)?) {
        this.onDismissListener = listener
    }

    /**
     * 根据外部设置的参数修改样式
     */
    private fun setParams() {
        // 无标题
        dialog?.requestWindowFeature(STYLE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(touchOutside)
        // 获取Window
        val window: Window = dialog?.window ?: return
        // 透明背景
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        // 去除 dialog 弹出的阴影
        if (lowerBackground) window.setDimAmount(0F)
        // 设置宽高
        window.decorView.setPadding(0, 0, 0, 0)
        val wlp = window.attributes
        wlp.width = mWidth
        wlp.height = mHeight
        // 设置对齐方式
        wlp.gravity = mGravity
        // 设置偏移量
        wlp.x = mOffsetX.dpInt()
        wlp.y = mOffsetY.dpInt()
        wlp.softInputMode = mSoftInputMode
        // 设置动画
        mAnimation?.also { window.setWindowAnimations(it) }
        window.attributes = wlp
        dialog?.setOnKeyListener { _, keyCode, _ ->
            if (!canPressBackDismiss && keyCode == KeyEvent.KEYCODE_BACK) {
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    abstract fun initViewBinding(): T

    abstract fun initView()

}