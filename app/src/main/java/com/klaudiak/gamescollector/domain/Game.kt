package com.klaudiak.gamescollector.domain


data class Game(
    val id: String,
    val name: String?,
    val released: String? = "",
    val image: String?,
    val rating: String? = "0"
)


data class Id(
    val id: String?
)

data class Username(
 val user: String?
)

data class GameInfo(
 val username: String?,
 val lastSync: String?
)