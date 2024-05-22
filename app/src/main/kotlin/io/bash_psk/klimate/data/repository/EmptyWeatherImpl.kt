package io.bash_psk.klimate.data.repository

import android.app.Application
import io.bash_psk.klimate.data.weather.KlimateJson
import io.bash_psk.klimate.domain.repository.EmptyWeather
import io.bash_psk.klimate.domain.resource.ConstantAgent
import io.bash_psk.klimate.domain.resource.ConstantQuery
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherForecastData
import io.bash_psk.klimate.domain.weather.WeatherGraphData
import io.bash_psk.klimate.domain.weather.WeatherType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData
)

class EmptyWeatherImpl @Inject constructor(
    private val application: Application
) : EmptyWeather {

    override fun getWeatherQuery(latitude: Double, longitude: Double) : String {

        val newWeatherQuery = "${
            ConstantQuery.OPEN_METEO
        }${
            ConstantQuery.FORECAST
        }${
            ConstantQuery.ADD
        }${
            ConstantQuery.LATITUDE
        }${
            ConstantQuery.EQUAL
        }${
            latitude
        }${
            ConstantQuery.AND
        }${
            ConstantQuery.LONGITUDE
        }${
            ConstantQuery.EQUAL
        }${
            longitude
        }${
            ConstantQuery.AND
        }${
            ConstantQuery.HOURLY
        }"

        return newWeatherQuery
    }

    override fun getWeatherForecast(
        latitude: Double,
        longitude: Double
    ) : Flow<WeatherForecastData> = flow {

        val weatherClient = HttpClient(engineFactory = Android) {

            install(plugin = Logging) {

                level = LogLevel.ALL
            }

            install(plugin = ContentNegotiation) {

                json(json = KlimateJson.WeatherJson, contentType = ContentType.Application.Json)
            }

            install(plugin = UserAgent) {

                agent = ConstantAgent.USER_AGENT
            }

            headers {

                append(name = HttpHeaders.Accept, value = ContentType.Application.Json)
            }
        }

        try {

            val weatherResponse = weatherClient.get(
                urlString = getWeatherQuery(latitude = latitude, longitude = longitude)
            ).body<WeatherGraphData>()

            val newWeatherList = weatherResponse.hourly.time.mapIndexed { index: Int, time: String ->

                val newWeatherData = WeatherData(
                    time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                    temperature = weatherResponse.hourly.temperature_2m[index],
                    pressure = weatherResponse.hourly.pressure_msl[index],
                    windSpeed = weatherResponse.hourly.windspeed_10m[index],
                    humidity = weatherResponse.hourly.relativehumidity_2m[index],
                    weatherType = WeatherType.fromWMO(code = weatherResponse.hourly.weathercode[index])
                )

                IndexedWeatherData(
                    index = index,
                    weatherData = newWeatherData
                )
            }.groupBy { indexedWeatherData: IndexedWeatherData ->

                indexedWeatherData.index / 24
            }.mapValues { indexedWeatherDataEntries: Map.Entry<Int, List<IndexedWeatherData>> ->

                indexedWeatherDataEntries.value.map { indexedWeatherData: IndexedWeatherData ->

                    indexedWeatherData.weatherData
                }
            }

            val newWeatherForecastData = WeatherForecastData(
                weatherUnits = weatherResponse.hourly_units,
                weatherDataList = newWeatherList
            )

            emit(value = newWeatherForecastData)
        } catch (exception: Exception) {

            emit(value = WeatherForecastData())
        }
    }
}