package com.example.watcha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(var title:String, var imgUrl:String,var date:String){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}