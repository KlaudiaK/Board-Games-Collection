package com.klaudiak.gamescollector.data.local

import androidx.room.*
import com.klaudiak.gamescollector.data.entities.ExtensionEntity

@Dao
interface ExtensionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(extensions: List<ExtensionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(extension: ExtensionEntity)

    @Delete
    suspend fun delete(extension: ExtensionEntity)

    @Query("DELETE FROM extensions")
    suspend fun deleteAllExtensions()

    @Query("SELECT * FROM extensions")
    fun getAllExtensionsItems(): List<ExtensionEntity>

    @Query("SELECT COUNT(*) FROM extensions")
    fun countAll(): Int

    @Query("SELECT * FROM extensions ORDER BY released")
    fun getExtensionSortedByReleaseYear(): List<ExtensionEntity>

    @Query("SELECT * FROM extensions ORDER BY extension_title")
    fun getExtensionSortedByTitle(): List<ExtensionEntity>


}