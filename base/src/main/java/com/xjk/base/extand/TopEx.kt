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

/**
 * 全部非空才执行
 */
fun <T1, T2> safe(t1: T1?, t2: T2?, block: (T1, T2) -> Unit) {
    if (t1 != null && t2 != null) {
        block(t1, t2)
    }
}

fun <T1, T2, T3> safe(t1: T1?, t2: T2?, t3: T3?, block: (T1, T2, T3) -> Unit) {
    if (t1 != null && t2 != null && t3 != null) {
        block(t1, t2, t3)
    }
}

fun <T1, T2, T3, T4> safe(t1: T1?, t2: T2?, t3: T3?, t4: T4?, block: (T1, T2, T3, T4) -> Unit) {
    if (t1 != null && t2 != null && t3 != null && t4 != null) {
        block(t1, t2, t3, t4)
    }
}