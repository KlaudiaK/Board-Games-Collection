package com.boardgamecollector.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = false) @NotNull val id: String,
    @ColumnInfo(name = "game_title") val name: String?,
    // @ColumnInfo(name = "original_game_title") val originalName: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "rating") val rating: Int = 0
)