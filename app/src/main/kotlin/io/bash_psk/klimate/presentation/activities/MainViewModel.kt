package io.bash_psk.klimate.presentation.activities

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.bash_psk.klimate.domain.events.MainUIEvent
import io.bash_psk.klimate.domain.repository.EmptyLocation
import io.bash_psk.klimate.domain.repository.EmptyWeather
import io.bash_psk.klimate.domain.resource.ConstantString
import io.bash_psk.klimate.domain.state.MainUIState
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherForecastData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val emptyLocation: EmptyLocation,
    private val emptyWeather: EmptyWeather
) : ViewModel() {

    private val _mainUIState = MutableStateFlow(value = MainUIState())

    val mainUIState = _mainUIState.asStateFlow()

    fun onMainUIEvent(mainUIEvent: MainUIEvent) = viewModelScope.launch {

        when (mainUIEvent) {

            is MainUIEvent.GetWeatherInfo -> {

                val loadingMainUIState = mainUIState.value.copy(
                    isLoading = true,
                    message = ConstantString.GETTING_WEATHER_INFO,
                    weatherForecast = WeatherForecastData(),
                    weatherData = WeatherData()
                )

                _mainUIState.emit(value = loadingMainUIState)

                emptyLocation.getCurrentLocation()?.let { location: Location ->

                    println(message = "LOC - ${location.latitude}, ${location.longitude}")

                    emptyWeather.getWeatherForecast(
                        latitude = location.latitude,
                        longitude = location.longitude
                    ).collectLatest { weatherForecastLatest: WeatherForecastData ->

                        val weatherForecastMainUIState = mainUIState.value.copy(
                            isLoading = false,
                            message = ConstantString.SUCCESSFULLY_TO_GET_WEATHER_INFO,
                            weatherForecast = weatherForecastLatest,
                            weatherData = weatherForecastLatest.weatherDataList[0]?.find { weatherData ->

                                weatherData.time.hour == LocalDateTime.now().hour
                            } ?: WeatherData()
                        )

                        _mainUIState.emit(value = weatherForecastMainUIState)
                    }
                } ?: kotlin.run {

                    val errorMainUIState = mainUIState.value.copy(
                        isLoading = false,
                        message = ConstantString.FAILED_TO_RETRIEVE_LOCATION,
                        weatherForecast = WeatherForecastData(),
                        weatherData = WeatherData()
                    )

                    _mainUIState.emit(value = errorMainUIState)
                }
            }

            is MainUIEvent.SetMainUIState -> {

                _mainUIState.emit(value = mainUIEvent.mainUIState)
            }
        }
    }
}