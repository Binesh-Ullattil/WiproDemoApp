package com.binesh.wiprodemo.di.module

import android.content.Context
import com.binesh.wiprodemo.apis.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(context: Context): ApiService {
        return ApiService(context)
    }
}