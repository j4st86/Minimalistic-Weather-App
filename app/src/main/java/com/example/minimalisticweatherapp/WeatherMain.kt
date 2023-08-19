package com.example.minimalisticweatherapp

import com.example.minimalisticweatherapp.retrofit.WeatherResponse

interface WeatherMain {
    interface View {
        fun showWeatherData(temperature: String, temperatureMax: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
        fun onRequestPermissionResult(requestCode: Int, grantResult: IntArray)
    }

    interface Model {
        fun fetchWeatherData(
            latitude: String,
            longitude: String,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )
    }
}