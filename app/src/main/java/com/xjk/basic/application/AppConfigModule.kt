package com.xjk.basic.application

import android.app.Application
import android.content.Context
import com.xjk.base.integration.config.AppLifecycle
import com.xjk.base.integration.config.ConfigModule
import com.xjk.base.integration.config.IRepositoryManager
import com.xjk.base.kodein.GlobeConfigModule

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/16 20:37
 */
class AppConfigModule : ConfigModule {

    override fun applyOptions(context: Context, builder: GlobeConfigModule.Builder) {
    }

    override fun registerComponents(context: Context, repositoryManager: IRepositoryManager) {
        // 注入api
        repositoryManager.injectRetrofitService()
    }

    override fun injectAppLifecycle(context: Context, lifecycles: ArrayList<AppLifecycle>) {
    }

    override fun injectActivityLifecycle(
        context: Context,
        lifecycles: ArrayList<Application.ActivityLifecycleCallbacks>
    ) {
    }

}