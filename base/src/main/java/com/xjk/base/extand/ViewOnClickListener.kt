package com.xjk.base.extand

import android.view.View

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/15 20:01
 */
internal class ViewOnClickListener constructor(
    /** 防抖时间间隔(ms) */
    private val timeMillis: Long,
    /** 按钮事件 */
    private val onClick: (() -> Unit)?
) : View.OnClickListener {

    /**
     * 上次点击时间
     */
    private var lastClickTime: Long = 0L

    override fun onClick(v: View?) {
        val now = System.currentTimeMillis()
        if (now - lastClickTime > timeMillis) {
            lastClickTime = now
            onClick?.invoke()
        }
    }

}