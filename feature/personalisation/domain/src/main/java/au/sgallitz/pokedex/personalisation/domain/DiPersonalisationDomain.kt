package au.sgallitz.pokedex.personalisation.domain

import au.sgallitz.pokedex.personalisation.domain.usecase.GetPokemonColors
import au.sgallitz.pokedex.personalisation.domain.usecase.PreGeneratePokemonColors
import org.koin.dsl.module

object DiPersonalisationDomain {
    val module = module {
        factory { GetPokemonColors(repository = get()) }
        factory { PreGeneratePokemonColors(repository = get()) }
    }
}
