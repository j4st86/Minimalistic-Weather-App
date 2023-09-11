package com.minimalisticweatherapp

import com.minimalisticweatherapp.retrofit.RetrofitClient
import com.minimalisticweatherapp.retrofit.model.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : WeatherMain.Model {

    private val retrofit = RetrofitClient.retrofitAPI

    override fun fetchWeatherData(
        userLocation: String?,
        callback: (CurrentWeatherResponse?, Throwable?) -> Unit
    ) {
        val call = retrofit.getCurrentWeather(
            BuildConfig.WEATHER_API_KEY,
            userLocation
        )
        call.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    callback(weatherResponse, null)
                } else {
                    callback(null, Exception("Weather request failed"))
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}