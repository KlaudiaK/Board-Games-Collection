package com.klaudiak.gamescollector.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.klaudiak.gamescollector.MainApplication
import com.klaudiak.gamescollector.prefs.Preferences
import com.klaudiak.gamescollector.prefs.PreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferances(appApplication: Application): SharedPreferences {
        return appApplication.getSharedPreferences("shared_prefs", MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun providePreferances(sharedPreferences: SharedPreferences): Preferences {
        return PreferencesImpl(sharedPreferences)
    }

}