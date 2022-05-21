package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "items")
data class ExtensionResponse (
    @param:ElementList(inline = true)
    @get:ElementList(name= "items")
    val  list: List<Item>? = null

) {
    @Root(name = "item", strict = false)
    data class Item(

        @param:Attribute(name = "objectid")
        @get:Attribute(name = "objectid")
        val id: String? = null,


        @param:Element(name = "name")
        @get:Element(name = "name")
        var title: String? = null,

        @param:Element
        @get:Element(name = "image")
        val image: String? = null,

        @param:Element(name = "yearpublished")
        @get:Element(name = "yearpublished")
        val year: String? = null


    )
}