package com.minimalisticweatherapp.extensions

fun directionTranslate(windDirection: String): String {
    return when(windDirection) {
        "N" -> "С"
        "NE" -> "СВ"
        "E" -> "В"
        "SE" -> "ЮВ"
        "S" -> "Ю"
        "SW" -> "ЮЗ"
        "W" -> "З"
        "NW" -> "СЗ"
        else -> {
            "Н.Д."
        }
    }
}