package com.minimalisticweatherapp

import com.minimalisticweatherapp.retrofit.model.WeatherResponse

interface WeatherMain {
    interface View {
        fun showWeatherData(
            temperatureCelsius: String,
            temperatureFahrenheit: String,
            weatherIcon: Int
        )

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
    }

    interface Model {
        fun fetchWeatherData(
            userLocation: String?,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )
    }
}