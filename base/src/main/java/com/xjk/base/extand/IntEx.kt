package com.xjk.base.extand

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/15 20:18
 */

/** 转换成dp值，返回float类型 */
fun Int.dp() = toFloat().dp()

/** 转换成dp值，返回int类型 */
fun Int.dpInt() = dp().toInt()

/** 如果为null就返回0。否则返回自身。 */
fun Int?.orZero(): Int {
    return this ?: 0
}

/** (中缀表达式) 如果为null就返回指定的数。否则返回自身 */
infix fun Int?.ifNullTo(i: Int): Int {
    return this ?: i
}

infix fun Int?.ifNullOrZeroTo(i: Int): Int {
    return if (this == null || this == 0) i else this
}
