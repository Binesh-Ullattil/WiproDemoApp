package com.binesh.wiprodemo.di.module

import android.content.Context
import com.binesh.wiprodemo.App
import com.binesh.wiprodemo.apis.ApiService
import com.binesh.wiprodemo.helper.AppConstants
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val cacheSize=(5*1024*1024).toLong()
        val myCache= App.app.cacheDir?.let { Cache(it,cacheSize) }
        val loggingInterceptor= HttpLoggingInterceptor().apply {
            level= HttpLoggingInterceptor.Level.BODY
        }

        val REWRITE_CACHE_CONTROL_INTERCEPTOR: Interceptor =
            object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalResponse = chain.proceed(chain.request())
                    return if (NetworkStatusHelper.isNetworkAvailable()) {
                        val maxAge = 60 // read from cache for 1 minute
                        originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=$maxAge")
                            .build()
                    } else {
                        val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                        originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                            .build()
                    }
                }
            }

        val httpClient= OkHttpClient.Builder()

        httpClient.apply {
            addInterceptor(loggingInterceptor)
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            cache(myCache)
            networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR)
        }
        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,okHttpClient:OkHttpClient): Retrofit {
      return   Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return ApiService(retrofit)
    }
}