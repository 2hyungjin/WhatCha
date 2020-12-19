package com.example.watcha.data


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val boxOfficeResult: BoxOfficeResult
):Parcelable