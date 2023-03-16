package au.sgallitz.pokedex.home.domain.model

import java.net.URL

data class PokemonHomeItem(
    val pokemonId: Int,
    val name: String,
    val image: URL,
    val pixelArtImage: URL
) : HomeItem()
