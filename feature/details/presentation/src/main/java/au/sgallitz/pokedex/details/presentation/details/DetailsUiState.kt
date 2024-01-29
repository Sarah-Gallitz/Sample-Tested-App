package au.sgallitz.pokedex.details.presentation.details

import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.details.domain.model.Images
import au.sgallitz.pokedex.mvi.UiState
import au.sgallitz.pokedex.personalisation.domain.model.PokemonColorSet
import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import java.net.URL

sealed class DetailsUiState : UiState {
    object Loading : DetailsUiState()
    data class HasData(val data: PokemonData) : DetailsUiState() {
        data class PokemonData(
            val id: Int,
            val pokemonName: String,
            val pokemonNumber: String,
            val isShowingShiny: Boolean,
            val isShowingFemale: Boolean,
            val isShowingBack: Boolean,
            val animations: Images,
            val pokemonColors: PokemonColors
        ) {
            val currentColorSet: PokemonColorSet
                get() = if (isShowingShiny) {
                    pokemonColors.shinyColors
                } else {
                    pokemonColors.normalColors
                }

            val animation: URL
                get() = when (Triple(isShowingFemale, isShowingBack, isShowingShiny)) {
                    Triple(false, false, false) -> animations.maleAnim
                    Triple(false, false, true) -> animations.maleAnimShiny
                    Triple(false, true, false) -> animations.maleAnimBack
                    Triple(false, true, true) -> animations.maleAnimShinyBack
                    Triple(true, false, false) -> animations.femaleAnim
                    Triple(true, false, true) -> animations.femaleAnimShiny
                    Triple(true, true, false) -> animations.femaleAnimBack
                    Triple(true, true, true) -> animations.femaleAnimShinyBack
                    else -> animations.maleAnim
                }

            val hasGenderDifference: Boolean
                get() = animations.femaleAnim.toString() != animations.maleAnim.toString() ||
                        animations.femaleAnimShiny.toString() != animations.maleAnimShiny.toString()
        }
    }

    data class HasError(val errorReason: ErrorReason) : DetailsUiState()
}
