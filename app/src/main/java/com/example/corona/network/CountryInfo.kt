package com.example.corona.network

data class CountryInfo(
    val _id: Int,
    val iso2: String,
    val iso3: String,
    val lat: Double,
    val long: Double,
    val flag: String
)