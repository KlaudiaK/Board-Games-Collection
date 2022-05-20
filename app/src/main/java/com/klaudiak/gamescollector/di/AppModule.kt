package com.boardgamecollector.di

import android.content.Context
import com.boardgamecollector.data.repository.MainRepository
import com.boardgamecollector.domain.Game
import com.boardgamecollector.utils.mappers.DatabaseMapper
import com.boardgamecollector.utils.mappers.EntityMapper
import com.boardgamecollector.utils.mappers.NetworkMapper
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.GameDao
import com.example.rickandmorty.data.remote.NetworkService
import com.example.rickandmorty.data.remote.reponses.GameResponse
import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory
import com.klaudiak.mybgc.MainApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton

    fun provideNetworkMapper() : EntityMapper<GameResponse.Item, Game> {
        return NetworkMapper()
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


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMainRepository(
        gameDao: GameDao,
        networkService: NetworkService,
        databaseMapper: DatabaseMapper,
        networkMapper: NetworkMapper
    ) : MainRepository{
        return MainRepository(gameDao, networkService, databaseMapper, networkMapper)
    }
}



@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofitService(): NetworkService = Retrofit.Builder()
        .baseUrl("https://boardgamegeek.com/wiki/page/BGG_XML_API2")
        .addConverterFactory(HtmlConverterFactory.create("https://boardgamegeek.com/wiki/page/BGG_XML_API2"))
        .build()
        .create(NetworkService::class.java)


}


