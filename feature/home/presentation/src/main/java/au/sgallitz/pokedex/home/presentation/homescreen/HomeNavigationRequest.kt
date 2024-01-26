package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.navigation.NavController
import au.sgallitz.pokedex.extensions.closeCurrentScreen
import au.sgallitz.pokedex.mvi.NavigationRequest
import au.sgallitz.pokedex.navigation.Destinations

sealed class HomeNavigationRequest : NavigationRequest {
    object CloseHome : HomeNavigationRequest() {
        override fun navigate(navController: NavController) {
            navController.closeCurrentScreen()
        }
    }
    data class OpenPokemon(
        val destinations: Destinations,
        val pokemonId: Int
    ) : HomeNavigationRequest() {
        override fun navigate(navController: NavController) {
            navController.navigate(destinations.getDetailsDestination(pokemonId))
        }
    }
}
