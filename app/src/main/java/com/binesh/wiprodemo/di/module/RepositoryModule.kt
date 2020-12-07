package com.binesh.wiprodemo.di.module

import com.binesh.wiprodemo.apis.ApiService
import com.binesh.wiprodemo.repository.CountryFeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    internal fun provideMovieListRepository(
        apiService: ApiService
    ): CountryFeedRepository {
        return CountryFeedRepository(apiService)
    }
}