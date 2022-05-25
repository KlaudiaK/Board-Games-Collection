package com.klaudiak.gamescollector.utils.mappers

import com.klaudiak.gamescollector.data.entities.GameEntity
import com.klaudiak.gamescollector.data.entities.Info
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.domain.GameInfo
import javax.inject.Inject

class DatabaseMapper @Inject constructor() : EntityMapper<GameEntity, Game>{
    override fun mapFromEntity(entity: GameEntity): Game {
        return Game(
            id = entity.id,
            name = entity.name,
            image = entity.image,
            released = entity.released,
            rating = entity.rating
        )
    }

    override fun mapToEntity(domainModel: Game): GameEntity {
        return GameEntity(
            gameid = null,
            id = domainModel.id,
            name = domainModel.name,
            image = domainModel.image,
            released = domainModel.released,
            rating = domainModel.rating
        )
    }

    fun mapFromListEntities(entities: List<GameEntity>) : List<Game>{
        return entities.map{mapFromEntity(it)}
    }

    fun mapToListEntities(domainModel: List<Game>?) : List<GameEntity>{
        return domainModel?.map{mapToEntity(it)} ?: ArrayList()
    }
}

class DatabaseUserMapper @Inject constructor() : EntityMapper<Info, GameInfo> {
    override fun mapFromEntity(entity: Info): GameInfo {
        return GameInfo(username = entity.username, lastSync = entity.lastSyncDate)
    }


    override fun mapToEntity(domainModel: GameInfo): Info {
        return Info(username = domainModel.username!!, lastSyncDate = domainModel.lastSync)
    }

}