package com.binesh.wiprodemo.di.module

import androidx.lifecycle.ViewModelProvider
import com.binesh.wiprodemo.view.FeedsFragment
import com.binesh.wiprodemo.viewModel.FeedViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [FeedModule::class])
abstract class FeedBuilderModule {

    @ContributesAndroidInjector(modules = [CountryFeedInjectorViewModel::class])
    abstract fun bind(): FeedsFragment

    @Module
    class CountryFeedInjectorViewModel {
        @Provides
        fun provideMovieListViewModelProvider(
            factory: ViewModelProvider.Factory,
            target: FeedsFragment
        ) = ViewModelProvider(target, factory).get(FeedViewModel::class.java)
    }
}