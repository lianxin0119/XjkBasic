package com.xjk.base.net.http.converter

/**
 * description:
 *
 * Create by LianXin on 2022/9/28 : 13:50
 *
 * @author Lianxin
 */
data class NullToAnyTemp(
    var code: Int = 0,
    var reason: String? = null,
    val result: Any = Any()
)