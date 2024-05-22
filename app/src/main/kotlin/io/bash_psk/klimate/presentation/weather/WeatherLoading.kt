package io.bash_psk.klimate.presentation.weather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun WeatherLoading(
    isWeatherLoading: Boolean
) {

    AnimatedVisibility(
        visible = isWeatherLoading,
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {

        CircularProgressIndicator()
    }
}