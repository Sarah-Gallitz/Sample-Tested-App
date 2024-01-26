package au.sgallitz.pokedex.details.presentation.details

import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import au.sgallitz.pokedex.mvi.UiState

sealed class DetailsUiState : UiState {
    object Loading : DetailsUiState()
    data class HasData(val data: PokemonDetails) : DetailsUiState()
    data class HasError(val errorReason: ErrorReason) : DetailsUiState()
}
