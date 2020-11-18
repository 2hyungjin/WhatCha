package com.example.watcha.api


import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    val boxofficeType: String,
    val dailyBoxOfficeList: List<DailyBoxOffice>,
    val showRange: String
)