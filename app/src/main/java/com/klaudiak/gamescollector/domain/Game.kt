package com.klaudiak.gamescollector.domain


data class Game(
val id: String,
 val name: String?,
val description: String?,
val released: String?,
 val image: String?,
 val rating: Int = 0
)


data class Id(
 val id: String?
)