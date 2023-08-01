package com.example.minimalisticweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.minimalisticweatherapp.retrofit.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)
        val b = findViewById<Button>(R.id.button)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val weatherApi = retrofit.create(WeatherApi::class.java)

        b.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val weather = weatherApi.getWeather()
                runOnUiThread {
                    tv.text = weather.temp.toString()
                }
            }
        }


    }
}