package com.example.minimalisticweatherapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.minimalisticweatherapp.model.LocationModel

class WeatherPresenter(
    private val view: WeatherMain.View,
    private val model: WeatherMain.Model,
) : WeatherMain.Presenter {

    private lateinit var locationData: LocationModel
    override fun start() {
        if ((view as Context?)?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            model.getCurrentLocation { location ->
                if (location != null) {
                    locationData =
                        LocationModel(location.latitude.toString(), location.longitude.toString())
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
        } else {
            (view as Activity?)?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION
                )
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