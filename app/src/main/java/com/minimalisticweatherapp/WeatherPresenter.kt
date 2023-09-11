package com.minimalisticweatherapp

import com.minimalisticweatherapp.model.LocationModel

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
    private val locationData: LocationModel
) : WeatherMain.Presenter {
    override fun start() {
        model.fetchWeatherData(
            locationData.userLocation
        ) { weatherResponse, error ->
            if (weatherResponse != null) {
                val temperatureCelsius = weatherResponse.weatherCurrentData.tempCurrentCelsius
                val temperatureFahrenheit = weatherResponse.weatherCurrentData.tempCurrentFahrenheit

                view.showWeatherData(
                    temperatureCelsius.toString(),
                    temperatureFahrenheit.toString()
                )
            } else {
                view.showErrorMessage(error?.message ?: "Error")
            }
        }
    }
}