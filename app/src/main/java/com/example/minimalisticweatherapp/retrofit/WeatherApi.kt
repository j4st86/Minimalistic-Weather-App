package com.example.minimalisticweatherapp.retrofit

import retrofit2.http.GET

interface WeatherApi {
    @GET("/data/2.5/weather?lat=44.34&lon=10.99&appid=4952f57884bddceab6b299e99f263f07")
    suspend fun getWeather(): WeatherData
}