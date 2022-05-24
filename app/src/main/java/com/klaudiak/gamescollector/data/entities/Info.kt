package com.klaudiak.gamescollector.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "info")
data class Info(
    @PrimaryKey @NotNull val username: String,
    @ColumnInfo(name = "last_sync") val lastSyncDate: String?
)