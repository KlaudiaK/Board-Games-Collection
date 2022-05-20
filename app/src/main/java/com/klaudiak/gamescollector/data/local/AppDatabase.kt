package com.example.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boardgamecollector.data.entities.GameEntity
import com.example.rickandmorty.data.entities.Extension
import com.example.rickandmorty.data.entities.Info

@Database(entities = [GameEntity::class, Extension::class, Info::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun extensionDao() : ExtensionDao
    abstract fun infoDao(): InfoDao
    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "app_database")
                .fallbackToDestructiveMigration()
                .build()
    }

}