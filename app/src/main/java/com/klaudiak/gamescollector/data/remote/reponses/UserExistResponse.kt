package com.klaudiak.gamescollector.data.remote.reponses

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.simpleframework.xml.*
import org.simpleframework.xml.core.Persister


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


