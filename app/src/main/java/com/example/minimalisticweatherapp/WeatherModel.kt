package com.example.minimalisticweatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.minimalisticweatherapp.retrofit.RetrofitClient
import com.example.minimalisticweatherapp.retrofit.WeatherResponse
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherModel : WeatherConstructor.Model {

    private val retrofit = RetrofitClient.retrofitAPI
    override fun getCurrentLocation(callback: (Location?) -> Unit) {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)   //?????????
        if (ActivityCompat.checkSelfPermission(
                context,    /// ?????????????
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,    /// ?????????????
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            callback(location)
        }
    }

    override fun fetchWeatherData(
        latitude: String,
        longitude: String,
        callback: (WeatherResponse?, Throwable?) -> Unit
    ) {
        val call = retrofit.getWeather(
            latitude, longitude,
            "4952f57884bddceab6b299e99f263f07", "metric", "en"
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
                    callback(null, Exception("Weather request failed"))
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}