package au.sgallitz.pokedex.home.domain.usecase

import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.net.URL

class GetHomeList() {
    suspend fun execute(): Flow<List<HomeItem>> {
        delay(1000)
        return flowOf(
            listOf(
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
        )
    }
}
