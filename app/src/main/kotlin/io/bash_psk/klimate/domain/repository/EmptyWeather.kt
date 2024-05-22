package io.bash_psk.klimate.domain.repository

import io.bash_psk.klimate.domain.weather.WeatherForecastData
import kotlinx.coroutines.flow.Flow

interface EmptyWeather {

    fun getWeatherQuery(latitude: Double, longitude: Double) : String

    fun getWeatherForecast(latitude: Double, longitude: Double) : Flow<WeatherForecastData>
}