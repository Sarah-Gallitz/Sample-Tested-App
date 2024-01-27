package au.sgallitz.pokedex.details.presentation.details

import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.mvi.UiState
import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import java.net.URL

sealed class DetailsUiState : UiState {
    object Loading : DetailsUiState()
    data class HasData(val data: PokemonData) : DetailsUiState() {
        data class PokemonData(
            val id: Int,
            val pokemonName: String,
            val pokemonNumber: String,
            val animation: URL,
            val pokemonColors: PokemonColors
        )
    }
    data class HasError(val errorReason: ErrorReason) : DetailsUiState()
}
