package au.sgallitz.pokedex.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.theme.Spacing
import java.net.URL

class PokemonView {
    companion object {
        @Composable
        fun RenderPokemonItem(item: PokemonHomeItem) {
            Card(
                Modifier.padding(Spacing.Medium)
            ) {
                Text(text = item.name)
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewRenderPokemonItem() {
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
