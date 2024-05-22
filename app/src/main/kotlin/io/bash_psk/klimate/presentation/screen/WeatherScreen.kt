package io.bash_psk.klimate.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.bash_psk.klimate.domain.events.MainUIEvent
import io.bash_psk.klimate.presentation.activities.MainViewModel
import io.bash_psk.klimate.presentation.weather.WeatherCardView
import io.bash_psk.klimate.presentation.weather.WeatherForecastView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel
) {

    val pullToRefreshState = rememberPullToRefreshState()

    val mainUIState by mainViewModel.mainUIState.collectAsStateWithLifecycle()

    LaunchedEffect(
        key1 = mainUIState.isLoading
    ) {

        when (mainUIState.isLoading) {

            true -> {

                pullToRefreshState.startRefresh()
            }

            false -> {

                pullToRefreshState.endRefresh()
            }
        }
    }

    LaunchedEffect(
        key1 = mainUIState.weatherForecast.weatherDataList.isEmpty(),
        key2 = mainUIState.isLoading
    ) {

        when {

            mainUIState.weatherForecast.weatherDataList.isEmpty() && mainUIState.isLoading.not() -> {

                val getWeatherInfoMainUIEvent = MainUIEvent.GetWeatherInfo

                mainViewModel.onMainUIEvent(mainUIEvent = getWeatherInfoMainUIEvent)
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = PaddingValues(
                        vertical = 8.dp,
                        horizontal = 8.dp
                    )
                ).nestedScroll(connection = pullToRefreshState.nestedScrollConnection),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            WeatherCardView(
                weatherData = mainUIState.weatherData,
                weatherUnits = mainUIState.weatherForecast.weatherUnits
            )

            WeatherForecastView(
                weatherDataList = mainUIState.weatherForecast.weatherDataList[0] ?: emptyList(),
                weatherUnits = mainUIState.weatherForecast.weatherUnits
            )
        }

        AnimatedVisibility(
            modifier = Modifier.align(alignment = Alignment.TopCenter),
            visible = mainUIState.isLoading,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {

            PullToRefreshContainer(
                modifier = Modifier.align(alignment = Alignment.TopCenter),
                state = pullToRefreshState
            )
        }

//        WeatherLoading(isWeatherLoading = mainUIState.isLoading)
    }
}