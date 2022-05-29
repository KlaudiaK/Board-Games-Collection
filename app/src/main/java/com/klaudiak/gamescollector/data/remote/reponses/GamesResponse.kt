package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.net.URL


@Root(name = "items", strict = false)
data class GamesResponse (
    @field:ElementList(name = "item", inline = true)
    @param:ElementList(name = "item", inline = true)
    var item: ArrayList<GameItemResponse>? = null
)

@Root(name = "item", strict = false)
data class GameItemResponse(
    @field:Attribute(name = "objectid", required = false)
    @param:Attribute(name = "objectid", required = false)
    var id: String,
    @field:Element(name = "name", required = false)
    @param:Element(name = "name", required = false)
    var title: String? = null,
    @field:Element(name = "thumbnail", required = false)
    @param:Element(name = "thumbnail", required = false)
    var thumbnail: URL? = null,


    @field:Element(name = "image", required = false)
    @param:Element(name = "image", required = false)
    var image: String? = null,
/*

    @field:ElementList(required = false, inline = true)
    @param:ElementList( required = false, inline = true)
    var elementPrimaryName: Primary,


    @param:Attribute(name = "sortindex", required = false)
    @get:Attribute(name = "sortindex", required = false)
    val sortindex: String ? = null,


 */





    @field:Element(name = "yearpublished", required = false)
    @param:Element(name = "yearpublished", required = false)
    var year: String? = null,
/*
    @field:Element(name = "statistics", required = false)
    @param:Element(name = "statistics", required = false)
    var statistics: Statistics? = null,

 */

    @field:Element(name = "stats", required = false)
    @param:Element(name = "stats", required = false)
    var stats: StatsResponse? = null,

    )
