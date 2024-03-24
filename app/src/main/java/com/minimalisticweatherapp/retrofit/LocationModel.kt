package com.minimalisticweatherapp.retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    var latitude: String?,
    var longitude: String?,
    var userLocation: String = "$latitude,$longitude"
) : Parcelable
