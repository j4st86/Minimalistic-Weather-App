package com.example.minimalisticweatherapp.retrofit

import com.google.gson.annotations.SerializedName

data class CloudsData(
    @SerializedName("all")
    val all: Int
)