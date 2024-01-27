package au.sgallitz.pokedex.personalisation.data

import au.sgallitz.pokedex.personalisation.data.repository.Material3PokemonColorsRepository
import au.sgallitz.pokedex.personalisation.domain.repository.PokemonColorsRepository
import org.koin.dsl.module

object DiPersonalisationData {
    val module = module {
        single<PokemonColorsRepository> {
            Material3PokemonColorsRepository(get())
        }
    }
}
