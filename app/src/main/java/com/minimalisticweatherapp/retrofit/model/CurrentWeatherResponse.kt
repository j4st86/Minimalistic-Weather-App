package com.minimalisticweatherapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("location")
    val weatherLocationData: LocationData,
    @SerializedName("current")
    val weatherCurrentData: CurrentWeatherData
)

data class LocationData(
    @SerializedName("name")
    val cityName: String
)

data class CurrentWeatherData(
    @SerializedName("temp_c")
    val tempCurrentCelsius: Double,
    @SerializedName("temp_f")
    val tempCurrentFahrenheit: Double,
    @SerializedName("wind_mph")
    val windCurrentSpeedMph: Double,
    @SerializedName("wind_kph")
    val windCurrentSpeedKph: Double,
    @SerializedName("is_day")
    val isDayCode: Int,
    @SerializedName("condition")
    val weatherCurrentCondition: CurrentWeatherCondition,
)

data class CurrentWeatherCondition(
    @SerializedName("text")
    val weatherConditionDescription: String,
    @SerializedName("code")
    val weatherConditionCode: Int
)