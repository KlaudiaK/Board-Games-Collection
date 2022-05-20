package com.klaudiak.mybgc

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        Timber.tag("INFO").i("START")
        //if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}