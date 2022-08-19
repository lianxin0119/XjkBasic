package com.xjk.base.integration

import retrofit2.Retrofit
import com.xjk.base.integration.config.IRepositoryManager

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 16:11
 */
class RepositoryManager constructor(private val retrofit: Retrofit) : IRepositoryManager {

    private val mRetrofitServiceCache = LinkedHashMap<String, Any>()

    /** 注入RetrofitService */
    override fun injectRetrofitService(vararg services: Class<*>) {
        for (service in services) {
            if (mRetrofitServiceCache.containsKey(service.name)) {
                continue
            }
            mRetrofitServiceCache[service.name] = retrofit.create(service)
        }
    }

    /** 根据传入的Class获取对应的Retrofit service */
    @Suppress("UNCHECKED_CAST")
    override fun <T> obtainRetrofitService(service: Class<T>): T {
        return mRetrofitServiceCache[service.name] as T
    }

}