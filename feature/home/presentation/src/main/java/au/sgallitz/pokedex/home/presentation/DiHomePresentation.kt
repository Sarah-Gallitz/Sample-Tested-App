package au.sgallitz.pokedex.home.presentation

import au.sgallitz.pokedex.home.presentation.homescreen.HomeScreen
import au.sgallitz.pokedex.home.presentation.homescreen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object DiHomePresentation {
    val module = module {
        scope<HomeScreen> {
            viewModel {
                HomeViewModel(
                    getHomeList = get()
                )
            }
        }
    }
}
