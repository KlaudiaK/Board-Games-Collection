package com.boardgamecollector.data.repository

import com.boardgamecollector.data.entities.GameEntity
import com.boardgamecollector.data.remote.reponses.UserGamesResponse
import com.boardgamecollector.domain.Game
import com.example.rickandmorty.data.local.GameDao
import com.example.rickandmorty.data.remote.NetworkService
import com.example.rickandmorty.data.remote.reponses.GameResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class GamesDataSource(
    private val networkService: NetworkService,
    private val gameDao: GameDao
    ) : IGamesDataSource {
    // add Response
    override suspend fun getGame(gameId: String, stats: String): GameResponse.Item {
       return networkService.getGame(gameId, stats)
    }

    override suspend fun saveGame(game: GameEntity) {
        gameDao.insert(game)
    }
 //add response
    override suspend fun getGamesFromApi(username: String,stats:String, subtype: String): List<UserGamesResponse.Item> {
        return networkService.getUserGames(username, stats, subtype )
    }

    override fun getGamesFromDB(): List<GameEntity> {
        return gameDao.getAllGameItems()
    }


    override suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

    override suspend fun deleteGame(gameId: String) {
        gameDao.delete(gameId)
    }
}

