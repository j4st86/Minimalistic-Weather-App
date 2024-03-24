package com.minimalisticweatherapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("daily")
    val dailyForecastData: DailyForecastData
)

data class DailyForecastData(
    @SerializedName("temperature_2m_max")
    val dailyMaxTemp: List<Double>,
    @SerializedName("temperature_2m_min")
    val dailyMinTemp: List<Double>
)