package au.sgallitz.pokedex.details.domain.model

import java.net.URL

data class PokemonDetails(
    val pokemonId: Int,
    val name: String,
    val images: Images
)

data class Images(
    val maleAnim: URL,
    val maleAnimShiny: URL,
    val femaleAnim: URL,
    val femaleAnimShiny: URL,
    val maleAnimBack: URL,
    val maleAnimShinyBack: URL,
    val femaleAnimBack: URL,
    val femaleAnimShinyBack: URL
)
