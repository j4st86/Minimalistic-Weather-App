package com.minimalisticweatherapp.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.minimalisticweatherapp.R
import com.minimalisticweatherapp.retrofit.LocationModel

class LoadingActivity : AppCompatActivity() {

    private val loadingImageView: AppCompatImageView by lazy { findViewById(R.id.loading_iv) }
    private val loadingTextView: AppCompatTextView by lazy { findViewById(R.id.loading_tv) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        loadingTextView.text = getString(R.string.weather_loading)
        loadingImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                this@LoadingActivity, R.drawable.ic_loading
            )
        )

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getCurrentLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.gps_was_not_obtained),
                    Toast.LENGTH_LONG
                ).show()
                navigateToMainActivityDefaultLocation()
            }
        }
    }

    private fun getCurrentLocation() {
        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                navigateToMainActivity(location)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.failed_to_retrieve_last_location),
                    Toast.LENGTH_LONG
                ).show()
                navigateToMainActivityDefaultLocation()
            }
        }
    }

    private fun navigateToMainActivity(location: Location) {
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra(
            MainActivity.EXTRA_LOCATION,
            LocationModel(location.latitude.toString(), location.longitude.toString())
        )
        startActivity(intent)
        finish()
    }

    private fun navigateToMainActivityDefaultLocation() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(
            MainActivity.EXTRA_LOCATION,
            LocationModel("55.7510", "37.6176")
        )
        startActivity(intent)
        finish()
    }

    companion object {
        private const val LOCATION_PERMISSION = 86
    }
}