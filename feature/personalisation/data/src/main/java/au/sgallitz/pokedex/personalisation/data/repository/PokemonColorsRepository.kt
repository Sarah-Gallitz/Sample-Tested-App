package au.sgallitz.pokedex.personalisation.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import au.sgallitz.pokedex.extensions.isSystemInDarkTheme
import au.sgallitz.pokedex.personalisation.data.colors.ContentBasedColors
import au.sgallitz.pokedex.personalisation.data.image.loadImage
import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import au.sgallitz.pokedex.personalisation.domain.repository.PokemonColorsRepository
import au.sgallitz.pokedex.theme.Colors
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.net.URL

class Material3PokemonColorsRepository(
    private val applicationContext: Context
) : PokemonColorsRepository {
    // Dynamic color is available on Android 12+
    private val isDynamicColorAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun getColors(
        pokemonId: Int
    ): Flow<PokemonColors> {
        val isDarkTheme = applicationContext.isSystemInDarkTheme()

        val defaultColors = Colors
            .getColorScheme(applicationContext, isDarkTheme)
            .toPokemonColors(isDarkTheme)

        if (!isDynamicColorAvailable) {
            return flowOf(defaultColors)
        } else {
            val cacheKey = "$pokemonId" + if (isDarkTheme) "-Dark" else "-Light"
            val cachedColors = PokemonColorsCache.inMemoryCache[cacheKey]

            val colors = MutableStateFlow(cachedColors ?: defaultColors)

            GlobalScope.launch {
                val contentBasedColors = generateColorScheme(
                    applicationContext,
                    pokemonId,
                    isDarkTheme
                )

                contentBasedColors?.let {
                    colors.emit(contentBasedColors)
                    PokemonColorsCache.inMemoryCache[cacheKey] = contentBasedColors
                }
            }

            return colors
        }
    }

    private val mutex = Mutex()

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun preGenerateColors(
        pokemonIds: List<Int>
    ) {
        GlobalScope.launch {
            mutex.withLock {
                if (isDynamicColorAvailable) {
                    pokemonIds.forEach { pokemonId ->
                        pokemonIds.first()
                        val isDarkTheme = applicationContext.isSystemInDarkTheme()
                        val cacheKey = "$pokemonId" + if (isDarkTheme) "-Dark" else "-Light"

                        println("XXXXXXX Starting color generation $cacheKey")
                        if (!PokemonColorsCache.inMemoryCache.containsKey(cacheKey)) {
                            val contentBasedColors = generateColorScheme(
                                applicationContext,
                                pokemonId,
                                isDarkTheme
                            )

                            contentBasedColors?.let {
                                println("XXXXXXX Completed color generation $cacheKey")
                                PokemonColorsCache.inMemoryCache[cacheKey] = contentBasedColors
                            }
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private suspend fun generateColorScheme(
        context: Context,
        pokemonId: Int,
        isDarkTheme: Boolean
    ): PokemonColors? {
        try {
            val image = withContext(Dispatchers.IO) {
                context.loadImage(
                    // user dreamworld image as color reference
                    URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$pokemonId.svg")
                )
            }

            image ?: return null

            return withContext(Dispatchers.Default) {
                ContentBasedColors
                    .generateColorScheme(
                        context = context,
                        bitmap = image,
                        isDarkTheme = isDarkTheme
                    )
                    .toPokemonColors(isDarkTheme)
            }
        } catch (e: Throwable) {
            return null
        }
    }
}

fun ColorScheme.toPokemonColors(
    isDarkTheme: Boolean
): PokemonColors {
    return PokemonColors(
        isDarkTheme = isDarkTheme,

        primary = primary.toHexCode(),
        onPrimary = onPrimary.toHexCode(),
        primaryContainer = primaryContainer.toHexCode(),
        onPrimaryContainer = onPrimaryContainer.toHexCode(),
        inversePrimary = inversePrimary.toHexCode(),
        secondary = secondary.toHexCode(),
        onSecondary = onSecondary.toHexCode(),
        secondaryContainer = secondaryContainer.toHexCode(),
        onSecondaryContainer = onSecondaryContainer.toHexCode(),
        tertiary = tertiary.toHexCode(),
        onTertiary = onTertiary.toHexCode(),
        tertiaryContainer = tertiaryContainer.toHexCode(),
        onTertiaryContainer = onTertiaryContainer.toHexCode(),
        background = background.toHexCode(),
        onBackground = onBackground.toHexCode(),
        surface = surface.toHexCode(),
        onSurface = onSurface.toHexCode(),
        surfaceVariant = surfaceVariant.toHexCode(),
        onSurfaceVariant = onSurfaceVariant.toHexCode(),
        surfaceTint = surfaceTint.toHexCode(),
        inverseSurface = inverseSurface.toHexCode(),
        inverseOnSurface = inverseOnSurface.toHexCode(),
        error = error.toHexCode(),
        onError = onError.toHexCode(),
        errorContainer = errorContainer.toHexCode(),
        onErrorContainer = onErrorContainer.toHexCode(),
        outline = outline.toHexCode(),
        outlineVariant = outlineVariant.toHexCode(),
        scrim = scrim.toHexCode()
    )
}

@OptIn(ExperimentalStdlibApi::class)
fun Color.toHexCode(): String {
    return "#" + this.toArgb().toHexString()
}
