package com.example.minimalisticweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.minimalisticweatherapp.retrofit.RetrofitClient
import com.example.minimalisticweatherapp.retrofit.WeatherResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val anotherTempTextView: AppCompatTextView by lazy { findViewById(R.id.another_temp_tv) }

//    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var locationLatitude: String = "60"
    private var locationLongitude: String = "60"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClient.retrofitAPI

        val call = retrofit.getWeather(
            locationLatitude, locationLongitude,
            "4952f57884bddceab6b299e99f263f07", "metric", "en"
        )

        call.enqueue(object : retrofit2.Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        val temperature = weatherResponse.mainWeatherData.temp
                        val temperatureMax = weatherResponse.mainWeatherData.tempMax
                        val temperatureMin = weatherResponse.mainWeatherData.tempMin
                        val description = weatherResponse.weather[0].description

                        tempTextView.text = temperature.toString()
                        anotherTempTextView.text = temperatureMax.toString()
                        weatherImageView.setImageDrawable(
                            AppCompatResources.getDrawable(
                                this@MainActivity, R.drawable.ic_sun
                            )
                        )
                    }
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // handle error
            }
        })

//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            val locationPermissionRequest = registerForActivityResult(
//                ActivityResultContracts.RequestMultiplePermissions()
//            ) { permissions ->
//                when {
//                    permissions.getOrDefault(
//                        Manifest.permission.ACCESS_FINE_LOCATION, false
//                    ) -> {
//                        // Precise location access granted.
//                    }
//
//                    permissions.getOrDefault(
//                        Manifest.permission.ACCESS_COARSE_LOCATION, false
//                    ) -> {
//                        // Only approximate location access granted.
//                    }
//
//                    else -> {
//                        // No location access granted.
//                    }
//                }
//            }
//
//            locationPermissionRequest.launch(
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//            )
//
//        }
//        fusedLocationClient.getCurrentLocation(
//            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
//            object : CancellationToken() {
//                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
//                    CancellationTokenSource().token
//
//                override fun isCancellationRequested() = false
//            })
//            .addOnSuccessListener { location: Location? ->
//                if (location == null)
//                    Toast.makeText(
//                        this, "Cannot get location.", Toast.LENGTH_SHORT
//                    ).show()
//                else {
//                    locationLatitude = location.latitude.toString()
//                    locationLongitude = location.longitude.toString()
//                }
//
//            }
//
//        weatherImageView.setImageDrawable(
//            AppCompatResources.getDrawable(
//                this, R.drawable.ic_sun
//            )
//        )
//        tempTextView.text = weather.mainWeatherData.temp.toString()
//        anotherTempTextView.text = weather.mainWeatherData.temp_max.toString()
    }
}