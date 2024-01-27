package au.sgallitz.pokedex.details.data.repository

import au.sgallitz.pokedex.details.data.api.Pokemon
import au.sgallitz.pokedex.details.domain.model.Images
import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import java.net.URL

internal fun Pokemon.toDomain(): PokemonDetails {
    return PokemonDetails(
        pokemonId = this.id,
        name = this.name,
        images = Images(
            dreamworld = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/$id.svg"),
            femaleAnim = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id.gif"),
            maleAnim = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/$id.gif"),
            femaleAnimShiny = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/$id.gif"),
            maleAnimShiny = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/$id.gif")
        )
    )
}
