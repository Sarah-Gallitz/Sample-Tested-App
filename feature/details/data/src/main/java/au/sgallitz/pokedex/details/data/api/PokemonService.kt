package au.sgallitz.pokedex.details.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{pokemonId}/")
    suspend fun getPokemon(@Path("pokemonId") pokemonId: Int): Pokemon
}
