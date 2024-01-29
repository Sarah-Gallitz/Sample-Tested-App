package au.sgallitz.pokedex.details.presentation.details.view

import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.components.ScalableRemoteImage
import au.sgallitz.pokedex.details.presentation.R
import au.sgallitz.pokedex.details.presentation.details.DetailsUiState.HasData.PokemonData
import au.sgallitz.pokedex.details.presentation.details.view.preview.SamplePokemonDataProvider
import au.sgallitz.pokedex.personalisation.presentation.PokemonTheme
import au.sgallitz.pokedex.theme.ThemedPreview

class SmallDetailsView {
    companion object {
        @Composable
        fun Render(
            data: PokemonData,
            scrollState: ScrollState = rememberScrollState(),
            onShowMaleImage: () -> Unit,
            onShowFemaleImage: () -> Unit,
            onShowFront: () -> Unit,
            onShowBack: () -> Unit,
            onShowShiny: () -> Unit,
            onShowNormal: () -> Unit,
            paddingValues: PaddingValues = PaddingValues()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValues.calculateTopPadding())
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(Modifier.height(IntrinsicSize.Max)) {
                    Box(
                        Modifier
                            .background(
                                MaterialTheme.colorScheme.surfaceVariant,
                                MaterialTheme.shapes.medium
                            )
                            .weight(1f)
                            .defaultMinSize(minHeight = 200.dp)
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ScalableRemoteImage(
                            url = data.animation,
                            scale = ScaleFactor(2f, 2f),
                            contentDescription = null // not important for a11y
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(
                            shape = CircleShape,
                            tonalElevation = 6.dp
                        ) {
                            Column {
                                IconButton(
                                    enabled = data.hasGenderDifference,
                                    onClick = { onShowMaleImage() }
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                            .background(
                                                if (!data.isShowingFemale && data.hasGenderDifference) {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                } else {
                                                    MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                        6.dp
                                                    )
                                                },
                                                CircleShape
                                            )
                                    )

                                    Icon(
                                        painter = painterResource(R.drawable.ic_male),
                                        contentDescription = "male image",
                                        tint = if (!data.isShowingFemale && data.hasGenderDifference) {
                                            MaterialTheme.colorScheme.onPrimaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        }
                                    )
                                }

                                IconButton(
                                    enabled = data.hasGenderDifference,
                                    onClick = { onShowFemaleImage() }
                                ) {
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                            .background(
                                                if (data.isShowingFemale && data.hasGenderDifference) {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                } else {
                                                    MaterialTheme.colorScheme.surfaceColorAtElevation(
                                                        6.dp
                                                    )
                                                },
                                                CircleShape
                                            )
                                    )

                                    Icon(
                                        painterResource(R.drawable.ic_female),
                                        "female image",
                                        tint = if (data.isShowingFemale && data.hasGenderDifference) {
                                            MaterialTheme.colorScheme.onPrimaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.onSurface
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(8.dp))

                        IconButton(onClick = {
                            if (data.isShowingBack) {
                                onShowFront()
                            } else {
                                onShowBack()
                            }
                        }) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                                    .background(
                                        MaterialTheme.colorScheme.secondaryContainer,
                                        CircleShape
                                    )
                            )

                            Icon(
                                painterResource(
                                    if (data.isShowingBack) {
                                        R.drawable.ic_back
                                    } else {
                                        R.drawable.ic_front
                                    }
                                ),
                                "front",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }


                        IconButton(onClick = {
                            if (data.isShowingShiny) {
                                onShowNormal()
                            } else {
                                onShowShiny()
                            }
                        }) {
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(4.dp)
                                    .background(
                                        if (data.isShowingShiny) {
                                            MaterialTheme.colorScheme.primaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.secondaryContainer
                                        },
                                        CircleShape
                                    )
                            )
                            if (data.isShowingShiny) {
                                Icon(
                                    painterResource(R.drawable.ic_shiny_filled),
                                    "Shiny image showing",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            } else {
                                Icon(
                                    painterResource(R.drawable.ic_shiny_outline),
                                    "Normal image showing",
                                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }

                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(data.pokemonName, style = MaterialTheme.typography.titleLarge)

                    Spacer(Modifier.weight(1f))

                    Text(data.pokemonNumber, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }

    @Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun PreviewRender(@PreviewParameter(SamplePokemonDataProvider::class) makePokemon: (Boolean) -> PokemonData) =
        ThemedPreview {
            val pokemon = makePokemon(isSystemInDarkTheme())
            PokemonTheme.WithColors(pokemon.currentColorSet) {
                Surface {
                    Render(
                        data = pokemon,
                        scrollState = rememberScrollState(),
                        onShowMaleImage = {},
                        onShowFemaleImage = {},
                        onShowFront = {},
                        onShowBack = {},
                        onShowShiny = {},
                        onShowNormal = {}
                    )
                }
            }
        }
}
