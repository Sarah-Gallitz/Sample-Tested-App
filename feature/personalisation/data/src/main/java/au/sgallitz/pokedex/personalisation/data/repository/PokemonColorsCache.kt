package au.sgallitz.pokedex.personalisation.data.repository

import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import java.util.concurrent.ConcurrentHashMap

internal object PokemonColorsCache {
    val inMemoryCache = ConcurrentHashMap<String, PokemonColors>()
}
