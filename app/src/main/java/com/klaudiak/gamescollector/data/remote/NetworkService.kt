package com.klaudiak.gamescollector.data.remote

import com.klaudiak.gamescollector.data.remote.reponses.*
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("/xmlapi2/username={username}")
    suspend fun getUser(
        @Path("username") username: String
    ) : UserExistResponse

    @GET("/xmlapi2/thing?")
    suspend fun getGame(
        @Query("id") id: String?,
        @Query("stats") stats: String,

    ) : UserFeed


/*
    @GET("/xmlapi2/collection?")
    suspend fun getUserGames(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : List<UserGamesResponse.Item>

 */

    companion object {
        const val BASE_URL = "https://boardgamegeek.com"
    }
}




