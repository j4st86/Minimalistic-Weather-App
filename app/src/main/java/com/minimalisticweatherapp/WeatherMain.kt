package com.minimalisticweatherapp

import android.location.Location
import com.minimalisticweatherapp.retrofit.model.WeatherResponse

interface LoadingMain {
    interface View {
        fun navigateToMainActivity(location: Location)
        fun requestLocationPermission()
        fun finishActivity()
    }

    interface Presenter {
        fun start()
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        )
    }

    interface Model {
        fun getCurrentLocation()
    }
}

interface WeatherMain {
    interface View {
        fun showCurrentWeatherData(
            temperatureCelsius: String,
            temperatureFahrenheit: String,
            weatherIcon: Int
        )

        fun showMainImages(
            settingIcon: Int
        )

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun start()
    }

    interface Model {
        fun fetchWeatherData(
            userLocation: String?,
            callback: (WeatherResponse?, Throwable?) -> Unit
        )
    }
}

interface SettingsMain {
    interface View {
        fun showInterface(
            backImage: Int,
            activityName: String
        )
    }

    interface Presenter {
        fun start()
    }

    interface Model {

    }
}