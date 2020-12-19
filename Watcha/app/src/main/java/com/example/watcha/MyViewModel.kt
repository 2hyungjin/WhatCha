package com.example.watcha

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcha.data.MovieEntity

class MyViewModel : ViewModel() {
    private var savedMovieList= MutableLiveData<List<MovieEntity>>()
    private var loadingCounter = MutableLiveData<Int>().apply {
        value = 0
    }
    fun loadData(list:List<MovieEntity>){
        savedMovieList.postValue(list)
    }
    fun getSavedList():MutableLiveData<List<MovieEntity>>{
        return savedMovieList
    }
    fun getSavedData(): List<MovieEntity>? {
        return savedMovieList.value
    }
    fun getLoadCounter(): MutableLiveData<Int> {
        return loadingCounter
    }

    fun cnt() {
        loadingCounter.value = loadingCounter.value?.plus(1)
    }

}