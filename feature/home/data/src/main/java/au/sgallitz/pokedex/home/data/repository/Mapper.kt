package au.sgallitz.pokedex.home.data.repository

import au.sgallitz.pokedex.home.data.graphql.ListPokemonQuery
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import java.net.URL

internal fun ListPokemonQuery.Data?.toDomain(): List<PokemonHomeItem> {
    return this?.pokemons?.results?.map { pokemon ->
        PokemonHomeItem(
            pokemonId = pokemon?.id ?: 1,
            name = pokemon?.name ?: "1",
            image = URL(pokemon!!.image!!),
            pixelArtImage = URL(pokemon!!.dreamworld!!)
        )
    } ?: emptyList()
}
