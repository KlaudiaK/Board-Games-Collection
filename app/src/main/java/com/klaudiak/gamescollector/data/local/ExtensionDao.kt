package com.example.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickandmorty.data.entities.Extension
import kotlinx.coroutines.flow.Flow

@Dao
interface ExtensionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(extension: Extension)

    @Delete
    suspend fun delete(extension: Extension)

    @Query("DELETE FROM extensions")
    suspend fun deleteAllExtensions()

    @Query("SELECT * FROM extensions")
    fun getAllExtensionsItems(): LiveData<List<Extension>>

    @Query("SELECT COUNT(*) FROM extensions")
    fun countAll(): LiveData<Int>

}