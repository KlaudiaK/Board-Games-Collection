package com.klaudiak.gamescollector.data.remote.reponses


import android.media.Image
import android.net.Uri
import androidx.annotation.AttrRes
import com.klaudiak.gamescollector.domain.Game
import org.simpleframework.xml.*
import java.net.URL


@Root(name = "items", strict = false)
data class UserGamesResponse (
    @param:ElementList(inline = true, required = false)
    @get:ElementList(name= "items", required = false)
    val  list: List<Item>? = null,

    @field:Attribute(name = "totalitems", required = false)
    @param:Attribute(name = "totalitems", required = false)
    val id: String? = null,



    @field:Attribute(name = "termsofuse", required = false)
    @param:Attribute(name = "termsofuse", required = false)
    val termsOfUse: String? = null,

    @field:Attribute(name = "pubdate", required = false)
    @param:Attribute(name = "pubdate", required = false)
    val pubdate: String? = null,

) {
    @Root(name = "item", strict = false)
    data class Item(

        @param:Attribute(name = "objectid")
        @get:Attribute(name = "objectid")
        val id: String? = null

        )
}

@Root(name = "item", strict = false)
data class Article  @JvmOverloads constructor(
    /**
     * @return the title
     */
    /**
     * @param title the title to set
     */
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    /**
     * @return the link
     */
    /**
     * @param link the link to set
     */
    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    /**
     * @return the description
     */
    /**
     * @param description the description to set
     */
    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String? = null
)
@Root(name = "items", strict = false)
data class Feed (
    @field:ElementList(name = "item", inline = true)
    @param:ElementList(name = "item", inline = true)
    var item: ArrayList<ElementItem>? = null
)

@Root(name = "item", strict = false)
data class ElementItem(

    /**
     * @return the channelTitle
     */
    /**
     * @param channelTitle the channelTitle to set
     */
    @field:Attribute(name = "type", required = false)
    @param:Attribute(name = "type",required = false)
    var type: String ="",

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


    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)

    var description: String? = null,



    @field:Element(name = "yearpublished", required = false)
    @param:Element(name = "yearpublished", required = false)
    var year: YearPublished? = null,

    @field:Element(name = "statistics", required = false)
    @param:Element(name = "statistics", required = false)
    var statistics: Statistics? = null,



)
@Element(name = "name", required = false)
data class Primary(

    @param:Attribute(name = "type", required = false)
    @get:Attribute(name = "type", required = false)
    val type: String? = null,

    @param:Attribute(name = "sortindex", required = false)
    @get:Attribute(name = "sortindex", required = false)
    val sortindex: String ? = null,

    @param:Attribute(name = "value", required = false)
    @get:Attribute(name = "value", required = false)
    var elementPrimaryName: String? = null
)

@Element(name = "yearpublished", required = false)
data class YearPublished(

    @param:Attribute(name = "value", required = false)
    @get:Attribute(name = "value", required = false)
    val year: String? = null

)
@Root(name = "statistics", strict = false)
data class Statistics(



    @param:ElementList(name = "ranks", required = false, inline = true)
    @get:ElementList(name = "ranks", required = false, inline = true)
    @field:Path("ratings/ranks")
    @param:Path("ratings/ranks")
    val stats: Ranks = Ranks()

)


@ElementList(name = "ranks", required = false)
data class Ranks(
    @param:Attribute(name = "type", required = false)
    @get:Attribute(name = "type", required = false)
    val pos: String = ""


)



class ThingResponse {
    @ElementList(name = "items", inline = true, required = false)
    var games: List<Game>? = null
}