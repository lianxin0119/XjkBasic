package com.xjk.base.net.http.converter

/**
 * description:
 *
 * Create by LianXin on 2022/9/28 : 13:50
 *
 * @author Lianxin
 */
data class NullToAnyTemp(
    val code: Int = 0,
    val reason: String? = null,
    val result: Any = Any()
)

data class NullToListTemp(
    val code: Int = 0,
    val reason: String? = null,
    val result: List<Any> = emptyList()
)