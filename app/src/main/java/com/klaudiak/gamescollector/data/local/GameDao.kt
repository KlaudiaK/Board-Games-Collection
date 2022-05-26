package com.klaudiak.gamescollector.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.klaudiak.gamescollector.data.entities.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Insert
    suspend fun insert(games: GameEntity)


    @Query("DELETE FROM games WHERE id = :gameId")
    suspend fun delete(gameId: String)

    @Query("DELETE FROM games")
    suspend fun deleteAllGames()

    @Query("SELECT * FROM games")
    fun getAllGameItems(): List<GameEntity>

    @Query("SELECT COUNT(*) FROM games")
    fun countAll(): Int

    @Query("SELECT * FROM games WHERE id = :id")
    fun getGameById(id: String): GameEntity


    @Query("SELECT * FROM games ORDER BY released")
    fun getGamesSortedByReleaseYear(): List<GameEntity>

    @Query("SELECT * FROM games ORDER BY game_title")
    fun getGamesSortedByTitle(): List<GameEntity>


    @Query("SELECT * FROM games ORDER BY rating")
    fun getGamesSortedByRating(): List<GameEntity>
}



