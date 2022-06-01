package com.klaudiak.gamescollector.utils.mappers

import com.klaudiak.gamescollector.data.entities.ExtensionEntity
import com.klaudiak.gamescollector.domain.Extension
import javax.inject.Inject


class ExtensionDatabaseMapper @Inject constructor() : EntityMapper<ExtensionEntity, Extension>{


    fun mapFromListEntities(entities: List<ExtensionEntity>) : List<Extension>{
        return entities.map{mapFromEntity(it)}
    }

    fun mapToListEntities(domainModel: List<Extension>?) : List<ExtensionEntity>{
        return domainModel?.map{mapToEntity(it)} ?: ArrayList()
    }

    override fun mapFromEntity(entity: ExtensionEntity): Extension {
        return Extension(
            id = entity.id,
            name = entity.name,
            image = entity.image,
            released = entity.released
        )
    }

    override fun mapToEntity(domainModel: Extension): ExtensionEntity {
        return ExtensionEntity(
            extensionid = null,
            id = domainModel.id,
            name = domainModel.name,
            image = domainModel.image,
            released = domainModel.released,
        )
    }
}