package au.sgallitz.pokedex.details.presentation.details.view.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import au.sgallitz.pokedex.details.domain.model.Images
import au.sgallitz.pokedex.details.presentation.details.DetailsUiState
import au.sgallitz.pokedex.personalisation.presentation.preview.getBulbasaurColors
import java.net.URL

class SamplePokemonDataProvider :
    PreviewParameterProvider<(Boolean) -> DetailsUiState.HasData.PokemonData> {
    override val values: Sequence<(Boolean) -> DetailsUiState.HasData.PokemonData>
        get() = sequenceOf(
            { isDarkTheme ->
                DetailsUiState.HasData.PokemonData(
                    id = 1,
                    pokemonName = "Bulbasaur",
                    pokemonNumber = "#00001",
                    isShowingShiny = false,
                    isShowingFemale = false,
                    isShowingBack = false,
                    animations = Images(
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/"),
                        URL("https://www.example.com/")
                    ),
                    pokemonColors = getBulbasaurColors(isDarkTheme)
                )
            }
        )
}