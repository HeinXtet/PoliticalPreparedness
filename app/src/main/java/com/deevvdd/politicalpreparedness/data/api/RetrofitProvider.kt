package com.deevvdd.politicalpreparedness.data.api

import com.deevvdd.politicalpreparedness.BuildConfig

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */

import android.annotation.SuppressLint
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.deevvdd.politicalpreparedness.data.api.jsonAdapter.DateAdapter
import com.deevvdd.politicalpreparedness.data.api.jsonAdapter.ElectionAdapter
import com.deevvdd.politicalpreparedness.data.api.jsonAdapter.RepresentativeAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.*


/**
 * Created by Dmitrii Afonin on 22.05.2021
 */
class RetrofitProvider(
    private val moshi: Moshi,
    private val apiUrl: String
) {

    fun provide(): Retrofit {
        val clientBuilder = OkHttpClient.Builder()
            .setAPIKey()
            .setLogger(HttpLoggingInterceptor.Level.BODY)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (VERSION.SDK_INT < VERSION_CODES.N) {
            try {
                val trustAllCerts: Array<TrustManager> = arrayOf(
                    object : X509TrustManager {
                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkClientTrusted(
                            chain: Array<out X509Certificate>?,
                            authType: String?
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkServerTrusted(
                            chain: Array<out X509Certificate>?,
                            authType: String?
                        ) {
                        }
                    }
                )

                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

                clientBuilder.sslSocketFactory(
                    sslSocketFactory,
                    (trustAllCerts[0] as X509TrustManager)
                )
                clientBuilder.hostnameVerifier { _, _ -> true }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

        return Retrofit.Builder()
            .client(clientBuilder.build())
            .setConverters()
            .baseUrl(apiUrl)
            .build()
    }

    private fun Retrofit.Builder.setConverters() =
        addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())


    private fun OkHttpClient.Builder.setLogger(
        logLevel: HttpLoggingInterceptor.Level
    ): OkHttpClient.Builder {
        if (BuildConfig.DEBUG) {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = logLevel
                }
            )
        }
        return this
    }

    private fun OkHttpClient.Builder.setAPIKey(): OkHttpClient.Builder {
        return addInterceptor {
            val original: Request = it.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", BuildConfig.GOOGLE_API_KEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            it.proceed(request)
        }
    }
}
