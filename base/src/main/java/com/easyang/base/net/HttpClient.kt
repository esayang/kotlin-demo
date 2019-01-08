package com.easyang.base.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit

/**
 * @author SC16004984
 * @date 2019/1/2 0002.
 */
class HttpClient private constructor() {

    fun initHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIME.toLong(), TimeUnit.SECONDS)
            .build()
    }


    fun initRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(initHttpClient())
            .baseUrl(baseUrl)
            .build()
    }

    fun <T : IService> create(baseUrl: String, clazz: Class<T>): T {
        if (!RETROFIT_MAP.containsKey(baseUrl)) {
            synchronized(HttpClient::class.java) {
                if (!RETROFIT_MAP.containsKey(baseUrl)) {
                    RETROFIT_MAP[baseUrl] = initRetrofit(baseUrl)
                }
            }
        }
        return RETROFIT_MAP[baseUrl]!!.create(clazz)
    }

    companion object {
        val CONNECT_TIME = 10
        val READ_TIME = 10

        internal val RETROFIT_MAP = ConcurrentHashMap<String, Retrofit>()
    }


}
