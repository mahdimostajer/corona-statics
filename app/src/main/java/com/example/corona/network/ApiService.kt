package com.example.corona.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CoronaApi {
    @GET("countries")
    suspend fun getCountries(
        @Query("sort") sort: String,
        @Query("yesterday") yesterday: Boolean
    ): List<Country>

    @GET("countries/{countryName}")
    suspend fun getCountry(
        @Path("countryName") countryName: String,
        @Query("yesterday") yesterday: Boolean = true,
        @Query("strict") strict: Boolean = true,
    ): Country

    @GET("historical/all")
    suspend fun getHistory(): History
}
