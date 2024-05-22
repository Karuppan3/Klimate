package io.bash_psk.klimate.domain.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import io.bash_psk.klimate.domain.resource.ConstantString
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherForecastData

@Stable
@Immutable
data class MainUIState(
    val isLoading: Boolean = false,
    val message: String = ConstantString.EMPTY,
    val weatherForecast: WeatherForecastData = WeatherForecastData(),
    val weatherData: WeatherData = WeatherData(),
)