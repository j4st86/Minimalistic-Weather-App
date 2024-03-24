package com.minimalisticweatherapp.extensions

fun directionTranslate(windDirection: String): String {
    return when(windDirection) {
        "N" -> "С"
        "NNE" -> "ССВ"
        "NE" -> "СВ"
        "ENE" -> "ВСВ"
        "E" -> "В"
        "ESE" -> "ВЮВ"
        "SE" -> "ЮВ"
        "SSE" -> "ЮЮВ"
        "S" -> "Ю"
        "SSW" -> "ЮЮЗ"
        "SW" -> "ЮЗ"
        "WSW" -> "ЗЮЗ"
        "W" -> "З"
        "WNW" -> "ЗСЗ"
        "NW" -> "СЗ"
        "NNW" -> "ССЗ"
        else -> {
            "Н.Д."
        }
    }
}