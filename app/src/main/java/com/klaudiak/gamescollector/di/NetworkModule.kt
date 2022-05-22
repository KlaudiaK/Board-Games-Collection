package com.klaudiak.gamescollector.di

import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory
import com.klaudiak.gamescollector.data.remote.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): NetworkService = Retrofit.Builder()
        .baseUrl("https://boardgamegeek.com/wiki/page/BGG_XML_API2/")
        .addConverterFactory(HtmlConverterFactory.create("https://boardgamegeek.com/wiki/page/BGG_XML_API2/"))
        .build()
        .create(NetworkService::class.java)


}