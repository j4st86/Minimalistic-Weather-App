package com.example.minimalisticweatherapp.retrofit

data class WeatherData(
    val base: String,
    val clouds: CloudsData,
    val cod: Int,
    val coord: CoordData,
    val dt: Int,
    val id: Int,
    val main: MainWeatherData,
    val name: String,
    val rain: RainData,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: WindData
)