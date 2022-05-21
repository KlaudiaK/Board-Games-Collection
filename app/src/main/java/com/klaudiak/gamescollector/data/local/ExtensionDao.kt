package com.klaudiak.gamescollector.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.klaudiak.gamescollector.data.entities.ExtensionEntity

@Dao
interface ExtensionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(extension: ExtensionEntity)

    @Delete
    suspend fun delete(extension: ExtensionEntity)

    @Query("DELETE FROM extensions")
    suspend fun deleteAllExtensions()

    @Query("SELECT * FROM extensions")
    fun getAllExtensionsItems(): LiveData<List<ExtensionEntity>>

    @Query("SELECT COUNT(*) FROM extensions")
    fun countAll(): LiveData<Int>

}