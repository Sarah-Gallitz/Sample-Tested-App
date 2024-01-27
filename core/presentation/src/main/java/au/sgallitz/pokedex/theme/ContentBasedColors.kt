package au.sgallitz.pokedex.theme

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.view.ContextThemeWrapper
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import au.sgallitz.pokedex.components.loadImage
import au.sgallitz.pokedex.core.presentation.R
import au.sgallitz.pokedex.extensions.getColorFromAttribute
import com.google.android.material.color.DynamicColors
import com.google.android.material.color.DynamicColorsOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

 object ContentBasedColors {
    @Composable
     internal fun getColorScheme(
        bitmapUrl: URL?
    ): ColorScheme {
        if (bitmapUrl?.toString() == null) {
            return Colors.getColorScheme()
        }

        val cacheKey = bitmapUrl.toString() + if (isSystemInDarkTheme()) "-Light" else "-Dark"
        val cachedColors = ContentBasedColorCache.themes[cacheKey]
        if (cachedColors != null) {
            return cachedColors
        }

        val context = LocalContext.current
        val bitmap = remember { mutableStateOf<Bitmap?>(null) }
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                bitmap.value = context.loadImage(bitmapUrl)?.asAndroidBitmap()
            }
        }

        bitmap.value?.let {
            return getColorScheme(cacheKey = bitmapUrl.toString(), bitmap = it.asImageBitmap())
        }

        return Colors.getColorScheme()
    }

    @Composable
     internal fun getColorScheme(
        cacheKey: String,
        bitmap: ImageBitmap,
        isDarkTheme: Boolean = isSystemInDarkTheme()
    ): ColorScheme {
        val cacheKeyWithUiMode = cacheKey + if (isSystemInDarkTheme()) "-Light" else "-Dark"
        val cachedColors = ContentBasedColorCache.themes[cacheKeyWithUiMode]
        if (cachedColors != null) {
            return cachedColors
        }

        val context = LocalContext.current
        val colors =
            remember { mutableStateOf(Colors.getColorScheme(context, isDarkTheme = isDarkTheme)) }

        LaunchedEffect(bitmap) {
            // Call the suspend function using launch
            withContext(Dispatchers.IO) {
                // Dynamic color is available on Android 12+
                val isDynamicColorAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

                val contentColors = when {
                    isDynamicColorAvailable -> {
                        val themedContext = DynamicColors.wrapContextIfAvailable(
                            ContextThemeWrapper(
                                context,
                                R.style.Theme_Material3_DayNight_NoActionBar
                            ),
                            DynamicColorsOptions.Builder()
                                .setContentBasedSource(bitmap.asAndroidBitmap())
                                .build()
                        )

                        if (isDarkTheme) {
                            getDarkColorScheme(themedContext)
                        } else {
                            getLightColorScheme(themedContext)
                        }
                    }

                    isDarkTheme ->
                        darkColorScheme()

                    else ->
                        lightColorScheme()
                }


                colors.value = contentColors

                ContentBasedColorCache.themes[cacheKeyWithUiMode] = contentColors
            }
        }

        return colors.value
    }

     fun hasCachedColors( bitmapUrl: URL, isDarkTheme: Boolean): Boolean {
         val cacheKey = bitmapUrl.toString() + if (isDarkTheme) "-Light" else "-Dark"
         return ContentBasedColorCache.themes.containsKey(cacheKey)
     }

     suspend fun precacheContentBasedColorScheme(
         context: Context,
         bitmapUrl: URL,
         isDarkTheme: Boolean
     ) {
         val cacheKey = bitmapUrl.toString() + if (isDarkTheme) "-Light" else "-Dark"
         if (ContentBasedColorCache.themes.containsKey(cacheKey)) {
            return
         }

         // Call the suspend function using launch
         withContext(Dispatchers.IO) {
             // Dynamic color is available on Android 12+
             val isDynamicColorAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

             val bitmap = context.loadImage(bitmapUrl)?.asAndroidBitmap()
             if (bitmap == null) {
                 return@withContext
             }

             val contentColors = when {
                 isDynamicColorAvailable -> {
                     val themedContext = DynamicColors.wrapContextIfAvailable(
                         ContextThemeWrapper(
                             context,
                             R.style.Theme_Material3_DayNight_NoActionBar
                         ),
                         DynamicColorsOptions.Builder()
                             .setContentBasedSource(bitmap)
                             .build()
                     )

                     if (isDarkTheme) {
                         getDarkColorScheme(themedContext)
                     } else {
                         getLightColorScheme(themedContext)
                     }
                 }

                 isDarkTheme ->
                     darkColorScheme()

                 else ->
                     lightColorScheme()
             }

             ContentBasedColorCache.themes[cacheKey] = contentColors
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