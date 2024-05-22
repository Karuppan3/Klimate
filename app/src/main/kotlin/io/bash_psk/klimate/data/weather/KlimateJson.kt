package io.bash_psk.klimate.data.weather

import kotlinx.serialization.json.Json

object KlimateJson {

    val WeatherJson = Json {

        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
    }
}