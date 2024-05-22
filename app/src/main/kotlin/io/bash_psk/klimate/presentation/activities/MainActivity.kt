package io.bash_psk.klimate.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import io.bash_psk.klimate.presentation.screen.WeatherScreen
import io.bash_psk.klimate.presentation.theme.KlimateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            KlimateTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding: PaddingValues ->

                    WeatherScreen(
                        modifier = Modifier.fillMaxSize()
                            .padding(paddingValues = innerPadding),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}