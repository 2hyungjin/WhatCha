package com.example.watcha

import android.app.Application
import android.util.Log
import com.example.watcha.api.Movie
import com.example.watcha.api.MovieRetrofit
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(myModule)
        }
    }

}