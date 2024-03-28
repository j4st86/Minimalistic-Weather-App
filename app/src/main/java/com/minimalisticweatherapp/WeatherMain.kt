package com.minimalisticweatherapp

import com.minimalisticweatherapp.retrofit.model.ForecastResponse
import com.minimalisticweatherapp.retrofit.model.HourWeatherData
import com.minimalisticweatherapp.retrofit.model.WeatherResponse

interface WeatherMain {
    interface View {
        fun showCurrentWeatherData(
            cityName: String,
            temperatureCurrent: String,
            windSpeedCurrent: String,
            pressureCurrent: String,
            humidityCurrent: String,
            weatherIcon: Int
        )

        fun showForecastWeatherData(
            temperatureMaximum: String,
            temperatureMinimum: String
        )

        fun showMainImages(
            settingIcon: Int,
            maxTempIcon: Int,
            minTempIcon: Int,
            windSpeedIcon: Int,
            pressureIcon: Int,
            humidityIcon: Int,
            dayTimeIcon: Int,
            nightTimeIcon: Int
        )

        fun updateWeatherView(
            timeHour: String,
            temperatureHour: String,
            windSpeedHour: String,
            pressureHour: String,
            humidityHour: String,
            weatherIcon: Int
        )

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()

        fun update(
            selectedTime: Int
        )
    }

    interface Model {
        fun fetchWeatherData(
            userLocation: String?,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )

        fun fetchForecastData(
            latitude: String?,
            longitude: String?,
            callback: (ForecastResponse?, Throwable?) -> Unit
        )

        fun getHourWeatherData(
            selectedTime: Int
        ) : HourWeatherData
    }
}

interface SettingsMain {
    interface View {
        fun showInterface(
            backImage: Int
        )
    }

    interface Presenter {
        fun start()
    }

    interface Model {

    }
}