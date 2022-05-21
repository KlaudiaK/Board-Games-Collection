package com.klaudiak.gamescollector.data.remote

import com.klaudiak.gamescollector.data.remote.reponses.GameResponse
import retrofit2.Response

interface GameRemoteDataSource {
    suspend fun getGames() : Response<List<GameResponse>>
}