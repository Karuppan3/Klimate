package io.bash_psk.klimate.domain.weather

import kotlinx.serialization.Serializable

@Serializable
data class WeatherUnits(
    val time: String = "",
    val temperature_2m: String = "",
    val weathercode: String = "",
    val relativehumidity_2m: String = "",
    val windspeed_10m: String = "",
    val pressure_msl: String = ""
)