package au.sgallitz.pokedex.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable

internal object Styles {
    @Composable
    fun PokedexTheme(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colorScheme = Colors.getColorScheme(isDarkTheme),
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
