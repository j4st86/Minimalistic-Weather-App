package com.minimalisticweatherapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location")
    val weatherLocationData: LocationData,
    @SerializedName("current")
    val weatherCurrentData: CurrentWeatherData,
    @SerializedName("forecast")
    val weatherForecastData: ForecastWeatherData
)

data class LocationData(
    @SerializedName("name")
    val cityName: String
)

data class CurrentWeatherData(
    @SerializedName("temp_c")
    val tempCurrentCelsius: Double,
    @SerializedName("temp_f")
    val tempCurrentFahrenheit: Double,
    @SerializedName("wind_mph")
    val windCurrentSpeedMph: Double,
    @SerializedName("wind_kph")
    val windCurrentSpeedKph: Double,
    @SerializedName("wind_dir")
    val windDirection: String,
    @SerializedName("pressure_mb")
    val pressureCurrent: Double,
    @SerializedName("humidity")
    val humidityCurrent: Int,
    @SerializedName("is_day")
    val isDayCode: Int,
    @SerializedName("condition")
    val weatherCurrentCondition: CurrentWeatherCondition
)

data class CurrentWeatherCondition(
    @SerializedName("code")
    val weatherConditionCode: Int
)

data class ForecastWeatherData(
    @SerializedName("forecastday")
    val forecastDayData: List<ForecastDayData>
)

data class ForecastDayData(
    @SerializedName("day")
    val dayWeatherData: DayWeatherData,
    @SerializedName("hour")
    val hourWeatherData: List<HourWeatherData>
)

data class DayWeatherData(
    @SerializedName("maxtemp_c")
    val maximumDayTempCelsius: Double,
    @SerializedName("maxtemp_f")
    val maximumDayTempFahrenheit: Double,
    @SerializedName("mintemp_c")
    val minimumDayTempCelsius: Double,
    @SerializedName("mintemp_f")
    val minimumDayTempFahrenheit: Double,
    @SerializedName("avgtemp_c")
    val averageDayTempCelsius: Double,
    @SerializedName("avgtemp_f")
    val averageDayTempFahrenheit: Double
)

data class HourWeatherData(
    @SerializedName("temp_c")
    val hourTempCelsius: Double,
    @SerializedName("temp_f")
    val hourTempFahrenheit: Double,
    @SerializedName("is_day")
    val hourIsDayCode: Int,
    @SerializedName("wind_kph")
    val hourWindSpeedKph: Double,
    @SerializedName("wind_mph")
    val hourWindSpeedMph: Double,
    @SerializedName("wind_dir")
    val hourWindDirection: String,
    @SerializedName("pressure_mb")
    val hourPressureMb: Double,
    @SerializedName("humidity")
    val hourHumidity: Int,
    @SerializedName("condition")
    val hourWeatherCondition: HourWeatherCondition
)

data class HourWeatherCondition(
    @SerializedName("code")
    val weatherHourConditionCode: Int
)