package com.example.corona.network

import java.math.BigInteger

data class Country(
    val updated: BigInteger,
    val country: String,

    val countryInfo: CountryInfo,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val todayRecovered: Int,
    val active: Int,
    val critical: Int,
    val casesPerOneMillion: Int,
    val deathsPerOneMillion: Double,
    val tests: Int,
    val testsPerOneMillion: Double,
    val population: Int,
    val continent: String,
    val oneCasePerPeople: Int,
    val oneDeathPerPeople: Int,
    val oneTestPerPeople: Int,
    val undefined: Double,
    val activePerOneMillion: Double,
    val recoveredPerOneMillion: Double,
    val criticalPerOneMillion: Double
)
