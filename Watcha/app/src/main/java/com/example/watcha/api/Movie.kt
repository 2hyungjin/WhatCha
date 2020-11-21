package com.example.watcha.api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val boxOfficeResult: BoxOfficeResult
):Parcelable