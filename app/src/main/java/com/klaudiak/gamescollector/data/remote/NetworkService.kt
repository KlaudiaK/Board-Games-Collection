package com.klaudiak.gamescollector.data.remote

import com.klaudiak.gamescollector.data.remote.reponses.ExtensionResponse
import com.klaudiak.gamescollector.data.remote.reponses.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {


    @GET("/xmlapi2/collection?")
    suspend fun gamesListFromApi(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : GamesResponse

    @GET("/xmlapi2/collection?")
    suspend fun getUserExtensions(
        @Query("username") username: String,
        @Query("subtype") subtype: String
    ) : ExtensionResponse


    companion object {
        const val BASE_URL = "https://boardgamegeek.com"
    }
}




