package com.minimalisticweatherapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.AppCompatTextView
import com.minimalisticweatherapp.R
import com.minimalisticweatherapp.WeatherMain
import com.minimalisticweatherapp.models.WeatherModel
import com.minimalisticweatherapp.presenters.WeatherPresenter
import com.minimalisticweatherapp.retrofit.LocationModel

class MainActivity : AppCompatActivity(), WeatherMain.View {

    private val settingsImageView: AppCompatImageView by lazy { findViewById(R.id.settings_iv) }
    private val cityTextView: AppCompatTextView by lazy { findViewById(R.id.city_name_tv) }

    private val weatherImageView: AppCompatImageView by lazy { findViewById(R.id.weather_iv) }
    private val tempTextView: AppCompatTextView by lazy { findViewById(R.id.temp_tv) }
    private val maxTempImageView: AppCompatImageView by lazy { findViewById(R.id.max_temp_iv) }
    private val minTempImageView: AppCompatImageView by lazy { findViewById(R.id.min_temp_iv) }
    private val maxTempTextView: AppCompatTextView by lazy { findViewById(R.id.max_temp_tv) }
    private val minTempTextView: AppCompatTextView by lazy { findViewById(R.id.min_temp_tv) }

    private val windSpeedImageView: AppCompatImageView by lazy { findViewById(R.id.wind_speed_iv) }
    private val pressureImageView: AppCompatImageView by lazy { findViewById(R.id.pressure_iv) }
    private val humidityImageView: AppCompatImageView by lazy { findViewById(R.id.humidity_iv) }
    private val windSpeedTextView: AppCompatTextView by lazy { findViewById(R.id.wind_speed_tv) }
    private val pressureTextView: AppCompatTextView by lazy { findViewById(R.id.pressure_tv) }
    private val humidityTextView: AppCompatTextView by lazy { findViewById(R.id.humidity_tv) }

    private val dayTimeImageView: AppCompatImageView by lazy { findViewById(R.id.daytime_iv) }
    private val nightTimeImageView: AppCompatImageView by lazy { findViewById(R.id.nighttime_iv) }
    private val sliderSeekBar: AppCompatSeekBar by lazy { findViewById(R.id.time_change_sb) }

    private lateinit var presenter: WeatherMain.Presenter
    //TODO добавить слайдер после температуры (можно пока не рабочий)
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
        windSpeedCurrent: String,
        pressureCurrent: String,
        humidityCurrent: String,
        @DrawableRes
        weatherIcon: Int
    ) {
        cityTextView.text = cityName
        tempTextView.text = temperatureCurrent
        windSpeedTextView.text = windSpeedCurrent
        pressureTextView.text = pressureCurrent
        humidityTextView.text = humidityCurrent
        weatherImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity, weatherIcon
            )
        )
    }

    override fun showForecastWeatherData(
        temperatureMaximum: String,
        temperatureMinimum: String
    ) {
        maxTempTextView.text = temperatureMaximum
        minTempTextView.text = temperatureMinimum
    }

    override fun showMainImages(
        settingIcon: Int,
        maxTempIcon: Int,
        minTempIcon: Int,
        windSpeedIcon: Int,
        pressureIcon: Int,
        humidityIcon: Int,
        dayTimeIcon: Int,
        nightTimeIcon: Int
    ) {
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
        windSpeedImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                windSpeedIcon
            )
        )
        pressureImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                pressureIcon
            )
        )
        humidityImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                humidityIcon
            )
        )
        dayTimeImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                dayTimeIcon
            )
        )
        nightTimeImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@MainActivity,
                nightTimeIcon
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