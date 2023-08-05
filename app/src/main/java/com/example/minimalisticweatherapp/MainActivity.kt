package com.example.minimalisticweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.minimalisticweatherapp.retrofit.RetrofitClient
import com.example.minimalisticweatherapp.retrofit.WeatherAPI
import com.example.minimalisticweatherapp.retrofit.WeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val anotherTempTextView: AppCompatTextView by lazy { findViewById(R.id.another_temp_tv) }

    private val retrofitClient: RetrofitClient = RetrofitClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var locationLatitude: Double = 60.0
        var locationLongitude: Double = 50.0

        val weatherAPI = retrofitClient.createRetrofit().create(WeatherAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val weather: WeatherData = weatherAPI.getWeather(
                locationLatitude, locationLongitude,
                "4952f57884bddceab6b299e99f263f07", "metric", "en"
            )
            runOnUiThread {
                weatherImageView.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this@MainActivity,
                        R.drawable.ic_sun
                    )
                )
                tempTextView.text = weather.mainWeatherData.temp.toString()
                anotherTempTextView.text = weather.mainWeatherData.temp_max.toString()
            }
        }
    }
}