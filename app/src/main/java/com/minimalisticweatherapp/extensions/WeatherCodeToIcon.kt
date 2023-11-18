package com.minimalisticweatherapp.extensions

import com.minimalisticweatherapp.R

fun weatherIconByCode(weatherConditionCode: Int, isDayCode: Int): Int {
    return when (weatherConditionCode) {
        1000 -> weatherIconDayNight(isDayCode, R.drawable.ic_sun, R.drawable.ic_moon)
        1003 -> weatherIconDayNight(isDayCode, R.drawable.ic_sun_cloudy, R.drawable.ic_moon_cloudy)
        1006, 1009 -> R.drawable.ic_cloudy
        1030 -> R.drawable.ic_fog
        1063, 1180, 1186, 1192, 1240, 1243 -> weatherIconDayNight(
            isDayCode,
            R.drawable.ic_sun_rain,
            R.drawable.ic_moon_rain
        )

        1066, 1210, 1213, 1216, 1222, 1255, 1279 -> weatherIconDayNight(
            isDayCode,
            R.drawable.ic_snow_sun,
            R.drawable.ic_snow_moon
        )

        1069, 1198, 1201, 1204, 1207, 1249, 1252 -> R.drawable.ic_snow_rain
        1072, 1150, 1153, 1168, 1171, 1183, 1189, 1195, 1246 -> R.drawable.ic_cloudy_rain
        1087, 1273 -> weatherIconDayNight(
            isDayCode,
            R.drawable.ic_sun_thunder,
            R.drawable.ic_moon_thunder
        )

        1114, 1117 -> R.drawable.ic_snow_wind
        1135 -> R.drawable.ic_fog_cloudy
        1147 -> R.drawable.ic_fog_snow
        1219, 1225, 1258, 1282 -> R.drawable.ic_snow_cloudy
        1237, 1261, 1264 -> R.drawable.ic_pellets
        1276 -> R.drawable.ic_cloudy_thunder
        else -> {
            R.drawable.ic_no_image
        }
    }
}

fun weatherIconDayNight(isDayCode: Int, iconDay: Int, iconNight: Int): Int {
    return if (isDayCode == 1) {
        iconDay
    } else iconNight
}