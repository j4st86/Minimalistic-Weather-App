package com.example.minimalisticweatherapp.retrofit

import com.google.gson.annotations.SerializedName

data class CoordData(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)