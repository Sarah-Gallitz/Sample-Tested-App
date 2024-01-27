package au.sgallitz.pokedex.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import java.net.URL

object Styles {
    @Composable
    internal fun PokedexTheme(
        colorScheme: ColorScheme = Colors.getColorScheme(isSystemInDarkTheme()),
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }

    @Composable
    fun ContentColoredTheme(
        bitmapUrl: URL?,
        content: @Composable () -> Unit
    ) {
        PokedexTheme(
            colorScheme = ContentBasedColors.getColorScheme(bitmapUrl = bitmapUrl),
            content = content
        )
    }
}
