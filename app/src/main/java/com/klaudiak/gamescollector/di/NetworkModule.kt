package com.klaudiak.gamescollector.di

import com.klaudiak.gamescollector.data.remote.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(2, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
    @Provides
    @Singleton
    fun provideRetrofitService(): NetworkService = Retrofit.Builder()
        .baseUrl(NetworkService.BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .client(OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()
        )
        .build()
        .create(NetworkService::class.java)

    //https://boardgamegeek.com/xmlapi2/
    //https://boardgamegeek.com/xmlapi/boardgame/1/

}