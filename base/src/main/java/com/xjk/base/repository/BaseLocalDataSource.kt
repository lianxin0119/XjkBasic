package com.xjk.base.repository

import androidx.room.Room
import androidx.room.RoomDatabase
import com.xjk.base.app.BaseApplication

/**
 * description : 本地缓存的数据源基类.
 *
 * Create by LianXin on 2022/8/16 19:25
 */
open class BaseLocalDataSource : ILocalDataSource {

    companion object {
        /** 数据库名称 */
        var dataBaseName = "xjk-database"
    }

    /**
     * 获取数据库
     *
     * @param clz 类型
     * @param name 数据库名称
     */
    @JvmOverloads
    protected inline fun <reified T : RoomDatabase> getDataBase(
        clz: Class<T>,
        name: String = dataBaseName
    ): T {
        return Room.databaseBuilder(BaseApplication.instance, clz, name).build()
    }

    /**
     * 获取数据库 AllowMain
     *
     * @param clz 类型
     * @param name 数据库名称
     */
    @JvmOverloads
    protected inline fun <reified T : RoomDatabase> getDataBaseAllowMain(
        clz: Class<T>,
        name: String = dataBaseName
    ): T {
        return Room.databaseBuilder(BaseApplication.instance, clz, name)
            .allowMainThreadQueries()
            .build()
    }

}