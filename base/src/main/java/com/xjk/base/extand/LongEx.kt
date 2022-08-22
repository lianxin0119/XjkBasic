package com.xjk.base.extand

/**
 * description:
 *
 * Create by LianXin on 2022/8/17 : 14:24
 *
 * @author Lianxin
 */

/**
 * 是否是0或者null
 */
fun Long?.isNullOrZero(): Boolean {
    return this == null || this == 0L
}
