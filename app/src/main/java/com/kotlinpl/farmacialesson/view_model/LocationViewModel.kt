package com.kotlinpl.farmacialesson.view_model

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.kotlinpl.farmacialesson.util.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale
import javax.inject.Inject

class LocationViewModel @Inject constructor (
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    // Decimos que nuestro usuario está en isla de pascua
    init {
        _location.value = Location(lat =-27.11944 , long = -109.35742)
    }

    fun hasPermissions() : Boolean {
         val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        return hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED && hasFineLocationPermission == PackageManager.PERMISSION_GRANTED
    }

    fun getLocation(latitude: Double, longitude: Double): List<Address?>? {
        return geocoder.getFromLocation(latitude, longitude, 1)
    }

    fun getUserLocation() {

    }

    private val geocoder = Geocoder(context, Locale.getDefault())
}