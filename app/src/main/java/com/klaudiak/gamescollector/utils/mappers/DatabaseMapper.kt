package com.boardgamecollector.utils.mappers

import com.boardgamecollector.data.entities.GameEntity
import com.boardgamecollector.domain.Game
import javax.inject.Inject

class DatabaseMapper @Inject constructor() : EntityMapper<GameEntity, Game> {
    override fun mapFromEntity(entity: GameEntity): Game {
        return Game(
            id = entity.id!!,
            name = entity.name,
            image = entity.image,
            description = entity.description,
            released = entity.released,
            rating = entity.rating
        )
    }

    override fun mapToEntity(domainModel: Game): GameEntity {
        return GameEntity(
            id = domainModel.id,
            name = domainModel.name,
            image = domainModel.image,
            description = domainModel.description,
            released = domainModel.released,
            rating = domainModel.rating
        )
    }

    fun mapFromListEntities(entities: List<GameEntity>) : List<Game>{
        return entities.map{mapFromEntity(it)}
    }
}