package com.boardgamecollector.data.repository

import com.boardgamecollector.data.entities.GameEntity
import com.boardgamecollector.data.remote.reponses.UserGamesResponse
import com.boardgamecollector.domain.Game
import com.example.rickandmorty.data.remote.reponses.GameResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IGamesDataSource {
    //add response
    suspend fun getGame(gameId: String, stats: String): GameResponse.Item
    suspend fun saveGame(game: GameEntity)
    //add response
    suspend fun getGamesFromApi(username: String, stats: String, subtype: String): List<UserGamesResponse.Item>
     fun getGamesFromDB():List<GameEntity>
    suspend fun deleteAllGames()
    suspend fun deleteGame(gameId: String)
}