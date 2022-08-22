package com.xjk.base.net.http.ssl

import android.annotation.SuppressLint
import com.xjk.base.app.BaseApplication
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * description : This person is too lazy to leave anything.
 *
 * Create by LianXin on 2022/8/14 17:22
 */
object SslManager {

    fun createSSLSocketFactory(): SSLSocketFactory? {
        try {
            val cerFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
            val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null)
            val cerAlias = 0.toString()
            keyStore.setCertificateEntry(
                cerAlias,
                cerFactory.generateCertificate(BaseApplication.instance.assets.open("xjk.cer"))
            )
            val sslContext = SSLContext.getInstance("TLS")
            val tm = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            tm.init(keyStore)
            sslContext.init(null, tm.trustManagers, SecureRandom())
            return sslContext.socketFactory
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun createX509TrustManager(): X509TrustManager {
        return TrustAllCerts()
    }

    /**
     * X509证书信任管理器类
     */
    @SuppressLint("CustomX509TrustManager")
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