package com.minimalisticweatherapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var BASE_URL: String = "http://api.weatherapi.com/v1/"

object RetrofitClient {
    private fun createInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createInterceptor())
            .build()
    }

    val retrofitAPI: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}