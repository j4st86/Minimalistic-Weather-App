package com.example.minimalisticweatherapp

import android.content.pm.PackageManager

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
    private val locationData: LocationModel
) : WeatherMain.Presenter {
    override fun start() {

        model.fetchWeatherData(
            locationData.latitude,
            locationData.longitude,
        ) { weatherResponse, error ->
            if (weatherResponse != null) {
                val temperature = weatherResponse.mainWeatherData.temp
                val temperatureMax = weatherResponse.mainWeatherData.tempMax
                val temperatureMin = weatherResponse.mainWeatherData.tempMin
                val description = weatherResponse.weather[0].description

                view.showWeatherData(temperature.toString(), temperatureMax.toString())
            } else {
                view.showErrorMessage(error?.message ?: "Error")
            }
        }
    }

    override fun onRequestPermissionResult(requestCode: Int, grantResult: IntArray) {
        if (requestCode == LOCATION_PERMISSION) {
            if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                start()
            } else {
                view.showErrorMessage("Location permission was denied")
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION = 86
    }
}