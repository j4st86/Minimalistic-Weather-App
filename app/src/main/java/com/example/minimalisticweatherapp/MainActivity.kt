package com.example.minimalisticweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity(), WeatherMain.View {

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val anotherTempTextView: AppCompatTextView by lazy { findViewById(R.id.another_temp_tv) }

    private lateinit var presenter: WeatherMain.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = WeatherModel(this)
        presenter = WeatherPresenter(this, model)

        presenter.start()
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
}