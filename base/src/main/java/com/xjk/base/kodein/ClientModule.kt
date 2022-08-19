package com.xjk.base.kodein

import com.google.gson.GsonBuilder
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import com.xjk.base.net.http.converter.AbnormalConverterFactory
import com.xjk.base.net.http.ssl.SslManager

const val KODEIN_MODULE_CLIENT_TAG = "kodein_module_client_tag"

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 16:20
 */
object ClientModule {

    var callBack: ((build: OkHttpClient.Builder) -> Unit)? = null
    var httpDebug: Boolean = false

    /** 超时时间 */
    private const val TIME_OUT = 30_000L

    val clientModule = Kodein.Module(KODEIN_MODULE_CLIENT_TAG) {

        bind<GsonBuilder>() with singleton {
            GsonBuilder().serializeNulls()
                .enableComplexMapKeySerialization()
                .registerTypeAdapter(
                    Double::class.java,
                    JsonSerializer<Double> { src, _, _ ->
                        if (src != null && src % 1 != 0.0) {
                            // 解决科学计数法转换的问题
                            return@JsonSerializer JsonPrimitive(src.toLong())
                        }
                        JsonPrimitive(src)
                    })
        }

        bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

        bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

        bind<OkHttpClient>() with singleton {
            val builder = instance<OkHttpClient.Builder>()
                .connectTimeout(TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, java.util.concurrent.TimeUnit.SECONDS)
            val interceptors = instance<List<okhttp3.Interceptor>>()
            builder.apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
                if (httpDebug) {
                    // log拦截
                    addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    // TODO : sslSocketFactory
                    sslSocketFactory(
                        SslManager.createSSLSocketFactory(),
                        SslManager.createX509TrustManager()
                    )
                    // 测试服忽略证书校验
                    hostnameVerifier { _, _ -> true }
                }
                callBack?.invoke(this)
            }.build()
        }

        bind<Retrofit>() with singleton {
            instance<Retrofit.Builder>()
                .baseUrl(instance<okhttp3.HttpUrl>())
                .client(instance())
                // 使用rxjava
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                // 使用自定义的解析
                .addConverterFactory(AbnormalConverterFactory.create())
                .build()
        }
    }

}