package com.binesh.wiprodemo.di.module

import androidx.lifecycle.ViewModel
import com.binesh.wiprodemo.di.ViewModelKey
import com.binesh.wiprodemo.repository.CountryFeedRepository
import com.binesh.wiprodemo.viewModel.FeedViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class FeedModule {

    @Provides
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    fun provideCountryFeedViewModel(repository: CountryFeedRepository): ViewModel {
        return FeedViewModel(repository)
    }

}