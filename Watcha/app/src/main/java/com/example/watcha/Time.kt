package com.example.watcha

import java.text.SimpleDateFormat
import java.util.*

class Time {
    val now=System.currentTimeMillis() // 지금 시간을 Long 형식으로 가져옴
    val date= Date(now) //Date로 캐스팅
    val dateFormat= SimpleDateFormat("yyyyMMdd")
    val dateNow=dateFormat.format(date)
    fun getdateNow()=(dateNow.toInt()-1).toString()
    fun getdateAYearAgo(year: Int)=(dateNow.toInt()-(10000*year)).toString()

}