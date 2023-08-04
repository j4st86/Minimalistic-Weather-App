package com.example.minimalisticweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.minimalisticweatherapp.retrofit.WeatherAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)
        val textCity = findViewById<TextView>(R.id.textCity)
        val b = findViewById<Button>(R.id.button)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherAPIService::class.java)

        b.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val weather = weatherApi.getWeather(44.34, 10.99,
                    "4952f57884bddceab6b299e99f263f07", "metric", "en")
                runOnUiThread {
                    tv.text = weather.mainWeatherData.temp.toString()
                    textCity.text = weather.name
                }
            }
        }
    }
}