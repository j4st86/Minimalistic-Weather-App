package com.example.minimalisticweatherapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    var latitude: String?,
    var longitude: String?
) : Parcelable
