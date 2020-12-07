package com.binesh.wiprodemo.apis

import android.content.Context
import com.binesh.wiprodemo.helper.AppConstants
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService(context: Context) {

    companion object{
        var retrofit: Retrofit?=null
    }

    init {
        val cacheSize=(5*1024*1024).toLong()
        val myCache= Cache(context.cacheDir,cacheSize)

        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
        val httpClient= OkHttpClient.Builder()

        httpClient.apply {
            addInterceptor(loggingInterceptor)
            readTimeout(60, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit?.create(serviceClass)!!
    }
}