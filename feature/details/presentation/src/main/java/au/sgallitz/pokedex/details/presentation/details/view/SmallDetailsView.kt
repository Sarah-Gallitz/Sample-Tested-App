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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.components.ScalableRemoteImage
import au.sgallitz.pokedex.details.presentation.details.DetailsUiState.HasData.PokemonData
import au.sgallitz.pokedex.personalisation.presentation.PokemonTheme
import au.sgallitz.pokedex.personalisation.presentation.preview.getBulbasaurColors
import au.sgallitz.pokedex.theme.ThemedPreview
import java.net.URL

class SmallDetailsView {
    companion object {
        @Composable
        fun Render(
            details: PokemonData,
            scrollState: ScrollState = rememberScrollState(),
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
                            .padding(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        ScalableRemoteImage(
                            url = details.animation,
                            scale = ScaleFactor(2f, 2f),
                            contentDescription = null // not important for a11y
                        )
                    }

                    Column(
                        Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Rounded.MailOutline, "male image")
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Rounded.MailOutline, "female image")
                        }

                        Spacer(Modifier.size(16.dp))

                        IconButton(onClick = { /*TODO*/ }) {
                            val contentDescription =
                                Icon(Icons.Rounded.MailOutline, "shiny image")
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(details.pokemonName, style = MaterialTheme.typography.titleLarge)

                    Spacer(Modifier.weight(1f))

                    Text(details.pokemonNumber, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
    @Composable
    private fun PreviewRenderLight() = ThemedPreview {
        PokemonTheme.WithColors(getBulbasaurColors(isSystemInDarkTheme())) {
            Surface {
                Render(
                    PokemonData(
                        1,
                        "Bulbasaur",
                        "#0001",
                        URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/1.gif"),
                        getBulbasaurColors(isSystemInDarkTheme())
                    )
                )
            }
        }
    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun PreviewRenderDark() = ThemedPreview {
        PokemonTheme.WithColors(getBulbasaurColors(isSystemInDarkTheme())) {
            Surface {
                Render(
                    PokemonData(
                        1,
                        "Bulbasaur",
                        "#0001",
                        URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/1.gif"),
                        getBulbasaurColors(isSystemInDarkTheme())
                    )
                )
            }
        }
    }
}
