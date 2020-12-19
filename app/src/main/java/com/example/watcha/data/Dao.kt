package com.example.watcha.data

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)
    @Delete
    suspend fun deleteMovie(movie:MovieEntity)
    @Query("SELECT * FROM MovieEntity")
    suspend fun selectAll():List<MovieEntity>
}