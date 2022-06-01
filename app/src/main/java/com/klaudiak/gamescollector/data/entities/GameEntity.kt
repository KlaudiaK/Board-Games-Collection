package com.klaudiak.gamescollector.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true) val gameid: Long?,
    @ColumnInfo val id: String,
    @ColumnInfo(name = "game_title") val name: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "rating") val rating: String? = "0"
)