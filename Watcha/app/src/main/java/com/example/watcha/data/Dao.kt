package com.example.watcha.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Dao
@Dao
interface Dao {
    @Insert
    suspend fun insertMovie(movie: MovieEntity)
    @Delete
    suspend fun deleteMovie(movie:MovieEntity)
    @Query("SELECT * FROM MovieEntity")
    suspend fun selectAll():List<MovieEntity>
}