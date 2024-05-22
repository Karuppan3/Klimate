package io.bash_psk.klimate.domain.repository

import android.location.Location
import android.location.LocationManager

interface EmptyLocation {

    val locationManager: LocationManager

    val isGPSEnabled: Boolean

    suspend fun getCurrentLocation() : Location?
}