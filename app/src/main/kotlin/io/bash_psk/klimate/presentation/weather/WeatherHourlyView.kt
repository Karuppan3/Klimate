package io.bash_psk.klimate.presentation.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherUnits
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHourlyView(
    weatherData: WeatherData,
    weatherUnits: WeatherUnits
) {

    val formattedTime = remember(key1 = weatherData) {
        weatherData.time.format(DateTimeFormatter.ofPattern("hh:mm a")).uppercase()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formattedTime,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )

        Image(
            modifier = Modifier.size(size = 40.dp),
            imageVector = ImageVector.vectorResource(id = weatherData.weatherType.icon),
            contentDescription = weatherData.weatherType.title
        )

        Text(
            text = "${weatherData.temperature} ${weatherUnits.temperature_2m}",
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium,
            overflow = TextOverflow.Ellipsis
        )
    }
}