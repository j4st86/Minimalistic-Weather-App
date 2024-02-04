package com.minimalisticweatherapp

import com.minimalisticweatherapp.retrofit.RetrofitClient
import com.minimalisticweatherapp.retrofit.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : WeatherMain.Model {

    private val retrofit = RetrofitClient.retrofitAPI

    override fun fetchWeatherData(
        userLocation: String?,
        callback: (WeatherResponse?, Throwable?) -> Unit
    ) {
        val call = retrofit.getCurrentWeather(
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
}

//TODO Result API