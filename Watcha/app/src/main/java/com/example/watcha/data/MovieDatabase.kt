package com.example.watcha.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class],version = 2)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun getDao(): Dao
    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "db.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}