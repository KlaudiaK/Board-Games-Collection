package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "items", strict = false)
data class ExtensionResponse (
    @field:ElementList(name = "item", inline = true)
    @param:ElementList(name = "item", inline = true)
    val  list: ArrayList<ExtensionItemResponse>? = null

)

@Root(name = "item", strict = false)
data class ExtensionItemResponse(

    @param:Attribute(name = "objectid", required = false)
    @field:Attribute(name = "objectid", required = false)
    val id: String,


    @param:Element(name = "name", required = false)
    @field:Element(name = "name", required = false)
    var title: String? = null,

    @param:Element(name = "image", required = false)
    @field:Element(name = "image", required = false)
    val image: String? = null,

    @param:Element(name = "yearpublished", required = false)
    @field:Element(name = "yearpublished", required = false)
    val year: String? = null
)