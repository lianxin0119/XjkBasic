package com.xjk.base.net.api.exception

/**
 * description : 网络请求异常的Exception.
 *
 * Create by LianXin on 2022/8/16 20:27
 */
open class ApiException @JvmOverloads constructor(
    var code: Int = 0,
    var msg: String? = null
) : Exception(message = msg)

