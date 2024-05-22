package io.bash_psk.klimate.domain.weather

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
@Immutable
data class WeatherForecastData(
    val uuid: String = UUID.randomUUID().toString(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val areaName: String = "",
    val weatherUnits: WeatherUnits = WeatherUnits(),
    val weatherDataList: Map<Int, List<WeatherData>> = emptyMap()
)