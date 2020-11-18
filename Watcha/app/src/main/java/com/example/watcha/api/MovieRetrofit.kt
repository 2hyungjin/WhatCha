package com.example.watcha.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRetrofit {
    @GET("searchDailyBoxOfficeList.json?key=$KEY")
    fun getDailyList(
        @Query("targetDt")targetDt:String
    ):Call<Movie>
}