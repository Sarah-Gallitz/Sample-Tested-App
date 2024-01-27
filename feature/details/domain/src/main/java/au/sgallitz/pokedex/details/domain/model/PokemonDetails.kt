package au.sgallitz.pokedex.details.domain.model

import java.net.URL

data class PokemonDetails(
    val pokemonId: Int,
    val name: String,
    val images: Images
)

data class Images(
    val dreamworld: URL,
    val femaleAnim: URL,
    val maleAnim: URL,
    val femaleAnimShiny: URL,
    val maleAnimShiny: URL
)
