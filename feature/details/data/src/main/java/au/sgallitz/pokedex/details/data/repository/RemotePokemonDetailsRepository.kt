package au.sgallitz.pokedex.details.data.repository

import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import au.sgallitz.pokedex.details.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import java.net.URL

class RemotePokemonDetailsRepository : PokemonDetailsRepository {
    override suspend fun get(pokemonId: Int): Flow<PokemonDetails> {
        return withContext(Dispatchers.IO) {
            flowOf(
                PokemonDetails(
                    pokemonId = pokemonId,
                    name = "Mock Pokemon",
                    image = URL("https://dummyimage.com/400x400/000/fff.jpg&text=Mock+Pic"),
                    pixelArtImage = URL("https://dummyimage.com/400x400/000/fff.jpg&text=Mock+Pic")
                )
            )
        }
    }
}
