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
            onShowMaleImage: () -> Unit,
            onShowFemaleImage: () -> Unit,
            onShowFront: () -> Unit,
            onShowBack: () -> Unit,
            onShowShiny: () -> Unit,
            onShowNormal: () -> Unit,
            scrollState: ScrollState = rememberScrollState(),
            paddingValues: PaddingValues = PaddingValues()
        ) {
            SmallDetailsView.Render(
                data = details,
                scrollState = scrollState,
                onShowMaleImage = onShowMaleImage,
                onShowFemaleImage = onShowFemaleImage,
                onShowFront = onShowFront,
                onShowBack = onShowBack,
                onShowShiny = onShowShiny,
                onShowNormal = onShowNormal,
                paddingValues = paddingValues
            )
        }
    }
}
