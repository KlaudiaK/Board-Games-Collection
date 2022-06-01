package com.klaudiak.gamescollector.domain

data class Extension(
    val id: String,
    val name: String?,
    val released: String? = "",
    val image: String?
)
