package au.sgallitz.pokedex.theme

import androidx.compose.runtime.Composable
import au.sgallitz.pokedex.theme.Styles.PokedexTheme

@Composable
fun ThemedPreview(content: @Composable () -> Unit) {
    PokedexTheme { content() }
}
