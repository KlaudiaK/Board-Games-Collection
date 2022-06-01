package com.klaudiak.gamescollector.di

import android.content.Context
import com.klaudiak.gamescollector.data.local.AppDatabase
import com.klaudiak.gamescollector.data.remote.reponses.ExtensionItemResponse
import com.klaudiak.gamescollector.data.remote.reponses.GameItemResponse
import com.klaudiak.gamescollector.domain.Extension
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.mappers.EntityMapper
import com.klaudiak.gamescollector.utils.mappers.ExtensionNetworkMapper
import com.klaudiak.gamescollector.utils.mappers.GameNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideGameNetworkMapper() : EntityMapper<GameItemResponse, Game> {
        return GameNetworkMapper()
    }


    @Provides
    @Singleton
    fun provideExtensionNetworkMapper() : EntityMapper<ExtensionItemResponse, Extension> {
        return ExtensionNetworkMapper()
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)


    @Provides
    fun provideGameDao(db: AppDatabase) = db.gameDao()
    @Provides
    fun provideExtensionDao(db: AppDatabase) = db.extensionDao()
    @Provides
    fun provideInfoDao(db: AppDatabase) = db.infoDao()



}


