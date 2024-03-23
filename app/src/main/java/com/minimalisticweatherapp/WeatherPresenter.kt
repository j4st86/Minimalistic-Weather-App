package com.minimalisticweatherapp

import com.minimalisticweatherapp.extensions.weatherIconByCode
import com.minimalisticweatherapp.model.LocationModel

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
    private val locationData: LocationModel
) : WeatherMain.Presenter {
    override fun start() {
        view.showMainImages(
            settingIcon = R.drawable.ic_settings,
            maxTempIcon = R.drawable.ic_max_temp,
            minTempIcon = R.drawable.ic_min_temp
        )

        model.fetchWeatherData(
            locationData.userLocation
        ) { weatherResponse, error ->
            if (weatherResponse != null) {
                view.showCurrentWeatherData(
                    cityName = weatherResponse.weatherLocationData.cityName,
                    temperatureCurrent = weatherResponse.weatherCurrentData.tempCurrentCelsius.toInt()
                        .toString() + "°",
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