package au.sgallitz.pokedex.details.domain

import au.sgallitz.pokedex.details.domain.usecase.GetPokemonDetails
import org.koin.dsl.module

object DiDetailsDomain {
    val module = module {
        factory { GetPokemonDetails(repository = get()) }
    }
}
