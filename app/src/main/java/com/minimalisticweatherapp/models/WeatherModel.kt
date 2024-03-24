package com.minimalisticweatherapp.models

import com.minimalisticweatherapp.BuildConfig
import com.minimalisticweatherapp.WeatherMain
import com.minimalisticweatherapp.retrofit.RetrofitClient
import com.minimalisticweatherapp.retrofit.model.ForecastResponse
import com.minimalisticweatherapp.retrofit.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : WeatherMain.Model {

    private val retrofitCurrent = RetrofitClient.retrofitWeatherAPI
    private val retrofitForecast = RetrofitClient.retrofitForecastAPI

    override fun fetchWeatherData(
        userLocation: String?,
        callback: (WeatherResponse?, Throwable?) -> Unit
    ) {
        val call = retrofitCurrent.getCurrentWeather(
            BuildConfig.WEATHER_API_KEY,
            userLocation
        )
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    callback(weatherResponse, null)
                } else {
                    callback(null, Exception("Error: Weather request failed"))
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }

    override fun fetchForecastData(
        latitude: String?, longitude: String?,
        callback: (ForecastResponse?, Throwable?) -> Unit
    ) {
        val call = retrofitForecast.getForecastWeather(
            latitude, longitude
        )
        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (response.isSuccessful) {
                    val forecastResponse = response.body()
                    callback(forecastResponse, null)
                } else {
                    callback(null, Exception("Error: Forecast request failed"))
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}