package au.sgallitz.pokedex.personalisation.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import au.sgallitz.pokedex.personalisation.presentation.preview.getBulbasaurColors
import au.sgallitz.pokedex.theme.Styles.PokedexTheme
import au.sgallitz.pokedex.theme.ThemedPreview

class PokemonTheme {
    companion object {
        @Composable
        fun WithColors(
            colors: PokemonColors?,
            content: @Composable () -> Unit
        ) {
            PokedexTheme(
                overrideColorScheme = colors?.toMaterialColors(),
                content = content
            )
        }
    }

    @Preview("Bulbasaur colors light")
    @Composable
    private fun PreviewBulbasaur() = ThemedPreview {
        val colors = getBulbasaurColors(isSystemInDarkTheme())
        WithColors(colors) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Hello World")
                    }
                }
            }
        }
    }

    @Preview("Bulbasaur colors dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
    @Composable
    private fun PreviewBulbasaurDark() = ThemedPreview {
        val colors = getBulbasaurColors(isSystemInDarkTheme())
        WithColors(colors) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
            ) {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Hello World")
                    }
                }
            }
        }
    }
}

fun PokemonColors.toMaterialColors(): ColorScheme {
    return if (this.isDarkTheme) {
        darkColorScheme(
            primary = primary.toColor(),
            onPrimary = onPrimary.toColor(),
            primaryContainer = primaryContainer.toColor(),
            onPrimaryContainer = onPrimaryContainer.toColor(),
            inversePrimary = inversePrimary.toColor(),
            secondary = secondary.toColor(),
            onSecondary = onSecondary.toColor(),
            secondaryContainer = secondaryContainer.toColor(),
            onSecondaryContainer = onSecondaryContainer.toColor(),
            tertiary = tertiary.toColor(),
            onTertiary = onTertiary.toColor(),
            tertiaryContainer = tertiaryContainer.toColor(),
            onTertiaryContainer = onTertiaryContainer.toColor(),
            background = background.toColor(),
            onBackground = onBackground.toColor(),
            surface = surface.toColor(),
            onSurface = onSurface.toColor(),
            surfaceVariant = surfaceVariant.toColor(),
            onSurfaceVariant = onSurfaceVariant.toColor(),
            surfaceTint = surfaceTint.toColor(),
            inverseSurface = inverseSurface.toColor(),
            inverseOnSurface = inverseOnSurface.toColor(),
            error = error.toColor(),
            onError = onError.toColor(),
            errorContainer = errorContainer.toColor(),
            onErrorContainer = onErrorContainer.toColor(),
            outline = outline.toColor(),
            outlineVariant = outlineVariant.toColor(),
            scrim = scrim.toColor()
        )
    } else {
        lightColorScheme(
            primary = primary.toColor(),
            onPrimary = onPrimary.toColor(),
            primaryContainer = primaryContainer.toColor(),
            onPrimaryContainer = onPrimaryContainer.toColor(),
            inversePrimary = inversePrimary.toColor(),
            secondary = secondary.toColor(),
            onSecondary = onSecondary.toColor(),
            secondaryContainer = secondaryContainer.toColor(),
            onSecondaryContainer = onSecondaryContainer.toColor(),
            tertiary = tertiary.toColor(),
            onTertiary = onTertiary.toColor(),
            tertiaryContainer = tertiaryContainer.toColor(),
            onTertiaryContainer = onTertiaryContainer.toColor(),
            background = background.toColor(),
            onBackground = onBackground.toColor(),
            surface = surface.toColor(),
            onSurface = onSurface.toColor(),
            surfaceVariant = surfaceVariant.toColor(),
            onSurfaceVariant = onSurfaceVariant.toColor(),
            surfaceTint = surfaceTint.toColor(),
            inverseSurface = inverseSurface.toColor(),
            inverseOnSurface = inverseOnSurface.toColor(),
            error = error.toColor(),
            onError = onError.toColor(),
            errorContainer = errorContainer.toColor(),
            onErrorContainer = onErrorContainer.toColor(),
            outline = outline.toColor(),
            outlineVariant = outlineVariant.toColor(),
            scrim = scrim.toColor()
        )
    }
}

fun String.toColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}
