package au.sgallitz.pokedex.details.presentation

import au.sgallitz.pokedex.details.presentation.details.DetailsScreen
import au.sgallitz.pokedex.details.presentation.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object DiDetailsPresentation {
    val module = module {
        scope<DetailsScreen> {
            viewModelOf(::DetailsViewModel)
        }
    }
}
