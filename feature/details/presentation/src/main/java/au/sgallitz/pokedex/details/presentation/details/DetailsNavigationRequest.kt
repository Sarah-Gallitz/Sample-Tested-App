package au.sgallitz.pokedex.details.presentation.details

import androidx.navigation.NavController
import au.sgallitz.pokedex.extensions.closeCurrentScreen
import au.sgallitz.pokedex.mvi.NavigationRequest

sealed class DetailsNavigationRequest : NavigationRequest {
    object CloseDetails : DetailsNavigationRequest() {
        override fun navigate(navController: NavController) {
            navController.closeCurrentScreen()
        }
    }
}
