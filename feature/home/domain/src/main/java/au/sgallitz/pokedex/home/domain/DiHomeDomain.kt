package au.sgallitz.pokedex.home.domain

import au.sgallitz.pokedex.home.domain.usecase.GetHomeList
import org.koin.dsl.module

object DiHomeDomain {
    val module = module {
        factory {
            GetHomeList(homeRepository = get(), preGeneratePokemonColors = get())
        }
    }
}
