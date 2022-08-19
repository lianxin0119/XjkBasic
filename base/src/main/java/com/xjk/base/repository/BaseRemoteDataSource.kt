package com.xjk.base.repository

import com.xjk.base.app.BaseApplication
import com.xjk.base.integration.config.IRepositoryManager
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import retrofit2.Retrofit

/**
 * description : 远程数据源的基类
 *
 * Create by LianXin on 2022/8/16 19:24
 */
open class BaseRemoteDataSource : IRemoteDataSource {

    protected var kodein: Kodein = BaseApplication.instance.kodein

    private val repositoryManager: IRepositoryManager by kodein.instance()
    protected val retrofit: Retrofit by kodein.instance()

    fun <T> retrofitService(service: Class<T>): T = repositoryManager.obtainRetrofitService(service)

}