package au.sgallitz.pokedex.home.presentation.homescreen

import au.sgallitz.pokedex.mvi.UiEvent

sealed class HomeUiEvent : UiEvent {
    object BackPressed : HomeUiEvent()
    object BottomOfListReached : HomeUiEvent()
    data class PokemonPressed(val pokemonId: Int) : HomeUiEvent()
}
