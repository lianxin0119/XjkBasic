package com.xjk.base.integration.config

import android.app.Application
import android.content.Context

/**
 * description : 用于代理 [Application] 的生命周期。
 *
 * Create by LianXin on 2022/8/14 14:49
 */
interface AppLifecycle {

    fun attachBaseContext(context: Context)

    fun onCreate(application: Application)

    fun onTerminate(application: Application?)

}
