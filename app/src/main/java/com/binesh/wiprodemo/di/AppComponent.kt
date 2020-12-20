package com.binesh.wiprodemo.di

import android.content.Context
import com.binesh.wiprodemo.App
import com.binesh.wiprodemo.di.module.NetworkModule
import com.binesh.wiprodemo.di.module.RepositoryModule
import com.binesh.wiprodemo.di.module.UIBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        AndroidInjectionModule::class,
        UIBuilderModule::class
    ]
)

@Singleton
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}