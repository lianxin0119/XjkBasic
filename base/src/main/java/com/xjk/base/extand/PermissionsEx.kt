package com.xjk.base.extand

import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions3.RxPermissions

/**
 * description : 权限相关扩展
 *
 * Create by LianXin on 2022/8/16 20:00
 */

@JvmOverloads
fun FragmentActivity.requestPermissions(
    vararg permissions: String,
    onGranted: (() -> Unit)? = null,
    onDenied: (() -> Unit)? = null
) {
    RxPermissions(this)
        .request(*permissions)
        .subscribe {
            if (it) {
                onGranted?.invoke()
            } else {
                onDenied?.invoke()
            }
        }
}