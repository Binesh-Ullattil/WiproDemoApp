package com.binesh.wiprodemo

import android.app.Application
import com.binesh.wiprodemo.di.DaggerAppComponent
import com.binesh.wiprodemo.helper.NetworkStatusHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App:Application(),HasAndroidInjector {

    companion object{
        lateinit var app: App
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    private fun initDI(){
        DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initDI()
        NetworkStatusHelper.init(this)
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}