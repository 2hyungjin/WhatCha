package com.example.watcha

import androidx.room.Room
import com.example.watcha.data.BASE_URL
import com.example.watcha.data.MovieDatabase
import com.example.watcha.data.MovieRetrofit
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModule= module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieRetrofit::class.java)
    }
    viewModel {
        MyViewModel()
    }
    single {
       MovieDatabase.getInstance(androidContext())
    }

}