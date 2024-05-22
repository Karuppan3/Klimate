package io.bash_psk.klimate.domain.weather

import kotlinx.serialization.Serializable

@Serializable
data class WeatherHourlyData(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val weathercode: List<Int> = emptyList(),
    val relativehumidity_2m: List<Double> = emptyList(),
    val windspeed_10m: List<Double> = emptyList(),
    val pressure_msl: List<Double> = emptyList(),
)