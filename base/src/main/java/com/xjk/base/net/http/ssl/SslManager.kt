package com.xjk.base.net.http.ssl

import android.annotation.SuppressLint
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 17:22
 */
object SslManager {

    fun createSSLSocketFactory(): SSLSocketFactory {
        // TODO("CreateSSLSocketFactory")
        var ssfFactory: SSLSocketFactory? = null
        try {
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, arrayOf(TrustAllCerts()), SecureRandom())
            ssfFactory = sc.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ssfFactory!!
    }

    fun createX509TrustManager(): X509TrustManager {
        // TODO("createX509TrustManager")
        return TrustAllCerts()
    }

    /**
     * X509证书信任管理器类
     * Create by YangYang on 2018/6/16 14:43
     */
    private class TrustAllCerts : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return emptyArray()
        }
    }

}