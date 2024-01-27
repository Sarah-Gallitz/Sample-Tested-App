package au.sgallitz.pokedex.theme

import androidx.compose.material3.ColorScheme
import java.util.concurrent.ConcurrentHashMap

internal object ContentBasedColorCache {
    val themes = ConcurrentHashMap<String, ColorScheme>()
}
