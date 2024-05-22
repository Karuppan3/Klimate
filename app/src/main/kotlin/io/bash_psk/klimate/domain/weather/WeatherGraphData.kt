package io.bash_psk.klimate.domain.weather

import kotlinx.serialization.Serializable

@Serializable
data class WeatherGraphData(
    val hourly_units: WeatherUnits = WeatherUnits(),
    val hourly: WeatherHourlyData = WeatherHourlyData()
)