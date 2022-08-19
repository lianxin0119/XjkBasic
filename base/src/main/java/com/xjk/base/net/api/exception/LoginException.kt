package com.xjk.base.net.api.exception

/**
 * description : 登录异常
 *
 * Create by LianXin on 2022/8/16 20:27
 */
class LoginException @JvmOverloads constructor(
    code: Int = 0,
    msg: String? = null
) : ApiException(code, msg)