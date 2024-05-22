package io.bash_psk.klimate.presentation.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.bash_psk.klimate.domain.resource.ConstantString
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherUnits
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecastView(
    weatherDataList: List<WeatherData>,
    weatherUnits: WeatherUnits
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "${ConstantString.TODAY} (${
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy"))
            }) :",
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {

            items(
                items = weatherDataList,
                key = { weatherData: WeatherData ->

                    weatherData.uuid
                }
            ) { weatherData: WeatherData ->

                WeatherHourlyView(
                    weatherData = weatherData,
                    weatherUnits = weatherUnits
                )
            }
        }
    }
}