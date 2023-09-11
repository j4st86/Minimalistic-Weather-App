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
            intent.getParcelableExtra<LocationModel>(EXTRA_LOCATION) ?: throw Exception("error")

        val model = WeatherModel()
        presenter = WeatherPresenter(this, model, locationData)

        presenter.start()
    }

    override fun showWeatherData(temperatureCelsius: String, temperatureFahrenheit: String) {
        tempTextView.text = temperatureCelsius
        anotherTempTextView.text = temperatureFahrenheit
        weatherImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, R.drawable.ic_sun_cloudy
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