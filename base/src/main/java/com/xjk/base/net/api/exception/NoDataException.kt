package com.xjk.base.net.api.exception

/**
 * description : 没有数据错误。
 *
 * Create by LianXin on 2022/8/16 20:28
 */
class NoDataException @JvmOverloads constructor(
    code: Int = 0,
    msg: String? = null
) : ApiException(code, msg)