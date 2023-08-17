package com.example.minimalisticweatherapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class WeatherPresenter(
    private val view: WeatherConstructor.View,
    private val model: WeatherConstructor.Model
) : WeatherConstructor.Presenter {
    override fun start() {
        if (ContextCompat.checkSelfPermission(
                view as Context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            model.getCurrentLocation { location ->
                if (location != null) {
                    model.fetchWeatherData(
                        location.latitude.toString(),
                        location.longitude.toString()
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
        } else {
            ActivityCompat.requestPermissions(
                view as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION
            )
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