package com.xjk.base.integration

import android.app.Application
import android.content.pm.PackageManager
import com.xjk.base.integration.config.ConfigModule

/**
 * description : 解析清单文件, 找到模块配置
 *
 * Create by LianXin on 2022/8/14 14:53
 */
internal class ManifestParser constructor(private var context: Application) {

    private val moduleValue = "ConfigModule"

    fun parse(): List<ConfigModule> {
        val modules = ArrayList<ConfigModule>()
        try {
            val appInfo = context.packageManager
                .getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            if (appInfo.metaData != null) {
                for (key in appInfo.metaData.keySet()) {
                    if (moduleValue == appInfo.metaData.get(key)) {
                        modules.add(parseModule(key))
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("找不到标签metadata去解析ConfigModule", e)
        }

        return modules
    }

    private fun parseModule(className: String): ConfigModule {
        val clazz: Class<*>
        try {
            clazz = Class.forName(className)
        } catch (e: ClassNotFoundException) {
            throw IllegalArgumentException("找不到ConfigModule实现", e)
        }

        val module: Any
        try {
            module = clazz.newInstance()
        } catch (e: InstantiationException) {
            throw RuntimeException("无法实例化ConfigModule的实现1 $clazz", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("无法实例化ConfigModule的实现2 $clazz", e)
        }

        if (module !is ConfigModule) {
            throw RuntimeException("对象错误, but found: $module")
        }
        return module
    }

}