package com.xjk.base.kodein

import com.google.gson.Gson
import com.xjk.base.app.BaseApplication
import com.xjk.base.integration.AppManager
import com.xjk.base.integration.RepositoryManager
import com.xjk.base.integration.config.IRepositoryManager
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 16:18
 */

const val KODEIN_MODULE_APP_TAG = "kodein_module_app_tag"

val appModule = Kodein.Module(KODEIN_MODULE_APP_TAG) {

    // AppManager
    bind<AppManager>() with singleton { AppManager(BaseApplication.instance) }

    // IRepositoryManager
    bind<IRepositoryManager>() with singleton { RepositoryManager(instance()) }

    // GSON
    bind<Gson>() with singleton { Gson() }

}