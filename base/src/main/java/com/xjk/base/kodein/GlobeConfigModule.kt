package com.xjk.base.kodein

import androidx.annotation.Size
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import com.xjk.base.net.http.GlobeHttpHandler

const val KODEIN_MODULE_GLOBE_CONFIG_TAG = "kodein_module_globe_config_tag"

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 15:20
 */
class GlobeConfigModule private constructor(builder: Builder) {

    companion object {
        fun builder(): Builder = Builder()
    }

    private var mApiUrl: HttpUrl =
        builder.baseUrl ?: throw RuntimeException("baseUrl can not be empty")
    private var mHandler: GlobeHttpHandler = builder.handler ?: GlobeHttpHandler.EMPTY
    private var mInterceptors: List<Interceptor> = builder.interceptors

    val globeConfigModule = Kodein.Module(KODEIN_MODULE_GLOBE_CONFIG_TAG) {
        bind<HttpUrl>() with singleton { mApiUrl }
        bind<List<Interceptor>>() with singleton { mInterceptors }
        bind<GlobeHttpHandler>() with singleton { mHandler }
    }

    class Builder {

        var baseUrl: HttpUrl? = null
        var handler: GlobeHttpHandler? = null
        val interceptors = ArrayList<Interceptor>()

        fun baseUrl(@Size(min = 1) baseUrl: String): Builder {
            if (baseUrl.isBlank()) {
                throw IllegalArgumentException("baseUrl can not be empty")
            }
            this.baseUrl = baseUrl.toHttpUrlOrNull()
            return this
        }

        /** 用来处理http响应结果 */
        fun globeHttpHandler(handler: GlobeHttpHandler): Builder {
            this.handler = handler
            return this
        }

        /** 动态添加任意个interceptor */
        fun addInterceptor(interceptor: Interceptor): Builder {
            this.interceptors.add(interceptor)
            return this
        }

        fun build(): GlobeConfigModule {
            return GlobeConfigModule(this)
        }

    }

}