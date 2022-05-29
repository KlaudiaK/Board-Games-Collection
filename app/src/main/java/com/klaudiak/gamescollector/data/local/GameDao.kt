package com.klaudiak.gamescollector.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.klaudiak.gamescollector.data.entities.GameEntity

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


    @Query("SELECT * FROM games WHERE rating != 'Not Ranked' ORDER BY rating*1 ASC")
    fun getGamesSortedByRating(): List<GameEntity>


    @Query("SELECT rating FROM games WHERE id = :id")
    fun getRankingPosition(id: String): String
}



