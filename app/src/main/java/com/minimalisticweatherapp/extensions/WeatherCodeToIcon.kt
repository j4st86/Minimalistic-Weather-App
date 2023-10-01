package com.minimalisticweatherapp.extensions

import com.minimalisticweatherapp.R

fun weatherIconByCode(weatherConditionCode: Int): Int {
    return when (weatherConditionCode) {
        1000 -> R.drawable.ic_cloudy
        else -> {
            -1
        }
    }
}