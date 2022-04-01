package com.lugloc.utils.config

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.lugloc.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConnectionManager {

    private const val MAX_TRYOUTS = 3
    private const val MAX_TRYOUTS_FOR_ACK = 1
    private const val INIT_TRYOUT = 1

    fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClient().build())
            .build()
    }

    fun createRetrofitInstanceForAck(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getHttpClientForAck().build())
            .build()
    }

    private fun getHttpClient(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        httpClient.addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor { chain ->
                val defaultRequest = chain.request()
                val httpUrl = defaultRequest.url().newBuilder().build()
                val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
                chain.proceed(requestBuilder.build())
            }
            .addInterceptor { chain ->
                val request = chain.request()
                var response = chain.proceed(request)
                var tryOuts = INIT_TRYOUT

                while (!response.isSuccessful && tryOuts < MAX_TRYOUTS) {
                    tryOuts++
                    response.close()
                    Log.d("Http Retry: ", "intercept: timeout/connection failure, performing automatic retry")
                    response = chain.proceed(request)
                }
                response}
            .addNetworkInterceptor(StethoInterceptor())

        return httpClient
    }

    private fun getHttpClientForAck(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()

        httpClient
            .retryOnConnectionFailure(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor { chain ->
                val defaultRequest = chain.request()
                val httpUrl = defaultRequest.url().newBuilder().build()
                val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
                chain.proceed(requestBuilder.build())}
            .addInterceptor { chain ->
                val request = chain.request()
                var response = chain.proceed(request)
                var tryOuts = INIT_TRYOUT
                while (!response.isSuccessful && tryOuts < MAX_TRYOUTS_FOR_ACK) {
                    tryOuts++
                    response = chain.proceed(request)
                }
                response}
            .addNetworkInterceptor(StethoInterceptor())

        return httpClient
    }
}
