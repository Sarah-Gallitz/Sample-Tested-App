package au.sgallitz.pokedex.home.data.repository

import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.home.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import java.net.URL

class GraphqlHomeRepository : HomeRepository {
    override suspend fun get(pageNumber: Int, pageSize: Int): List<HomeItem> {
        delay(2000)
        return listOf(
            PokemonHomeItem(
                pokemonId = 1,
                name = "bulbasaur",
                image = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg"),
                pixelArtImage = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
            ),
            PokemonHomeItem(
                pokemonId = 2,
                name = "ivysaur",
                image = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/2.svg"),
                pixelArtImage = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png")
            ),
            PokemonHomeItem(
                pokemonId = 3,
                name = "venusaur",
                image = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/3.svg"),
                pixelArtImage = URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png")
            )
        )
    }
}
