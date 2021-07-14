package com.example.corona.utils

fun formatNumber(number: Int): String {
    if (number > 1000000) {
        val newNumber = number / 1000000
        return "$newNumber M"
    } else if (number > 1000) {
        val newNumber = number / 1000
        return "$newNumber K"
    } else return number.toString()
}