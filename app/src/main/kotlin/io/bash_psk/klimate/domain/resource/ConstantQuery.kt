package io.bash_psk.klimate.domain.resource

object ConstantQuery {

    const val TEST_01 = "https://api.open-meteo.com/v1/forecast?latitude=3.51&longitude=6.17&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"

    const val OPEN_METEO = "https://api.open-meteo.com"
    const val FORECAST = "/v1/forecast"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val HOURLY = "hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
    const val ADD = "?"
    const val AND = "&"
    const val EQUAL = "="
}