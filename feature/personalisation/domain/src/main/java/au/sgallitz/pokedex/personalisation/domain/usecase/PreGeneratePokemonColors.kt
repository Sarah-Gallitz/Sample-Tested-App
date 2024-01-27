package au.sgallitz.pokedex.personalisation.domain.usecase

import au.sgallitz.pokedex.personalisation.domain.repository.PokemonColorsRepository

class PreGeneratePokemonColors(
    private val repository: PokemonColorsRepository
) {
    suspend fun execute(pokemonIds: List<Int>) {
        return repository.preGenerateColors(pokemonIds)
    }
}
