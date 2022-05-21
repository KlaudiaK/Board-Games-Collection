package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items")
data class GameResponse (
    @param:ElementList(inline = true)
    @get:ElementList(name= "items")
    val  list: List<Item>? = null

){
    @Root(name = "item", strict = false)
    data class Item(

        @param:Attribute(name = "objectid")
        @get:Attribute(name = "objectid")
        val id: String? = null,



        @param:Element(name = "name")
        @get:Element(name = "name")
        var title: String? = null,

        @param:Element(name = "description")
        @get:Element(name = "description")
        var description: String? = null,

        @param:Element
        @get:Element(name = "image")
        val image: String? = null,

        @param:Element(name = "yearpublished")
        @get:Element(name = "yearpublished")
        val year: String? = null,

        val rank: Ranks.Rank?
    )

    @Root(name= "stats")
    data class Stats (
        @param:Element(name = "rating")
        val rating: String? = null
    )

    @Root(name = "ranks")
    data class Ranks(
        @param:ElementList(inline = true)
        @get:ElementList(name = "ranks")
        val rankList: List<Rank>
    ){

        @Root(name = "rank")
        data class Rank(
            @param:Attribute(name = "type")
            val type: String = "subtype",

            @param:Attribute(name = "value")
            @get:Attribute(name="value")
            val rankPos: Int,

            @param:Attribute(name = "name")
            val name: String = "boardgame"
        )
    }

}