package com.klaudiak.gamescollector.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.klaudiak.gamescollector.data.entities.GameEntity
import com.klaudiak.gamescollector.data.entities.ExtensionEntity
import com.klaudiak.gamescollector.data.entities.Info

@Database(entities = [GameEntity::class, ExtensionEntity::class, Info::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun extensionDao() : ExtensionDao
    abstract fun infoDao(): InfoDao
    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "bgc_db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}