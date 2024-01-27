package au.sgallitz.pokedex.personalisation.data.colors

import android.content.Context
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import au.sgallitz.pokedex.core.presentation.R
import au.sgallitz.pokedex.extensions.getColorFromAttribute
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.DynamicColorsOptions

object ContentBasedColors {
    @RequiresApi(31)
    suspend fun generateColorScheme(
        context: Context,
        bitmap: ImageBitmap,
        isDarkTheme: Boolean
    ): ColorScheme {
        val themedContext = DynamicColors.wrapContextIfAvailable(
            ContextThemeWrapper(
                context,
                R.style.Theme_Material3_DayNight_NoActionBar
            ),
            DynamicColorsOptions.Builder()
                .setContentBasedSource(bitmap.asAndroidBitmap())
                .build()
        )

        return if (isDarkTheme) {
            getDarkColorScheme(themedContext)
        } else {
            getLightColorScheme(themedContext)
        }
    }

    private fun getLightColorScheme(context: Context): ColorScheme {
        return lightColorScheme(
            primary = context.getColorFromAttribute(R.attr.colorPrimary),
            onPrimary = context.getColorFromAttribute(R.attr.colorOnPrimary),
            primaryContainer = context.getColorFromAttribute(R.attr.colorPrimaryContainer),
            onPrimaryContainer = context.getColorFromAttribute(R.attr.colorOnPrimaryContainer),
            inversePrimary = context.getColorFromAttribute(R.attr.colorPrimaryInverse),
            secondary = context.getColorFromAttribute(R.attr.colorSecondary),
            onSecondary = context.getColorFromAttribute(R.attr.colorOnSecondary),
            secondaryContainer = context.getColorFromAttribute(R.attr.colorSecondaryContainer),
            onSecondaryContainer = context.getColorFromAttribute(R.attr.colorOnSecondaryContainer),
            tertiary = context.getColorFromAttribute(R.attr.colorTertiary),
            onTertiary = context.getColorFromAttribute(R.attr.colorOnTertiary),
            tertiaryContainer = context.getColorFromAttribute(R.attr.colorTertiaryContainer),
            onTertiaryContainer = context.getColorFromAttribute(R.attr.colorOnTertiaryContainer),
            background = context.getColorFromAttribute(R.attr.colorSurface),
            onBackground = context.getColorFromAttribute(R.attr.colorOnSurface),
            surface = context.getColorFromAttribute(R.attr.colorSurface),
            onSurface = context.getColorFromAttribute(R.attr.colorOnSurface),
            surfaceVariant = context.getColorFromAttribute(R.attr.colorSurfaceVariant),
            onSurfaceVariant = context.getColorFromAttribute(R.attr.colorOnSurfaceVariant),
            surfaceTint = context.getColorFromAttribute(R.attr.colorSurfaceBright),
            inverseSurface = context.getColorFromAttribute(R.attr.colorSurfaceInverse),
            inverseOnSurface = context.getColorFromAttribute(R.attr.colorOnSurfaceInverse),
            error = context.getColorFromAttribute(R.attr.colorError),
            onError = context.getColorFromAttribute(R.attr.colorOnError),
            errorContainer = context.getColorFromAttribute(R.attr.colorErrorContainer),
            onErrorContainer = context.getColorFromAttribute(R.attr.colorOnErrorContainer),
            outline = context.getColorFromAttribute(R.attr.colorOutline),
            outlineVariant = context.getColorFromAttribute(R.attr.colorOutlineVariant),
            scrim = context.getColorFromAttribute(R.attr.scrimBackground)
        )
    }

    private fun getDarkColorScheme(context: Context): ColorScheme {
        return darkColorScheme(
            primary = context.getColorFromAttribute(R.attr.colorPrimary),
            onPrimary = context.getColorFromAttribute(R.attr.colorOnPrimary),
            primaryContainer = context.getColorFromAttribute(R.attr.colorPrimaryContainer),
            onPrimaryContainer = context.getColorFromAttribute(R.attr.colorOnPrimaryContainer),
            inversePrimary = context.getColorFromAttribute(R.attr.colorPrimaryInverse),
            secondary = context.getColorFromAttribute(R.attr.colorSecondary),
            onSecondary = context.getColorFromAttribute(R.attr.colorOnSecondary),
            secondaryContainer = context.getColorFromAttribute(R.attr.colorSecondaryContainer),
            onSecondaryContainer = context.getColorFromAttribute(R.attr.colorOnSecondaryContainer),
            tertiary = context.getColorFromAttribute(R.attr.colorTertiary),
            onTertiary = context.getColorFromAttribute(R.attr.colorOnTertiary),
            tertiaryContainer = context.getColorFromAttribute(R.attr.colorTertiaryContainer),
            onTertiaryContainer = context.getColorFromAttribute(R.attr.colorOnTertiaryContainer),
            background = context.getColorFromAttribute(R.attr.colorSurface),
            onBackground = context.getColorFromAttribute(R.attr.colorOnSurface),
            surface = context.getColorFromAttribute(R.attr.colorSurface),
            onSurface = context.getColorFromAttribute(R.attr.colorOnSurface),
            surfaceVariant = context.getColorFromAttribute(R.attr.colorSurfaceVariant),
            onSurfaceVariant = context.getColorFromAttribute(R.attr.colorOnSurfaceVariant),
            surfaceTint = context.getColorFromAttribute(R.attr.colorSurfaceBright),
            inverseSurface = context.getColorFromAttribute(R.attr.colorSurfaceInverse),
            inverseOnSurface = context.getColorFromAttribute(R.attr.colorOnSurfaceInverse),
            error = context.getColorFromAttribute(R.attr.colorError),
            onError = context.getColorFromAttribute(R.attr.colorOnError),
            errorContainer = context.getColorFromAttribute(R.attr.colorErrorContainer),
            onErrorContainer = context.getColorFromAttribute(R.attr.colorOnErrorContainer),
            outline = context.getColorFromAttribute(R.attr.colorOutline),
            outlineVariant = context.getColorFromAttribute(R.attr.colorOutlineVariant),
            scrim = context.getColorFromAttribute(R.attr.scrimBackground)
        )
    }
}
