package com.klaudiak.gamescollector.data.repository

import com.klaudiak.gamescollector.data.entities.Info
import com.klaudiak.gamescollector.data.local.GameDao
import com.klaudiak.gamescollector.data.local.InfoDao
import com.klaudiak.gamescollector.data.remote.NetworkService
import com.klaudiak.gamescollector.data.remote.reponses.GameItemResponse
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.utils.DataState
import com.klaudiak.gamescollector.utils.mappers.DatabaseMapper
import com.klaudiak.gamescollector.utils.mappers.DatabaseUserMapper
import com.klaudiak.gamescollector.utils.mappers.NetworkMapper
import com.klaudiak.gamescollector.utils.mappers.UserMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val gameDao: GameDao,
    private val infoDao: InfoDao,
    private val networkService: NetworkService,
    private val databaseMapper: DatabaseMapper,
    private val networkMapper: NetworkMapper,
    private val databaseUserMapper : DatabaseUserMapper,
    private val userMapper: UserMapper
) {

    suspend fun getGames(username: String, stats:String, type:String ): Flow<DataState<List<Game>>> = flow{
        emit(DataState.Loading)


        try {

            val gamesItems: ArrayList<GameItemResponse>? =
                networkService.gamesListFromApi("batman", "1", "boardgame").item

            val games = networkMapper.mapFromEntityList(gamesItems)
            val gamesDB =  databaseMapper.mapToListEntities(games)
            val gamesEntities = databaseMapper.mapToListEntities(games)

            val cachedGames = gameDao.getAllGameItems()


           // for (i in databaseMapper.mapFromListEntities(cachedGames)) Log.i("Info", i.id)
            //emit(DataState.Success(databaseMapper.mapFromListEntities(cachedGames)))

        }catch (e:Exception){
           emit(DataState.Error<IOException>("Cannot get games!"))

        }


    }


    suspend fun getUserFirstTime(username: String): Flow<DataState<String?>> = flow{
        emit(DataState.Loading)
        try{
            val usernameFrom = networkService.getUser(username)
            //val gamesId = networkMapper.mapFromEntityIdList(networkGamesIdList)

           // val cachedGames = gameDa6o.getAllGameItems()
            val user = userMapper.mapFromEntity(usernameFrom)
            infoDao.insert(Info(user.user!!, null))
            val usernameFromDb = infoDao.getUsername()
           // val userInfoFromDB = infoDao.getUsername()
            emit(DataState.Success(usernameFromDb))
        }catch (e: IOException){
            emit(DataState.Error<IOException>("Cannot get user!"))
        }
    }





    suspend fun getUsername(): Flow<DataState<String?>> = flow{
        emit(DataState.Loading)
        try{

            val username = infoDao.getUsername()
            emit(DataState.Success(username))

       }catch (e:IOException){
           emit(DataState.Error<IOException>("Cannot get games!"))

       }
   }
}


