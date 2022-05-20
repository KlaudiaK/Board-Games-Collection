package com.example.rickandmorty.data.remote

import com.boardgamecollector.data.remote.reponses.UserGamesResponse
import com.boardgamecollector.domain.Id
import com.example.rickandmorty.data.remote.reponses.ExtensionResponse
import com.example.rickandmorty.data.remote.reponses.GameResponse
import com.example.rickandmorty.data.remote.reponses.UserExistResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response
import javax.inject.Inject
/*
interface NetworkService {

    @GET("collection?")
    suspend fun getUserGames(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : Response<List<String>>

    @GET("collection?")
    suspend fun getUserExtensions(
        @Query("username") username: String,
        @Query("subtype") subtype: String
    ) : Response<ExtensionResponse>

    @GET
    suspend fun getUser(
        @Query("username") username: String
    ) : Response<UserExistResponse>

    @GET("thing?")
    suspend fun getGame(
        @Query("id") id: String,
        @Query("stats") stats: String
    ) : Response<GameResponse.Item>
}


 */

interface NetworkService {

    @GET("collection?")
    suspend fun getUserGames(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : List<UserGamesResponse.Item>

    @GET("collection?")
    suspend fun getUserExtensions(
        @Query("username") username: String,
        @Query("subtype") subtype: String
    ) : ExtensionResponse

    @GET
    suspend fun getUser(
        @Query("username") username: String
    ) : UserExistResponse

    @GET("thing?")
    suspend fun getGame(
        @Query("id") id: String?,
        @Query("stats") stats: String
    ) :GameResponse.Item
}

