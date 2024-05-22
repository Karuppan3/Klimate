package io.bash_psk.klimate.domain.weather

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import java.time.LocalDateTime
import java.util.UUID

@Stable
@Immutable
data class WeatherData(
    val uuid: String = UUID.randomUUID().toString(),
    val time: LocalDateTime = LocalDateTime.now(),
    val temperature: Double = 0.0,
    val pressure: Double = 0.0,
    val windSpeed: Double = 0.0,
    val humidity: Double = 0.0,
    val weatherType: WeatherType = WeatherType.ClearSky
)