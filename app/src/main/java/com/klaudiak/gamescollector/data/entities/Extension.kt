package com.example.rickandmorty.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "extensions")
data class Extension(
    @PrimaryKey @NotNull val id: String,
    @ColumnInfo(name = "extension_title")val name: String?,
    val released: String?,
    val image: String?
)