package au.sgallitz.pokedex.details.domain.repository

import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import kotlinx.coroutines.flow.Flow

interface PokemonDetailsRepository {
    suspend fun get(pokemonId: Int): Flow<PokemonDetails>
}
