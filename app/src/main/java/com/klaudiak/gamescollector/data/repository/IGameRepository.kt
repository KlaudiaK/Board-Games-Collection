package com.boardgamecollector.data.repository


import com.boardgamecollector.data.entities.GameEntity
import com.boardgamecollector.data.remote.reponses.UserGamesResponse
import com.boardgamecollector.domain.Game
import com.example.rickandmorty.data.remote.reponses.GameResponse
import com.boardgamecollector.utils.*
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    suspend fun getUserGamesId(username: String, stats: String, type: String) : Resources<List<UserGamesResponse.Item>>
    suspend fun getGame(id:String, stats:String) : Resources<GameResponse.Item>
    suspend fun saveGame(game: GameEntity)
    fun getGames() : List<GameEntity>
    suspend fun saveAllToDB(
        username: String,
        stats: String,
        type: String)

}