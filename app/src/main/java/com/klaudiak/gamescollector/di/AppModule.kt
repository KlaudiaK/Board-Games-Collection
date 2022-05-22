package com.klaudiak.gamescollector.di

import android.content.Context
import com.klaudiak.gamescollector.data.repository.MainRepository
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.mappers.DatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.EntityMapper
import com.klaudiak.gamescollector.utils.mappers.NetworkMapper
import com.klaudiak.gamescollector.data.local.AppDatabase
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.data.remote.reponses.GameResponse
import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory
import com.klaudiak.gamescollector.data.local.ExtensionDao
import com.klaudiak.gamescollector.data.local.InfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkMapper() : EntityMapper<GameResponse.Item, Game> {
        return NetworkMapper()
    }

    @Provides
    fun provideGameDao(db: AppDatabase): GameDao  = db.gameDao()
    @Provides
    fun provideExtensionDao(db: AppDatabase): ExtensionDao = db.extensionDao()
    @Provides
    fun provideInfoDao(db: AppDatabase): InfoDao = db.infoDao()


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase = AppDatabase.getDatabase(appContext)

}


