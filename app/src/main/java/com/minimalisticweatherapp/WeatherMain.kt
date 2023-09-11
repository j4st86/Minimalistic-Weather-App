package com.minimalisticweatherapp

import com.minimalisticweatherapp.retrofit.model.CurrentWeatherResponse

interface WeatherMain {
    interface View {
        fun showWeatherData(temperatureCelsius: String, temperatureFahrenheit: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
    }

    interface Model {
        fun fetchWeatherData(
            userLocation: String?,
            callback: (CurrentWeatherResponse?, Throwable?) -> Unit
        )
    }
}