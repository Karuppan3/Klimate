package io.bash_psk.klimate.domain.weather

import androidx.annotation.DrawableRes
import io.bash_psk.klimate.R

sealed class WeatherType(
    val title: String,
    @DrawableRes
    val icon: Int
) {
    
    data object ClearSky : WeatherType(
        title = "Clear Sky",
        icon = R.drawable.ic_sunny
    )
    
    data object MainlyClear : WeatherType(
        title = "Mainly Clear",
        icon = R.drawable.ic_cloudy
    )
    
    data object PartlyCloudy : WeatherType(
        title = "Partly Cloudy",
        icon = R.drawable.ic_cloudy
    )
    
    data object Overcast : WeatherType(
        title = "Overcast",
        icon = R.drawable.ic_cloudy
    )
    
    data object Foggy : WeatherType(
        title = "Foggy",
        icon = R.drawable.ic_very_cloudy
    )
    
    data object DepositingRimeFog : WeatherType(
        title = "Depositing Rime Fog",
        icon = R.drawable.ic_very_cloudy
    )
    
    data object LightDrizzle : WeatherType(
        title = "Light Drizzle",
        icon = R.drawable.ic_rain_shower
    )
    
    data object ModerateDrizzle : WeatherType(
        title = "Moderate Drizzle",
        icon = R.drawable.ic_rain_shower
    )
    
    data object DenseDrizzle : WeatherType(
        title = "Dense Drizzle",
        icon = R.drawable.ic_rain_shower
    )
    
    data object LightFreezingDrizzle : WeatherType(
        title = "Slight Freezing Drizzle",
        icon = R.drawable.ic_snowy_rainy
    )
    
    data object DenseFreezingDrizzle : WeatherType(
        title = "Dense Freezing Drizzle",
        icon = R.drawable.ic_snowy_rainy
    )
    
    data object SlightRain : WeatherType(
        title = "Slight rain",
        icon = R.drawable.ic_rainy
    )
    
    data object ModerateRain : WeatherType(
        title = "Rainy",
        icon = R.drawable.ic_rainy
    )
    
    data object HeavyRain : WeatherType(
        title = "Heavy Rain",
        icon = R.drawable.ic_rainy
    )
    
    data object HeavyFreezingRain: WeatherType(
        title = "Heavy Freezing Rain",
        icon = R.drawable.ic_snowy_rainy
    )
    
    data object SlightSnowFall: WeatherType(
        title = "Slight Snow Fall",
        icon = R.drawable.ic_snowy
    )
    
    data object ModerateSnowFall: WeatherType(
        title = "Moderate Snow Fall",
        icon = R.drawable.ic_heavy_snow
    )
    
    data object HeavySnowFall: WeatherType(
        title = "Heavy Snow Fall",
        icon = R.drawable.ic_heavy_snow
    )
    
    data object SnowGrains: WeatherType(
        title = "Snow Grains",
        icon = R.drawable.ic_heavy_snow
    )
    
    data object SlightRainShowers: WeatherType(
        title = "Slight Rain Showers",
        icon = R.drawable.ic_rain_shower
    )
    
    data object ModerateRainShowers: WeatherType(
        title = "Moderate Rain Showers",
        icon = R.drawable.ic_rain_shower
    )
    
    data object ViolentRainShowers: WeatherType(
        title = "Violent Rain Showers",
        icon = R.drawable.ic_rain_shower
    )
    
    data object SlightSnowShowers: WeatherType(
        title = "Light Snow Showers",
        icon = R.drawable.ic_snowy
    )
    
    data object HeavySnowShowers: WeatherType(
        title = "Heavy Snow Showers",
        icon = R.drawable.ic_snowy
    )
    
    data object ModerateThunderstorm: WeatherType(
        title = "Moderate Thunderstorm",
        icon = R.drawable.ic_thunder
    )
    
    data object SlightHailThunderstorm: WeatherType(
        title = "Thunderstorm With Slight Hail",
        icon = R.drawable.ic_rainy_thunder
    )
    
    data object HeavyHailThunderstorm: WeatherType(
        title = "Thunderstorm With Heavy Hail",
        icon = R.drawable.ic_rainy_thunder
    )

    companion object {

        fun fromWMO(code: Int) : WeatherType {

            return when(code) {

                0 -> {

                    ClearSky
                }

                1 -> {

                    MainlyClear
                }

                2 -> {

                    PartlyCloudy
                }

                3 -> {

                    Overcast
                }

                45 -> {

                    Foggy
                }

                48 -> {

                    DepositingRimeFog
                }

                51 -> {

                    LightDrizzle
                }

                53 -> {

                    ModerateDrizzle
                }

                55 -> {

                    DenseDrizzle
                }

                56 -> {

                    LightFreezingDrizzle
                }

                57 -> {

                    DenseFreezingDrizzle
                }

                61 -> {

                    SlightRain
                }

                63 -> {

                    ModerateRain
                }

                65 -> {

                    HeavyRain
                }

                66 -> {

                    LightFreezingDrizzle
                }

                67 -> {

                    HeavyFreezingRain
                }

                71 -> {

                    SlightSnowFall
                }

                73 -> {

                    ModerateSnowFall
                }

                75 -> {

                    HeavySnowFall
                }

                77 -> {

                    SnowGrains
                }

                80 -> {

                    SlightRainShowers
                }

                81 -> {

                    ModerateRainShowers
                }

                82 -> {

                    ViolentRainShowers
                }

                85 -> {

                    SlightSnowShowers
                }

                86 -> {

                    HeavySnowShowers
                }

                95 -> {

                    ModerateThunderstorm
                }

                96 -> {

                    SlightHailThunderstorm
                }

                99 -> {

                    HeavyHailThunderstorm
                }

                else -> {

                    ClearSky
                }
            }
        }
    }
}