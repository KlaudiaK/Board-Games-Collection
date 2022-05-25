package com.klaudiak.gamescollector.data.repository

import android.util.Log
import com.klaudiak.gamescollector.data.entities.GameEntity
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
import kotlinx.coroutines.flow.map
import timber.log.Timber
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
                networkService.GamesList("batman", "1", "boardgame").item


            val games = networkMapper.mapFromEntityList(gamesItems)
            val gamesDB =  databaseMapper.mapToListEntities(games)
           // for (i in gamesDB) Log.i("Info", i.toString())
            val gamesEntities = databaseMapper.mapToListEntities(games)
            //gameDao.deleteAllGames()
            //gameDao.insertAll(databaseMapper.mapToListEntities(games))

            //Log.i("Info", gameDao.getGameById("186435").name.toString())
            Log.i("Info", gameDao.countAll().toString())
            val cachedGames = gameDao.getAllGameItems()
            gameDao.getAllGameItems().map { game -> databaseMapper.mapFromListEntities(game) }
            Log.i("info", "Success")

           // for (i in databaseMapper.mapFromListEntities(cachedGames)) Log.i("Info", i.id)
            emit(DataState.Success(databaseMapper.mapFromListEntities(cachedGames)))


        }catch (e:Exception){
           emit(DataState.Error<IOException>(Exception("Cannot get games!")))

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
            //emit(DataState.Error(e))
            emit(DataState.Error<IOException>(Exception("Cannot get user!")))
        }
    }





    suspend fun getUsername(): Flow<DataState<String?>> = flow{
        emit(DataState.Loading)
        try{



            // Log.i("Info",gamesItems?.get(0)?.name.toString())


            // networkGamesIdList = networkService.getUserGames(username, stats, type)
            val username = infoDao.getUsername()


            emit(DataState.Success(username))

/*
            val item = networkService.getGame("5867", "1").item
            print(item?.get(0)?.type)
            Log.i("Info",item?.get(0)?.description.toString())
            Log.i("Info",item?.get(0)?.type.toString())
            Log.i("Info",item?.get(0)?.thumbnail.toString())
            Log.i("Info",item?.get(0)?.image.toString())
            Log.i("Info",item?.get(0)?.year.toString())
            Log.i("Info",item?.get(0)?.statistics?.stats?.pos.toString())
            val name = networkService.getGame("5867", "1").item?.get(0)?.type
            Log.i("Info", name.toString())
            // Log.i("info",networkService.getGame("102794", "1"))
           //networkMapper.mapFromEntity(networkService.getGame("102794", "1"))

        // val response = networkService.user("batman")
          //  print(networkService.user("batman")::class.java)
           val gamesItems = networkService.GamesList("rahdo", "1","boardgameexpansion" ).item

            Log.i("Info",gamesItems?.get(0)?.name.toString())

 */
       }catch (e:IOException){
           emit(DataState.Error<IOException>(Exception("Cannot get games!")))

       }
   }
}


