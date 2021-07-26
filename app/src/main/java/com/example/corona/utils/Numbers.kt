package com.example.corona.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun formatNumber(number: Int): String {
    return when {
        number >= 1000000 -> "${number / 1000000} M"
        number >= 1000 -> "${number / 1000} K"
        else -> number.toString()
    }
}

@SuppressLint("SimpleDateFormat")
fun distanceBetweenDates(entry:String): Float {
    val date = SimpleDateFormat("MM/dd/yy").parse(entry)
    val diff: Long = Date().time - date.time
    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS).toFloat()
}