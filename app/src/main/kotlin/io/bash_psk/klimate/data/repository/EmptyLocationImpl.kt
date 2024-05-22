package io.bash_psk.klimate.data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import io.bash_psk.klimate.domain.repository.EmptyLocation
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class EmptyLocationImpl @Inject constructor(
    private val application: Application,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : EmptyLocation {

    override val locationManager : LocationManager
        get() = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    override val isGPSEnabled : Boolean
        get() = locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        ) || locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER
        )

    override suspend fun getCurrentLocation() : Location? {

        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isDeniedFineLocation = hasAccessFineLocationPermission.not()
        val isDeniedCoarseLocation = hasAccessCoarseLocationPermission.not()
        val isDisabledGPS = isGPSEnabled.not()

        when {

            isDeniedFineLocation || isDeniedCoarseLocation || isDisabledGPS -> {

                return null
            }
        }

        return suspendCancellableCoroutine { cancellableContinuation: CancellableContinuation<Location?> ->

            fusedLocationProviderClient.getCurrentLocation(
                Priority.PRIORITY_BALANCED_POWER_ACCURACY,
                CancellationTokenSource().token
            ).apply {

                when {

                    isComplete -> {

                        when (isSuccessful) {

                            true -> {

                                cancellableContinuation.resume(value = result)
                            }

                            false -> {

                                cancellableContinuation.resume(value = null)
                            }
                        }

                        return@suspendCancellableCoroutine
                    }
                }

                addOnSuccessListener { location ->

                    cancellableContinuation.resume(value = location)
                }

                addOnFailureListener { exception: Exception ->

                    cancellableContinuation.resume(value = null)
                }

                addOnCanceledListener {

                    cancellableContinuation.cancel()
                }
            }
        }
    }
}