package com.klaudiak.gamescollector.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "info")
data class Info(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo val username: String,
    @ColumnInfo(name = "last_sync") val lastSyncDate: String?
)