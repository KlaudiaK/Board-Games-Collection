package com.klaudiak.gamescollector.utils.mappers

import android.util.Log
import com.klaudiak.gamescollector.data.remote.reponses.GameItemResponse
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.domain.Id
import com.klaudiak.gamescollector.data.remote.reponses.UserExistResponse
import com.klaudiak.gamescollector.domain.Username
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<GameItemResponse, Game> {
    override fun mapFromEntity(entity: GameItemResponse): Game {
        return Game(
            id = entity.id,
            name = entity.title,
            image = entity.image,
            released = entity.year?.year,
            rating = entity.statistics?.stats?.pos
        )
    }

    override fun mapToEntity(domainModel: Game): GameItemResponse {
        return GameItemResponse(
            id = "1",
            title = null,
            image = null,

        )
    }

    private fun mapFromIdEntity(entity: GameItemResponse): Id {
        return Id(
            id = entity.id
        )
    }

    fun mapFromEntityList(entities: ArrayList<GameItemResponse>?): List<Game>? {
        Log.i("Info","In mapper")
        return entities?.map { mapFromEntity(it) }
    }

    fun mapFromEntityIdList(entities: List<GameItemResponse>): List<Id>{
        return entities.map { mapFromIdEntity(it) }
    }



}

class UserMapper @Inject constructor() : EntityMapper<UserExistResponse, Username> {

    override fun mapFromEntity(entity: UserExistResponse): Username {
        return Username(user = entity.firstname)
    }

    override fun mapToEntity(domainModel: Username): UserExistResponse {
        return UserExistResponse(firstname = null)
    }
}