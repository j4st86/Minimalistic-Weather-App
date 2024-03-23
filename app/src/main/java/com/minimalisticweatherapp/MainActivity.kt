package com.minimalisticweatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.minimalisticweatherapp.model.LocationModel

class MainActivity : AppCompatActivity(), WeatherMain.View {

    private val settingsImageView: AppCompatImageView by lazy { findViewById(R.id.settings_iv) }
    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val cityTextView: AppCompatTextView by lazy { findViewById(R.id.city_name_tv) }
    private val maxTempImageView: AppCompatImageView by lazy { findViewById(R.id.max_temp_iv) }
    private val minTempImageView: AppCompatImageView by lazy { findViewById(R.id.min_temp_iv) }
    private val maxTempTextView: AppCompatTextView by lazy { findViewById(R.id.max_temp_tv) }
    private val minTempTextView: AppCompatTextView by lazy { findViewById(R.id.min_temp_tv) }

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

        settingsImageView.setOnClickListener {
            intent = Intent(applicationContext, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showCurrentWeatherData(
        cityName: String,
        temperatureCurrent: String,
        @DrawableRes
        weatherIcon: Int
    ) {
        cityTextView.text = cityName
        tempTextView.text = temperatureCurrent
        weatherImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, weatherIcon
            )
        )
    }

    override fun showMainImages(settingIcon: Int, maxTempIcon: Int, minTempIcon: Int) {
        settingsImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, settingIcon
            )
        )
        maxTempImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                maxTempIcon
            )
        )
        minTempImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                minTempIcon
            )
        )
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        const val EXTRA_LOCATION = "com.minimalisticweatherapp.LOCATION"
    }
}