package com.klaudiak.gamescollector.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "extensions")
data class ExtensionEntity(

    @PrimaryKey(autoGenerate = true) val extensionid: Long?,
    @ColumnInfo val id: String,
    @ColumnInfo(name = "extension_title") val name: String?,
    @ColumnInfo(name = "released") val released: String?,
    @ColumnInfo(name = "image") val image: String?,
)