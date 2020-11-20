package com.example.watcha

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcha.api.DailyBoxOffice
import com.example.watcha.api.Movie

class MyViewModel :ViewModel() {
    private val mainMovieLiveList=MutableLiveData<List<DailyBoxOffice>>()
    val mainMovieList= mutableListOf<DailyBoxOffice>()
    private val subMoveLiveList= MutableLiveData<List<Movie>>()
    val subMoveList= mutableListOf<Movie>()
    private var loadingCounter = MutableLiveData<Int>().apply {
        value=0
    }
    fun addMainMovie(daily : DailyBoxOffice){
        mainMovieList.add(daily)
        mainMovieLiveList.value=mainMovieList
    }
    fun addSubMovie(movie : Movie){
        subMoveList.add(movie)
        subMoveLiveList.value=subMoveList
    }
    fun getLoadCounter(): MutableLiveData<Int> {
        return loadingCounter
    }
    fun cnt(){
        loadingCounter.value= loadingCounter.value?.plus(1)
    }
}