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

//TODO https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&daily=temperature_2m_max,temperature_2m_min&timezone=GMT&forecast_days=14