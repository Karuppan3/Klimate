package io.bash_psk.klimate.presentation.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.bash_psk.klimate.R
import io.bash_psk.klimate.domain.resource.ConstantDesc
import io.bash_psk.klimate.domain.weather.WeatherData
import io.bash_psk.klimate.domain.weather.WeatherUnits
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCardView(
    weatherData: WeatherData,
    weatherUnits: WeatherUnits
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.elevatedCardColors(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    paddingValues = PaddingValues(
                        vertical = 12.dp,
                        horizontal = 12.dp
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            Text(
                modifier = Modifier.align(alignment = Alignment.End),
                text = weatherData.time.format(
                    DateTimeFormatter.ofPattern("hh:mm:ss a")
                ).uppercase(),
                maxLines = 1,
                style = MaterialTheme.typography.labelSmall,
                overflow = TextOverflow.Ellipsis
            )

            Image(
                modifier = Modifier.size(size = 200.dp),
                painter = painterResource(id = weatherData.weatherType.icon),
                contentDescription = weatherData.weatherType.title
            )

            Text(
                text = "${weatherData.temperature} ${weatherUnits.temperature_2m}",
                maxLines = 1,
                style = MaterialTheme.typography.displayLarge,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = weatherData.weatherType.title,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {

                    Icon(
                        modifier = Modifier.size(size = 20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        contentDescription = ConstantDesc.PRESSURE_ICON
                    )

                    Text(
                        text = "${weatherData.pressure.roundToInt()} ${weatherUnits.pressure_msl}",
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {

                    Icon(
                        modifier = Modifier.size(size = 20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        contentDescription = ConstantDesc.HUMIDITY_ICON
                    )

                    Text(
                        text = "${weatherData.humidity.roundToInt()} ${weatherUnits.relativehumidity_2m}",
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(space = 4.dp)
                ) {

                    Icon(
                        modifier = Modifier.size(size = 20.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        contentDescription = ConstantDesc.WIND_ICON
                    )

                    Text(
                        text = "${weatherData.windSpeed.roundToInt()} ${weatherUnits.windspeed_10m}",
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}