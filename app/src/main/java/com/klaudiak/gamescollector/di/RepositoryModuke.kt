package com.klaudiak.gamescollector.di

import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.data.repository.MainRepository
import com.klaudiak.gamescollector.utils.mappers.DatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.NetworkMapper
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
    fun provideMainRepository(
        gameDao: GameDao,
        networkService: NetworkService,
        databaseMapper: DatabaseMapper,
        networkMapper: NetworkMapper
    ) : MainRepository {
        return MainRepository(gameDao, networkService, databaseMapper, networkMapper)
    }
}