package com.boardgamecollector.data.repository

import com.boardgamecollector.domain.Game
import com.boardgamecollector.utils.DataState
import com.boardgamecollector.utils.mappers.DatabaseMapper
import com.boardgamecollector.utils.mappers.NetworkMapper
import com.example.rickandmorty.data.local.GameDao
import com.example.rickandmorty.data.remote.NetworkService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val gameDao: GameDao,
    private val networkService: NetworkService,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getGames(username: String, stats:String, type:String ): Flow<DataState<List<Game>>> = flow{
        emit(DataState.Loading)
        try{
            val networkGamesIdList = networkService.getUserGames(username, stats, type)
            val gamesId = networkMapper.mapFromEntityIdList(networkGamesIdList)
            for(id in gamesId){
                val networkGame = networkService.getGame(id.id, stats)
                val game = networkMapper.mapFromEntity(networkGame)
                gameDao.insert(databaseMapper.mapToEntity(game))
            }
            val cachedGames = gameDao.getAllGameItems()
            emit(DataState.Success(databaseMapper.mapFromListEntities(cachedGames)))
        }catch (e:Exception){
           // emit(DataState.Error())
        }
    }
}