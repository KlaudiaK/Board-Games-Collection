package com.klaudiak.gamescollector.data.repository

import android.util.Log
import com.klaudiak.gamescollector.data.entities.Info
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.local.InfoDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import com.klaudiak.gamescollector.utils.mappers.DatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.NetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
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
        return flow {
            emit(DataState.Loading)
            val countGames : Int = gameDao.countAll().count()
            emit(DataState.Success(countGames))
        }
    }
    suspend fun getUsername(): Flow<DataState<String>>{
        return flow {
            Log.i("Error", "get username ")
            var name: String = ""
            infoDao.getUsername()
            emit(DataState.Loading)
            emit(DataState.Success(name))
        }
    }
    suspend fun getLastSyncDate(): Flow<DataState<String>>{
        return flow {
            emit(DataState.Loading)
            emit(DataState.Success(infoDao.getLastSyncDate()))
        }
    }


     suspend fun getGames(
        username: String,
        stats: String,
        subtype: String
    ): Flow<DataState<List<Game>>> {
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
        return flow {

            emit(DataState.Loading)

            gameDao.deleteAllGames()


            //val games = mutableListOf<GameItemResponse>()
            val responseGamesList = networkService.gamesListFromApi(username, stats, subtype).item
            val sizeFromRemote = responseGamesList?.size
            val games = networkMapper.mapFromEntityList(responseGamesList)
            val gamesLocalList =  databaseMapper.mapToListEntities(games)
            gameDao.insertAll(gamesLocalList)
            val sizeFromLocal =  gameDao.countAll()

            emit(DataState.Success(data = true))
           // emit(DataState.Loading(false))
            // val mappedLocalGames = localGames.map { databaseMapper.mapFromEntity(it)}
          //  emit(DataState.Success(data = localGames.map { databaseMapper.mapFromEntity(it) }))

        }
    }

    suspend fun getUser(): Flow<DataState<String>> {
        return flow{
            emit(DataState.Loading)
            val username = infoDao.getUsername()
        }
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    suspend fun saveUser(username: String): Flow<DataState<String>> {
        Log.i("name", "userinserted!")
        return flow{
            Log.i("name", "userinserted!")
            var name = ""
          //  val current = LocalDateTime.now()
            Log.i("name", "userinserted!")
         //   val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = ""//current.format(formatter)
            try{
                networkService.gamesListFromApi(username, "", "").item
                infoDao.insert(Info(username, formatted))
                emit(DataState.Success(data = name))
                name = infoDao.getUsername()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(DataState.Error<Exception>("Couldn't load data"))

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(DataState.Error<Exception>("This user doesn't exist or you have problem with network connection."))

            }
            emit(DataState.Success(data = name))
        }

    }


}