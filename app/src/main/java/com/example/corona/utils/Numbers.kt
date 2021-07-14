package com.example.corona.utils

fun formatNumber(number: Int): String {
    return when {
        number > 1000000 -> "${number / 1000000} M"
        number > 1000 -> "${number / 1000} K"
        else -> number.toString()
    }
}