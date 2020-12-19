package com.example.watcha.data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoxOfficeResult(
    val boxofficeType: String,
    val dailyBoxOfficeList: List<DailyBoxOffice>,
    val showRange: String
):Parcelable