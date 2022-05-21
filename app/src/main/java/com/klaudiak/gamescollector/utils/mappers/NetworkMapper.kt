package com.klaudiak.gamescollector.utils.mappers

import com.klaudiak.gamescollector.data.remote.reponses.UserGamesResponse
import com.klaudiak.gamescollector.domain.Game
import com.klaudiak.gamescollector.domain.Id
import com.klaudiak.gamescollector.data.remote.reponses.GameResponse
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<GameResponse.Item, Game> {
    override fun mapFromEntity(entity: GameResponse.Item): Game {
        return Game(
            id = entity.id!!,
            name = entity.title,
            image = entity.image,
            description = entity.description,
            released = entity.year,
            rating = entity.rank?.rankPos ?: 0
        )
    }

    override fun mapToEntity(domainModel: Game): GameResponse.Item {
        return GameResponse.Item(
            id = null,
            title = null,
            image = null,
            description = null,
            rank = null
        )
    }

    private fun mapFromIdEntity(entity: UserGamesResponse.Item): Id {
        return Id(
            id = entity.id
        )
    }

    fun mapFromEntityList(entities: List<GameResponse.Item>): List<Game>{
        return entities.map { mapFromEntity(it) }
    }

    fun mapFromEntityIdList(entities: List<UserGamesResponse.Item>): List<Id>{
        return entities.map { mapFromIdEntity(it) }
    }
}