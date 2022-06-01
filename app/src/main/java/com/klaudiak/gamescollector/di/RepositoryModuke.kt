package com.klaudiak.gamescollector.di

import com.klaudiak.gamescollector.data.local.ExtensionDao
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.local.InfoDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.data.repository.GamesRepositoryImpl
import com.klaudiak.gamescollector.utils.mappers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideGameRepository(
        gameDao: GameDao,
        extensionDao: ExtensionDao,
        infoDao: InfoDao,

        networkService: NetworkService,
        gameDatabaseMapper: GameDatabaseMapper,
        extensionDatabaseMapper: ExtensionDatabaseMapper,
        gameNetworkMapper: GameNetworkMapper,
        extensionNetworkMapper: ExtensionNetworkMapper

    ) : GamesRepositoryImpl {
        return GamesRepositoryImpl(
            gameDao,
            extensionDao,
            infoDao,
            networkService,
            gameDatabaseMapper,
            extensionDatabaseMapper,
            gameNetworkMapper,
        extensionNetworkMapper)
    }



}