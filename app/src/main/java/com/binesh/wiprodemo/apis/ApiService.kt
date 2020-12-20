package com.binesh.wiprodemo.apis

import android.content.Context
import com.binesh.wiprodemo.helper.AppConstants
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import com.google.gson.GsonBuilder
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
import javax.inject.Inject

class ApiService @Inject constructor(var retrofit: Retrofit) {

   /* companion object{
        var retrofit: Retrofit?=null
    }

    init {
        retrofit =
    }*/


    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit?.create(serviceClass)!!
    }
}