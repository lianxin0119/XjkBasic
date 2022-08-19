package com.xjk.base.integration.config

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 14:51
 */
interface IRepositoryManager {

    /** 注入RetrofitService */
    fun injectRetrofitService(vararg services: Class<*>)

    /** 根据传入的Class获取对应的Retrofit service */
    fun <T> obtainRetrofitService(service: Class<T>): T

}