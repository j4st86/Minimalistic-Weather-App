package com.example.minimalisticweatherapp.retrofit

data class Weather (
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val humidity: Int
)