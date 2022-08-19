package com.xjk.base.extand

import android.view.View

/**
 * description : View相关扩展函数
 *
 * Create by LianXin on 2022/7/31 16:53
 */

/** 设置View的可见状态为[View.GONE] */
fun View.gone() {
    visibility = View.GONE
}

/** 设置View的可见状态为[View.VISIBLE] */
fun View.visible() {
    visibility = View.VISIBLE
}

/** 设置View的可见状态为[View.INVISIBLE] */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/** 设置View可用 */
fun View.enable() {
    this.isEnabled = true
}

/** 设置View不可用 */
fun View.disable() {
    this.isEnabled = false
}

/**
 * View设置点击事件，防抖
 */
fun View.click(timeMillis: Long, onClick: (() -> Unit)?) {
    this.setOnClickListener(ViewOnClickListener(timeMillis, onClick))
}

/**
 * 没有使用kotlin的重载是想可以直接传递闭包函数
 */
fun View.click(onClick: () -> Unit) {
    this.setOnClickListener(ViewOnClickListener(600, onClick))
}

/**
 * 根据条件表达式设置View.VISIBLE 和 View.GONE，不包括View.INVISIBLE
 * @param expression true 为 View.VISIBLE false 为View.GONE
 */
fun View.visibilityWith(expression: () -> Boolean) {
    if (expression.invoke()) visible() else gone()
}

/**
 * 根据条件表达式设置View.VISIBLE 和 View.GONE，不包括View.INVISIBLE
 * @param expression true 为 View.VISIBLE false 为View.GONE
 */
fun View.visibilityWith(expression: Boolean) {
    if (expression) visible() else gone()
}