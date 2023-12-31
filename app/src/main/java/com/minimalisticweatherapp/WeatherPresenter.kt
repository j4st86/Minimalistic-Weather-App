package com.minimalisticweatherapp

import com.minimalisticweatherapp.extensions.weatherIconByCode
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
                view.showWeatherData(
                    temperatureCelsius = weatherResponse.weatherCurrentData.tempCurrentCelsius.toString(),
                    temperatureFahrenheit = weatherResponse.weatherCurrentData.tempCurrentFahrenheit.toString(),
                    weatherIcon = weatherIconByCode(
                        weatherResponse.weatherCurrentData.weatherCurrentCondition.weatherConditionCode,
                        weatherResponse.weatherCurrentData.isDayCode
                    )
                )
            } else {
                view.showErrorMessage(error?.message ?: "Failed to retrieve weather data")
            }
        }
    }
}