package com.minimalisticweatherapp.retrofit

import com.minimalisticweatherapp.retrofit.model.ForecastResponse
import com.minimalisticweatherapp.retrofit.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast.json?")
    fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") location: String?,
        @Query("days") days: String = "1"
    ): Call<WeatherResponse>
}

interface ForecastService {
    @GET("forecast?")
    fun getForecastWeather(
        @Query("q") location: String?,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "GMT",
        @Query("forecast_days") forecastDays: String = "14"
    ): Call<ForecastResponse>
}