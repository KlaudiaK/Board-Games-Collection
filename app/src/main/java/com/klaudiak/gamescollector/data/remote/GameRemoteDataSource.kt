package com.example.rickandmorty.data.remote

import com.example.rickandmorty.data.remote.reponses.GameResponse
import retrofit2.Response

interface GameRemoteDataSource {
    suspend fun getGames() : Response<List<GameResponse>>
}