package com.minimalisticweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.minimalisticweatherapp.model.LocationModel

class MainActivity : AppCompatActivity(), WeatherMain.View {

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val anotherTempTextView: AppCompatTextView by lazy { findViewById(R.id.another_temp_tv) }

    private lateinit var presenter: WeatherMain.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationData =
            intent.getParcelableExtra<LocationModel>(EXTRA_LOCATION)
                ?: throw Exception("Error: Can't get position data or no last known location data")

        val model = WeatherModel()
        presenter = WeatherPresenter(this, model, locationData)

        presenter.start()
    }

    override fun showWeatherData(
        temperatureCelsius: String,
        temperatureFahrenheit: String,
        weatherIcon: Int
    ) {
        tempTextView.text = temperatureCelsius
        anotherTempTextView.text = temperatureFahrenheit
        weatherImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, weatherIcon
            )
        )
    }

    override fun showErrorMessage(message: String) {
        //TODO Display Error
    }

    companion object {
        const val EXTRA_LOCATION = "com.minimalisticweatherapp.LOCATION"
    }
}