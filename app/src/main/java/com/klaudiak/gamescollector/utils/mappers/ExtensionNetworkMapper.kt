package com.klaudiak.gamescollector.utils.mappers


import com.klaudiak.gamescollector.data.remote.reponses.ExtensionItemResponse
import com.klaudiak.gamescollector.domain.Extension
import com.klaudiak.gamescollector.domain.Id
import javax.inject.Inject

class ExtensionNetworkMapper @Inject constructor() : EntityMapper<ExtensionItemResponse, Extension> {

    private fun mapFromIdEntity(entity: ExtensionItemResponse): Id {
        return Id(
            id = entity.id
        )
    }

    fun mapFromEntityList(entities: ArrayList<ExtensionItemResponse>?): List<Extension>? {
        return entities?.map { mapFromEntity(it) }
    }

    fun mapFromEntityIdList(entities: List<ExtensionItemResponse>): List<Id>{
        return entities.map { mapFromIdEntity(it) }
    }

    override fun mapFromEntity(entity: ExtensionItemResponse): Extension {
        return Extension(
            id = entity.id,
            name = entity.title,
            image = entity.image,
            released = entity.year
        )
    }

    override fun mapToEntity(domainModel: Extension): ExtensionItemResponse {
        TODO("Not yet implemented")
    }

}