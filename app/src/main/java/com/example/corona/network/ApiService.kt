package com.example.corona.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://corona.lmao.ninja/v2/"

private val loggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .baseUrl(BASE_URL).build()

interface GitApi {
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
}

object GitApiService {
    val retrofitService: GitApi by lazy {
        retrofit.create(GitApi::class.java)
    }
}