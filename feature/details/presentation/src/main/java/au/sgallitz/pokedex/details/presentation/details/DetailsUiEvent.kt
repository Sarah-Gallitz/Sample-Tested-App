package au.sgallitz.pokedex.details.presentation.details

import au.sgallitz.pokedex.mvi.UiEvent

sealed class DetailsUiEvent : UiEvent {
    object BackPressed : DetailsUiEvent()
    object BottomOfListReached : DetailsUiEvent()
}
