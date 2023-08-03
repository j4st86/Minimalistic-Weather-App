package com.example.minimalisticweatherapp.retrofit

import com.google.gson.annotations.SerializedName

data class WindData(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
)