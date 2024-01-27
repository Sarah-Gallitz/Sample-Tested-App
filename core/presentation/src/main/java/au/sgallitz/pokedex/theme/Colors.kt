package au.sgallitz.pokedex.theme

import android.content.Context
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.theme.Colors.getColorScheme

object Colors {
    @Composable
    fun getColorScheme(isDarkTheme: Boolean = isSystemInDarkTheme()): ColorScheme {
        return getColorScheme(LocalContext.current, isDarkTheme)
    }

    fun getColorScheme(context: Context, isDarkTheme: Boolean): ColorScheme {
        // Dynamic color is available on Android 12+
        val isDynamicColorAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

        return when {
            isDynamicColorAvailable && isDarkTheme ->
                dynamicDarkColorScheme(context)

            isDynamicColorAvailable && !isDarkTheme ->
                dynamicLightColorScheme(context)

            isDarkTheme ->
                darkColorScheme()

            else ->
                lightColorScheme()
        }
    }
}

@Preview(name = "Light Mode")
@Composable
private fun PreviewColors() {
    DrawColorScheme(getColorScheme(isDarkTheme = false))
}

@Preview(name = "Dark Mode")
@Composable
private fun PreviewDarkColors() {
    DrawColorScheme(getColorScheme(isDarkTheme = true))
}

@Composable
private fun DrawColorScheme(colors: ColorScheme) {
    Surface(
        color = colors.surface,
        contentColor = colors.onSurface
    ) {
        Column {
            DrawColorSwatch(colors.primary, "primary")
            DrawColorSwatch(colors.onPrimary, "onPrimary")
            DrawColorSwatch(colors.primaryContainer, "primaryContainer")
            DrawColorSwatch(colors.onPrimaryContainer, "onPrimaryContainer")

            DrawColorSwatch(colors.inversePrimary, "inversePrimary")

            DrawColorSwatch(colors.secondary, "secondary")
            DrawColorSwatch(colors.onSecondary, "onSecondary")
            DrawColorSwatch(colors.secondaryContainer, "secondaryContainer")
            DrawColorSwatch(colors.onSecondaryContainer, "onSecondaryContainer")

            DrawColorSwatch(colors.tertiary, "tertiary")
            DrawColorSwatch(colors.onTertiary, "onTertiary")
            DrawColorSwatch(colors.tertiaryContainer, "tertiaryContainer")
            DrawColorSwatch(colors.onTertiaryContainer, "onTertiaryContainer")

            DrawColorSwatch(colors.background, "background")
            DrawColorSwatch(colors.onBackground, "onBackground")

            DrawColorSwatch(colors.surface, "surface")
            DrawColorSwatch(colors.onSurface, "onSurface")
            DrawColorSwatch(colors.surfaceVariant, "surfaceVariant")
            DrawColorSwatch(colors.onSurfaceVariant, "onSurfaceVariant")
            DrawColorSwatch(colors.surfaceTint, "surfaceTint")
            DrawColorSwatch(colors.inverseSurface, "inverseSurface")
            DrawColorSwatch(colors.inverseOnSurface, "inverseOnSurface")

            DrawColorSwatch(colors.error, "error")
            DrawColorSwatch(colors.onError, "onError")
            DrawColorSwatch(colors.errorContainer, "errorContainer")
            DrawColorSwatch(colors.onErrorContainer, "onErrorContainer")

            DrawColorSwatch(colors.outline, "outline")
            DrawColorSwatch(colors.outlineVariant, "outlineVariant")

            DrawColorSwatch(colors.scrim, "scrim")
        }
    }
}

@Composable
private fun DrawColorSwatch(color: Color, name: String) {
    Row(
        Modifier.padding(
            horizontal = Spacing.Medium,
            vertical = Spacing.Small
        )
    ) {
        Box(
            Modifier
                .size(48.dp)
                .background(color, CircleShape)
                .border(1.dp, LocalContentColor.current.copy(alpha = 0.2f), CircleShape)
        )

        Column(
            Modifier.padding(start = Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.xSmall)
        ) {
            Text(name)
            Text(color.getHexCode(), color = LocalContentColor.current.copy(alpha = 0.6f))
        }
    }
}

private fun Color.getHexCode(): String {
    return "#" + Integer
        .toHexString(this.toArgb())
        .uppercase()
        // If alpha is 100% omit it
        .replace("""^FF""".toRegex(), "")
}
