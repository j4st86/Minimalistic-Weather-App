package com.minimalisticweatherapp.presenters

import com.minimalisticweatherapp.R
import com.minimalisticweatherapp.WeatherMain
import com.minimalisticweatherapp.extensions.directionTranslate
import com.minimalisticweatherapp.extensions.weatherIconByCode
import com.minimalisticweatherapp.retrofit.LocationModel

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
    private val locationData: LocationModel
) : WeatherMain.Presenter {
    override fun start() {
        view.showMainImages(
            settingIcon = R.drawable.ic_settings,
            maxTempIcon = R.drawable.ic_max_temp,
            minTempIcon = R.drawable.ic_min_temp,
            windSpeedIcon = R.drawable.ic_wind,
            pressureIcon = R.drawable.ic_pressure,
            humidityIcon = R.drawable.ic_humidity
        )

        model.fetchWeatherData(
            locationData.userLocation
        ) { weatherResponse, error ->
            if (weatherResponse != null) {
                view.showCurrentWeatherData(
                    cityName = weatherResponse.weatherLocationData.cityName,
                    temperatureCurrent = weatherResponse.weatherCurrentData.tempCurrentCelsius.toInt()
                        .toString() + "°",
                    windSpeedCurrent = (weatherResponse.weatherCurrentData.windCurrentSpeedMph * 0.278).toInt()
                        .toString() + " мс, " + directionTranslate(weatherResponse.weatherCurrentData.windDirection),
                    pressureCurrent = (weatherResponse.weatherCurrentData.pressureCurrent * 0.7501).toInt()
                        .toString() + " мм. рт. ст.",
                    humidityCurrent = weatherResponse.weatherCurrentData.humidityCurrent.toString() + " %",
                    weatherIcon = weatherIconByCode(
                        weatherResponse.weatherCurrentData.weatherCurrentCondition.weatherConditionCode,
                        weatherResponse.weatherCurrentData.isDayCode
                    )
                )
            } else {
                view.showErrorMessage(error?.message ?: "Failed to retrieve weather data")
            }
        }

        model.fetchForecastData(
            locationData.latitude, locationData.longitude
        ) { forecastResponse, error ->
            if (forecastResponse != null) {
                view.showForecastWeatherData(
                    temperatureMaximum = forecastResponse.dailyForecastData.dailyMaxTemp[0].toInt()
                        .toString() + "°",
                    temperatureMinimum = forecastResponse.dailyForecastData.dailyMinTemp[0].toInt()
                        .toString() + "°"
                )
            } else {
                view.showErrorMessage(error?.message ?: "Failed to retrieve forecast data")
            }
        }
    }
}