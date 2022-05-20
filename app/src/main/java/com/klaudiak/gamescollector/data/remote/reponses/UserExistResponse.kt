package com.example.rickandmorty.data.remote.reponses

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "user", strict = false)
data class UserExistResponse (

    @param:Attribute(name = "name")
    @get:Attribute(name = "name")
    val name: String? = null

)