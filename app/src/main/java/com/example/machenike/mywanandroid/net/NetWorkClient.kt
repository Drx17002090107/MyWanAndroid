package com.example.machenike.mywanandroid.net

import android.util.Log
import com.example.machenike.mywanandroid.app.App
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.orhanobut.logger.Logger
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import com.example.machenike.mywanandroid.utils.LogUtils
/**
created time：2019/12/12 17:04
created by：动感超人
Describe ：
 */
object NetWorkClient {
    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(App.CONTEXT)) }
    private val okHttpClient:OkHttpClient by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor(
            object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    if ((message.startsWith("{") && message.endsWith("}")) || (message.startsWith("[") && message.endsWith("]"))) {
                        LogUtils.json(message)
                    } else {
                        LogUtils.verbose(message)
                    }
                }
            })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder =  OkHttpClient.Builder()
        builder.run {
            connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            readTimeout(5_000, TimeUnit.MILLISECONDS)
            writeTimeout(5_000L, TimeUnit.MILLISECONDS)
            addInterceptor(httpLoggingInterceptor)
        }
        persistentCookie(builder)
        builder.build()
    }
    fun persistentCookie(builder:OkHttpClient.Builder){
        val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            .cookieJar(cookieJar)
//            .addInterceptor { chain ->
//                var request = chain.request()
//                if (!isNetworkAvailable(App.CONTEXT)) {
//                    request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build()
//                }
//                val response = chain.proceed(request)
//                if (!isNetworkAvailable(App.CONTEXT)) {
//                    val maxAge = 60 * 60
//                    response.newBuilder()
//                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public, max-age=$maxAge")
//                        .build()
//                } else {
//                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
//                    response.newBuilder()
//                        .removeHeader("Pragma")
//                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
//                        .build()
//                }
//                response
//            }
    }
    val retrofitService:ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

}