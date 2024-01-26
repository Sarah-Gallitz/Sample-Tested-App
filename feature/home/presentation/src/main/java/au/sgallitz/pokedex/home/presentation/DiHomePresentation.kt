package au.sgallitz.pokedex.home.presentation

import au.sgallitz.pokedex.home.presentation.homescreen.HomeScreen
import au.sgallitz.pokedex.home.presentation.homescreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object DiHomePresentation {
    val module = module {
        scope<HomeScreen> {
            viewModelOf(::HomeViewModel)
        }
    }
}
