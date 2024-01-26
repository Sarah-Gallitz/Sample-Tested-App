package au.sgallitz.pokedex.details.domain.model

import java.net.URL

data class PokemonDetails(
    val pokemonId: Int,
    val name: String,
    val image: URL,
    val pixelArtImage: URL
)
