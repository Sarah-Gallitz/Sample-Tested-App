package au.sgallitz.pokedex.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable

object Styles {
    @Composable
    fun PokedexTheme(
        overrideColorScheme: ColorScheme? = null,
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colorScheme = overrideColorScheme ?: Colors.getColorScheme(isSystemInDarkTheme()),
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
