package com.minimalisticweatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("main")
    val mainWeatherData: MainData,
    @SerializedName("weather")
    val weather: List<WeatherDescription>
)

data class MainData(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double
)

data class WeatherDescription(
    @SerializedName("description")
    val description: String
)