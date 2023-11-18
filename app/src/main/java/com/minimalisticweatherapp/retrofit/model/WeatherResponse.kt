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
    @SerializedName("is_day")
    val isDayCode: Int,
    @SerializedName("condition")
    val weatherCurrentCondition: CurrentWeatherCondition
)

data class CurrentWeatherCondition(
    @SerializedName("text")
    val weatherConditionDescription: String,
    @SerializedName("code")
    val weatherConditionCode: Int
)

data class ForecastWeatherData(
    @SerializedName("forecastday")
    val forecastDayData: List<ForecastDayData>
)

data class ForecastDayData(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val dayWeatherData: DayWeatherData,
    @SerializedName("hour")
    val hourWeatherData: List<HourWeatherData>
)

data class DayWeatherData(
    @SerializedName("maxtemp_c")
    val maximalDayTempCelsius: Double,
    @SerializedName("maxtemp_f")
    val maximumDayTempFahrenheit: Double,
    @SerializedName("mintemp_c")
    val minimalDayTempCelsius: Double,
    @SerializedName("mintemp_f")
    val minimalDayTempFahrenheit: Double,
    @SerializedName("avgtemp_c")
    val averageDayTempCelsius: Double,
    @SerializedName("avgtemp_f")
    val averageDayTempFahrenheit: Double,
    @SerializedName("condition")
    val dayWeatherCondition: DayWeatherCondition
)

data class DayWeatherCondition(
    @SerializedName("code")
    val weatherDayConditionCode: Int
)

data class HourWeatherData(
    @SerializedName("time_epoch")
    val hourTimeEpoch: Int,
    @SerializedName("time")
    val hourTime: String,
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
    @SerializedName("wind_degree")
    val hourWindDegree: Int,
    @SerializedName("wind_dir")
    val hourWindDirection: String,
    @SerializedName("pressure_in")
    val hourPressureIn: Double,
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