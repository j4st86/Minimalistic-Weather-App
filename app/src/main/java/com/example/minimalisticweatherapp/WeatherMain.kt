package com.example.minimalisticweatherapp

import com.example.minimalisticweatherapp.model.WeatherResponse

interface WeatherMain {
    interface View {
        fun showWeatherData(temperature: String, temperatureMax: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
    }

    interface Model {
        fun fetchWeatherData(
            latitude: String?,
            longitude: String?,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )
    }
}