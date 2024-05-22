package io.bash_psk.klimate.domain.events

import io.bash_psk.klimate.domain.state.MainUIState

sealed class MainUIEvent {

    data object GetWeatherInfo : MainUIEvent()

    data class SetMainUIState(
        val mainUIState: MainUIState
    ) : MainUIEvent()
}