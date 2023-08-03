package com.example.minimalisticweatherapp.retrofit

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: CloudsData,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: CoordData,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val mainWeatherData: MainWeatherData,
    @SerializedName("name")
    val name: String,
    @SerializedName("rain")
    val rain: RainData,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: WindData
)