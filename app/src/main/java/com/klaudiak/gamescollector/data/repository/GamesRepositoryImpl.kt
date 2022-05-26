package com.klaudiak.gamescollector.data.repository

import android.util.Log
import com.klaudiak.gamescollector.data.entities.Info
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.local.InfoDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import com.klaudiak.gamescollector.utils.Resources
import com.klaudiak.gamescollector.utils.mappers.DatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.NetworkMapper
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gameDao: GameDao,
    private val infoDao: InfoDao,
    private val networkService: NetworkService,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper,
) {

    suspend fun getUserGamesCount(): Flow<DataState<Int>>{
        var countGames : Int = 0

        return flow {
            emit(DataState.Loading)
            countGames =gameDao.countAll()
           emit(DataState.Success(countGames))


             }
        }


    suspend fun getUsername(): Flow<DataState<String>>{
        var name = ""

        return flow {
            emit(DataState.Loading)

            name =  infoDao.getUsername()
          //  Log.i("NAME FROM DB", infoDao.getUsername())
          //  Log.i("Repo" , name)
           // emit(DataState.Loading)
            emit(DataState.Success(name))
        }
    }



    suspend fun getLastSyncDate(): Flow<DataState<String>>{
        return flow {
            emit(DataState.Loading)
            emit(DataState.Success(infoDao.getLastSyncDate()))
        }
    }


     suspend fun getGames(): Flow<DataState<List<Game>>> {
        return flow {
            emit(DataState.Loading)

            val localGames = gameDao.getAllGameItems()
           // val mappedLocalGames = localGames.map { databaseMapper.mapFromEntity(it)}
            emit(DataState.Success(data = localGames.map { databaseMapper.mapFromEntity(it)}))

        }
    }


    suspend fun getGamesSynchronize(
        username: String,
        stats: String,
        subtype: String
    ): Flow<DataState<Boolean>> {

        gameDao.deleteAllGames()

        return flow {

            emit(DataState.Loading)

            val responseGamesList = networkService.gamesListFromApi(username, stats, subtype).item
            val sizeFromRemote = responseGamesList?.size
            val games = networkMapper.mapFromEntityList(responseGamesList)
            val gamesLocalList =  databaseMapper.mapToListEntities(games)
            gameDao.insertAll(gamesLocalList)
            val sizeFromLocal =  gameDao.countAll()
            emit(DataState.Success(data = true))
        }
    }

    suspend fun getUser(): Flow<DataState<String>> {
        return flow{
            emit(DataState.Loading)
            val username = infoDao.getUsername()
        }
    }


    suspend fun saveUser(username: String): Flow<DataState<String>> {

        return flow{


            infoDao.insert(Info(1,username, ""))

            var name = ""
          //  val current = LocalDateTime.now()

         //   val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = ""//current.format(formatter)

            networkService.gamesListFromApi(username, "1", "boardgame").item
            infoDao.insert(Info(0,username, formatted))
            Log.i("name", "success!")
            emit(DataState.Success(data = name))

        }

    }


    suspend fun deleteData() {

        infoDao.deleteAll()
        gameDao.deleteAllGames()
        // TODO delete all extensions

    }


}