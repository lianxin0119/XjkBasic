package com.xjk.basic.application

import com.xjk.base.app.BaseApplication

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/13 10:39
 */
class AppApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initThirdSdk()
    }

}