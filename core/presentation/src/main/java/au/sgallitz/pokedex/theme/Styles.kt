package au.sgallitz.pokedex.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class Styles {
    companion object {
        @Composable
        fun PokedexTheme(
            isDarkTheme: Boolean = isSystemInDarkTheme(),
            content: @Composable () -> Unit
        ) {
            // Dynamic color is available on Android 12+
            val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            val colorScheme = when {
                dynamicColor && isDarkTheme ->
                    dynamicDarkColorScheme(LocalContext.current)
                dynamicColor && !isDarkTheme ->
                    dynamicLightColorScheme(LocalContext.current)
                isDarkTheme ->
                    darkColorScheme()
                else ->
                    lightColorScheme()
            }

            MaterialTheme(
                colorScheme = colorScheme,
                typography = typography,
                shapes = shapes,
                content = content
            )
        }
    }
}
