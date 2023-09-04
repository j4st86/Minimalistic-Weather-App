package com.minimalisticweatherapp

import com.minimalisticweatherapp.model.WeatherResponse
import com.minimalisticweatherapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : WeatherMain.Model {

    private val retrofit = RetrofitClient.retrofitAPI

    override fun fetchWeatherData(
        latitude: String?,
        longitude: String?,
        callback: (WeatherResponse?, Throwable?) -> Unit
    ) {
        val call = retrofit.getWeather(
            latitude, longitude,
            BuildConfig.OPEN_WEATHER_API_KEY, "metric", "en"

        )
        // TODO Replace API key to gradle strings
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    callback(weatherResponse, null)
                } else {
                    callback(null, Exception("Weather request failed"))
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}