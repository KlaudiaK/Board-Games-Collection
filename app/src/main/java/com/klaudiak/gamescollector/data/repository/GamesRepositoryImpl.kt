package com.klaudiak.gamescollector.data.repository

import com.klaudiak.gamescollector.data.entities.Info
import com.klaudiak.gamescollector.data.local.ExtensionDao
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.local.InfoDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.domain.Extension
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import com.klaudiak.gamescollector.utils.mappers.ExtensionDatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.ExtensionNetworkMapper
import com.klaudiak.gamescollector.utils.mappers.GameDatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.GameNetworkMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import java.text.SimpleDateFormat
import java.util.*

class GamesRepositoryImpl @Inject constructor(
    private val gameDao: GameDao,
    private val extensionDao: ExtensionDao,
    private val infoDao: InfoDao,
    private val networkService: NetworkService,
    private val gameDatabaseMapper: GameDatabaseMapper,
    private val extensionDatabaseMapper: ExtensionDatabaseMapper,
    private val gameNetworkMapper: GameNetworkMapper,
    private val extensionNetworkMapper: ExtensionNetworkMapper
) {

    suspend fun getUserGamesCount(): Flow<DataState<Int>>{
        var countGames : Int = 0

        return flow {
             emit(DataState.Loading)
             countGames =gameDao.countAll()
             emit(DataState.Success(countGames))
             }
        }

    suspend fun getUserExtensionsCount(): Flow<DataState<Int>>{
        var countExtensions : Int = 0

        return flow {
            emit(DataState.Loading)
            countExtensions = extensionDao.countAll()
            emit(DataState.Success(countExtensions))
        }
    }



    suspend fun getUsername(): Flow<DataState<String>>{
        var name = ""
        return flow {
            emit(DataState.Loading)
            name =  infoDao.getUsername()
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
            emit(DataState.Success(data = localGames.map { gameDatabaseMapper.mapFromEntity(it)}))

        }
    }

    suspend fun getExtensions(): Flow<DataState<List<Extension>>> {
        return flow {
            emit(DataState.Loading)
            val localExtensions = extensionDao.getAllExtensionsItems()
            emit(DataState.Success(data = localExtensions.map { extensionDatabaseMapper.mapFromEntity(it)}))

        }
    }


    suspend fun getGamesSortedByReleaseYear(): Flow<DataState<List<Game>>> {
        return flow {
            emit(DataState.Loading)
            val localGames = gameDao.getGamesSortedByReleaseYear()
            emit(DataState.Success(data = localGames.map { gameDatabaseMapper.mapFromEntity(it)}))

        }
    }

    suspend fun getGamesSortedByTitle(): Flow<DataState<List<Game>>> {
        return flow {
            emit(DataState.Loading)
            val localGames = gameDao.getGamesSortedByTitle()
            emit(DataState.Success(data = localGames.map { gameDatabaseMapper.mapFromEntity(it)}))

        }
    }

    suspend fun getGamesSortedByRating(): Flow<DataState<List<Game>>> {
        return flow {
            emit(DataState.Loading)

            val localGames = gameDao.getGamesSortedByRating()
            // val mappedLocalGames = localGames.map { databaseMapper.mapFromEntity(it)}
            emit(DataState.Success(data = localGames.map { gameDatabaseMapper.mapFromEntity(it)}))

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
            val games = gameNetworkMapper.mapFromEntityList(responseGamesList)
            val gamesLocalList =  gameDatabaseMapper.mapToListEntities(games)
            gameDao.insertAll(gamesLocalList)
            val sizeFromLocal =  gameDao.countAll()
            emit(DataState.Success(data = true))
        }
    }


    suspend fun getExtensionsSynchronize(
        username: String,
        subtype: String
    ): Flow<DataState<Boolean>> {

        extensionDao.deleteAllExtensions()

        return flow {

            emit(DataState.Loading)

            val responseExtensionsList = networkService.getUserExtensions(username, subtype).list
            val sizeFromRemote = responseExtensionsList?.size
            val extensions = extensionNetworkMapper.mapFromEntityList(responseExtensionsList)
            val extensionsLocalList =  extensionDatabaseMapper.mapToListEntities(extensions)
            extensionDao.insertAll(extensionsLocalList)
            val sizeFromLocal =  extensionDao.countAll()
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
        val simpleDateFormat= SimpleDateFormat("dd-MM-yyyy HH:MM:SS")
        val currentDT: String = simpleDateFormat.format(Date())

        return flow{
        //    infoDao.insert(Info(1,username, ""))


            var name = ""
          //  val current = LocalDateTime.now()

         //   val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
           // val currentDate = ""//current.format(formatter)
           // val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
          //  val currentDate = currentDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
            networkService.gamesListFromApi(username, "1", "boardgame").item
            infoDao.insert(Info(0,username, currentDT))
            emit(DataState.Success(data = name))

        }

    }


    suspend fun deleteData(): Flow<DataState<Boolean>> {
        return flow{
            infoDao.deleteAll()
            gameDao.deleteAllGames()
            extensionDao.deleteAllExtensions()
            emit(DataState.Success(data = true))
        }

        // TODO delete all extensions

    }


}