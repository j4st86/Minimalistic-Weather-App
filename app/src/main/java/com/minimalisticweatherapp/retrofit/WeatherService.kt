package com.minimalisticweatherapp.retrofit

import com.minimalisticweatherapp.retrofit.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService { //TODO peredelat'
    @GET("forecast.json?")
    fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") location: String?,
        @Query("days") days: String = "5"
    ): Call<WeatherResponse>
}