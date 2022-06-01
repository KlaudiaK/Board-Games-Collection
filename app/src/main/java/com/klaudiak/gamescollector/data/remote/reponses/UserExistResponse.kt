package com.klaudiak.gamescollector.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "user", strict =false)
data class UserExistResponse(

    @field:Attribute(name = "id", required = false)
    @param:Attribute(name = "id", required = false)
    val id: String? = null,

    @field:Attribute(name = "name", required = false)
    @param:Attribute(name = "name", required = false)
    val name: String? = null,

    @field:Attribute(name = "termsofuse", required = false)
    @param:Attribute(name = "termsofuse", required = false)
    val termsOfUse: String? = null,
    @field:Element(name = "firstname", required = false)
    @param:Element(name = "firstname", required = false)
    val firstname: String? = null

)


