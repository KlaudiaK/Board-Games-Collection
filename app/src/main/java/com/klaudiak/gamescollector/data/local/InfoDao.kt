package com.klaudiak.gamescollector.data.local

import androidx.room.*
import com.klaudiak.gamescollector.data.entities.Info
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appInfo: Info)

    @Delete
    suspend fun delete(appInfo: Info)

    @Query("SELECT last_sync FROM info")
    fun getLastSyncDate(): Flow<String>

    @Query("SELECT username FROM info")
    fun getUsername(): Flow<String>

    @Query("UPDATE info SET last_sync=:syncDate")
    fun updateSyncDate(syncDate: String)
}