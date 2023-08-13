package com.example.minimalisticweatherapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appID: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
    ): Call<WeatherResponse>
}