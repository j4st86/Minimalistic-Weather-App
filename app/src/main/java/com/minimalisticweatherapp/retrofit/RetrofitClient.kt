package com.minimalisticweatherapp.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var WEATHER_API_BASE_URL: String = "https://api.weatherapi.com/v1/"
private var OPEN_API_BASE_URL: String = "https://api.open-meteo.com/v1/"

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

    val retrofitWeatherAPI: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    val retrofitForecastAPI: ForecastService by lazy {
        Retrofit.Builder()
            .baseUrl(OPEN_API_BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastService::class.java)
    }
}