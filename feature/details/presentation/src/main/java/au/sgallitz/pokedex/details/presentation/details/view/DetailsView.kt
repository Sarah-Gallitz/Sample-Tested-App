package au.sgallitz.pokedex.details.presentation.details.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import au.sgallitz.pokedex.details.presentation.details.DetailsUiState.HasData.PokemonData

class DetailsView {
    companion object {
        @Composable
        fun Render(
            details: PokemonData,
            scrollState: ScrollState = rememberScrollState(),
            paddingValues: PaddingValues = PaddingValues()
        ) {
            SmallDetailsView.Render(details, scrollState, paddingValues)
        }
    }
}
