package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.navigation.NavController
import au.sgallitz.pokedex.mvi.NavigationRequest
import au.sgallitz.pokedex.navigation.closeCurrentScreen

sealed class HomeNavigationRequest : NavigationRequest {
    object CloseHome : HomeNavigationRequest() {
        override fun navigate(navController: NavController) {
            navController.closeCurrentScreen()
        }
    }
}
