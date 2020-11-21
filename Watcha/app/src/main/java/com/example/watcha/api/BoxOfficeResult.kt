package com.example.watcha.api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoxOfficeResult(
    val boxofficeType: String,
    val dailyBoxOfficeList: List<DailyBoxOffice>,
    val showRange: String
):Parcelable