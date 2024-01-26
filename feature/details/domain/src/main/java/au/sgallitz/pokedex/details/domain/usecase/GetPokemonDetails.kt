package au.sgallitz.pokedex.details.domain.usecase

import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import au.sgallitz.pokedex.details.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonDetails(
    private val repository: PokemonDetailsRepository
) {
    companion object {
        private const val PAGE_SIZE = 20
    }

    suspend fun execute(pokemonId: Int): Flow<PokemonDetails> {
        return repository.get(pokemonId)
    }
}
