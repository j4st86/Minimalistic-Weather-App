package com.example.minimalisticweatherapp.retrofit

import com.google.gson.annotations.SerializedName

data class RainData(
    @SerializedName("'1h'")
    val `1h`: Double
)