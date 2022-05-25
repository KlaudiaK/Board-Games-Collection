package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "items")
data class GameResponse (
    //@param:ElementList(inline = true)
    //@get:ElementList(name= "items")
    @field:ElementList(name = "items", inline = true)
    val  list: List<Item>? = ArrayList()

){
    @Root(name = "item", strict = false)
    data class Item(

        //@param:Attribute(name = "id")
        //@get:Attribute(name = "id")
        @field:Attribute(name = "id")
        val id: String? = null,



        //@param:Element(name = "name")
        // @get:Element(name = "name")
        @field:Element(name = "name")
        var title: String? = null,

        @field:Element(name = "description")
        var description: String? = null,


        @field:Element(name = "image")
        val image: String? = null,

        @field:Element(name = "yearpublished")
        val year: String? = null,

        val rank: Ranks.Rank?


    )

    @Root(name= "stats")
    data class Stats (
        @field:Element(name = "rating")
        val rating: String? = null
    )

    @Root(name = "ranks")
    data class Ranks(
        @field:ElementList(name = "ranks", inline = true)
        val rankList: List<Rank>
    ){

        @Root(name = "rank")
        data class Rank(
            @field:Attribute(name = "type")
            val type: String = "subtype",

            @field:Attribute(name = "value")
            val rankPos: Int,

            @field:Attribute(name = "name")
            val name: String = "boardgame"
        )
    }

}
