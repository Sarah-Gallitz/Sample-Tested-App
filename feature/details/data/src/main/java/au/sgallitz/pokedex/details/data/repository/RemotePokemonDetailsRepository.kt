package au.sgallitz.pokedex.details.data.repository

import au.sgallitz.pokedex.core.domain.DomainException
import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.details.data.api.PokemonService
import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import au.sgallitz.pokedex.details.domain.repository.PokemonDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class RemotePokemonDetailsRepository(
    val retrofit: Retrofit
) : PokemonDetailsRepository {
    private val service: PokemonService by lazy {
        retrofit.create(PokemonService::class.java)
    }
    override suspend fun get(pokemonId: Int): Flow<PokemonDetails> {
        return withContext(Dispatchers.IO) {
            try {
                flowOf(
                    service.getPokemon(pokemonId).toDomain()
                )
            } catch (e: Throwable) {
                throw DomainException(ErrorReason.GeneralError())
            }
        }
    }
}
