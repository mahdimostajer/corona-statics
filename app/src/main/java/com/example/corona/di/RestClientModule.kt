package com.example.corona.di

import com.example.corona.network.CoronaApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestClientModule {
    companion object {
        private const val BASE_URL = "https://corona.lmao.ninja/v2/"
    }

    @Provides
    @Singleton
    internal fun provideClient(): OkHttpClient {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BASE_URL).build()
    }

    @Provides
    @Singleton
    internal fun provideCoronaApi(retrofit: Retrofit) = retrofit.create(CoronaApi::class.java)
}