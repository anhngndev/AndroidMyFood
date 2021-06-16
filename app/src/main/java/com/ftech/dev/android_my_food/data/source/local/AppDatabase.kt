package com.ftech.dev.android_my_food.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ftech.dev.android_my_food.utils.getApplication

@Database(entities = [SearchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        fun getInstance(): AppDatabase {
            val db = Room.databaseBuilder(
                getApplication(),
                AppDatabase::class.java,
                "app.db"
            )
                .allowMainThreadQueries()
                .addMigrations()
                .fallbackToDestructiveMigration()
                .build()

            return db
        }
    }
}

