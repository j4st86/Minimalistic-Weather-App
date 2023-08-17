package com.example.minimalisticweatherapp

import android.location.Location
import com.example.minimalisticweatherapp.retrofit.WeatherResponse

interface WeatherConstructor {
    interface View {
        fun showWeatherData(temperature: String, temperatureMax: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
        fun onRequestPermissionResult(requestCode: Int, grantResult: IntArray)
    }

    interface Model {
        fun getCurrentLocation(callback: (Location?) -> Unit)
        fun fetchWeatherData(
            latitude: String,
            longitude: String,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )
    }
}