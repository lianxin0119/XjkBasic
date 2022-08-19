package com.xjk.base.extand

import com.blankj.utilcode.util.BarUtils

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/15 20:12
 */

/**
 * TAG
 */
val Any.TAG
    get() = this.javaClass.simpleName

/**
 * 状态栏高度(px)
 */
val mStatusBarHeight: Int
    get() {
        return BarUtils.getStatusBarHeight()
    }