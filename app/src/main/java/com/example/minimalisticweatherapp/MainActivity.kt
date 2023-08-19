package com.example.minimalisticweatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity(), WeatherMain.View {

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val anotherTempTextView: AppCompatTextView by lazy { findViewById(R.id.another_temp_tv) }


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var presenter: WeatherMain.Presenter
    private lateinit var locationData: LocationModel

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 86
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = WeatherModel()
        presenter = WeatherPresenter(this@MainActivity, model, locationData)
        presenter.start()



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }

    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                locationData = LocationModel(
                    location.latitude.toString(),
                    location.longitude.toString()
                )

//                fetchWeatherData(locationData.latitude, locationData.longitude)
            }
        }
    }

    override fun showWeatherData(temperature: String, temperatureMax: String) {
        tempTextView.text = temperature
        anotherTempTextView.text = temperatureMax
        weatherImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, R.drawable.ic_sneg
            )
        )
    }

    override fun showErrorMessage(message: String) {
        // Display Error
    }

//    private fun fetchWeatherData(latitude: String, longitude: String) {
//        val retrofit = RetrofitClient.retrofitAPI
//        val call = retrofit.getWeather(
//            latitude, longitude,
//            "4952f57884bddceab6b299e99f263f07", "metric", "en"
//        )
//        call.enqueue(object : retrofit2.Callback<WeatherResponse> {
//            override fun onResponse(
//                call: Call<WeatherResponse>,
//                response: Response<WeatherResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val weatherResponse = response.body()
//                    if (weatherResponse != null) {
//                        val temperature = weatherResponse.mainWeatherData.temp
//                        val temperatureMax = weatherResponse.mainWeatherData.tempMax
//                        val temperatureMin = weatherResponse.mainWeatherData.tempMin
//                        val description = weatherResponse.weather[0].description
//
//                        tempTextView.text = temperature.toString()
//                        anotherTempTextView.text = temperatureMax.toString()
//                        weatherImageView.setImageDrawable(
//                            AppCompatResources.getDrawable(
//                                this@MainActivity, R.drawable.ic_sneg
//                            )
//                        )
//                    }
//                } else {
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
//                // handle error
//            }
//        })
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_LOCATION_PERMISSION) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getLocationAndFetchWeather()
//            } else {
//                // handle error
//            }
//        }
//    }
}