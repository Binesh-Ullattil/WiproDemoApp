package com.binesh.wiprodemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.binesh.wiprodemo.di.AppViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module(includes = [FeedBuilderModule::class])
class UIBuilderModule {

    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}