package com.klaudiak.gamescollector.data.remote

import com.klaudiak.gamescollector.data.remote.reponses.*
import okhttp3.ResponseBody
import org.w3c.dom.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
interface NetworkService {

    @GET("/xmlapi2/collection?")
    suspend fun getUserGames(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : List<UserGamesResponse.Item>



    @GET("/xmlapi2/collection?")
    suspend fun GamesList(
        @Query("username") username: String,
        @Query("stats") stats:String,
        @Query("subtype") subtype: String
    ) : GamesResponse

    @GET("/xmlapi2/collection")
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

    ) : Feed


    @GET("/xmlapi2/username")
    suspend fun getUsername() :String

    @GET("/xmlapi2/thing?comments=1")
    suspend fun thingWithComments(@Query("id") gameId: Int, @Query("page") page: Int): ThingResponse

    @GET("/xmlapi2/user")
    suspend fun user(@Query("name") name: String?): User

    companion object {
        const val BASE_URL = "https://boardgamegeek.com"
    }
}
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



