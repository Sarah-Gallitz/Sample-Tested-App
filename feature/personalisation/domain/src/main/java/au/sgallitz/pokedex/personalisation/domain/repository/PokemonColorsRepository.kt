package au.sgallitz.pokedex.personalisation.domain.repository

import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import kotlinx.coroutines.flow.Flow

interface PokemonColorsRepository {
    suspend fun getColors(pokemonId: Int): Flow<PokemonColors>

    suspend fun preGenerateColors(pokemonIds: List<Int>)
}
