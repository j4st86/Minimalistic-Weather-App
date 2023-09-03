package com.minimalisticweatherapp

import com.minimalisticweatherapp.model.LocationModel

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
    private val locationData: LocationModel
) : WeatherMain.Presenter {
    override fun start() {
        model.fetchWeatherData(
            locationData.latitude,
            locationData.longitude
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
}