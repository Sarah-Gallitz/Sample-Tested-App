package au.sgallitz.pokedex.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.components.RemoteImage
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.theme.Spacing
import au.sgallitz.pokedex.theme.ThemedPreview
import java.net.URL

class PokemonView {
    companion object {
        @Composable
        fun RenderPokemonItem(item: PokemonHomeItem) {
            Card {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = Spacing.Medium,
                            vertical = Spacing.Large
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RemoteImage(
                        url = item.image,
                        modifier = Modifier.size(112.dp),
                        contentDescription = null // this is not important for a11y
                    )

                    Spacer(Modifier.size(Spacing.Medium))

                    Text(
                        text = item.name.replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewRenderPokemonItem() = ThemedPreview {
        RenderPokemonItem(
            item = PokemonHomeItem(
                1,
                "bulbasaur",
                URL("http://test.test"),
                URL("http://test.test")
            )
        )
    }
}
