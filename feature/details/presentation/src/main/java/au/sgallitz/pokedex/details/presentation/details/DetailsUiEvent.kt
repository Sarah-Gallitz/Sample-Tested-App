package au.sgallitz.pokedex.details.presentation.details

import au.sgallitz.pokedex.mvi.UiEvent

sealed class DetailsUiEvent : UiEvent {
    data object BackPressed : DetailsUiEvent()
    data object BottomOfListReached : DetailsUiEvent()

    data object ShowMaleImage : DetailsUiEvent()
    data object ShowFemaleImage : DetailsUiEvent()
    data object ShowFront : DetailsUiEvent()
    data object ShowBack : DetailsUiEvent()
    data object ShowShiny : DetailsUiEvent()
    data object ShowNormal : DetailsUiEvent()
}
