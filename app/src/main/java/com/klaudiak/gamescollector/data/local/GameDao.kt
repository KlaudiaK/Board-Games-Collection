package com.example.rickandmorty.data.local

import androidx.room.*
import com.boardgamecollector.data.entities.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(games: GameEntity)


    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun delete(gameId: String)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT * FROM games")
    fun getAllGameItems(): List<GameEntity>

    @Query("SELECT COUNT(*) FROM games")
    fun countAll(): Flow<Int>

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: String): GameEntity
}



