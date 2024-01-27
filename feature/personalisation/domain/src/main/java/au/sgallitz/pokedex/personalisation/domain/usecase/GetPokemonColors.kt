package au.sgallitz.pokedex.personalisation.domain.usecase

import au.sgallitz.pokedex.personalisation.domain.model.PokemonColors
import au.sgallitz.pokedex.personalisation.domain.repository.PokemonColorsRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonColors(
    private val repository: PokemonColorsRepository
) {
    suspend fun execute(pokemonId: Int): Flow<PokemonColors> {
        return repository.getColors(pokemonId)
    }
}
