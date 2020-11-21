package com.example.watcha

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watcha.api.DailyBoxOffice
import com.example.watcha.api.Movie

class MyViewModel : ViewModel() {

    private var loadingCounter = MutableLiveData<Int>().apply {
        value = 0
    }

    fun getLoadCounter(): MutableLiveData<Int> {
        return loadingCounter
    }

    fun cnt() {
        loadingCounter.value = loadingCounter.value?.plus(1)
    }

}