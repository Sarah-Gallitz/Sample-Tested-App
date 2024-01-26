package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.navigation.NavController
import au.sgallitz.pokedex.mvi.NavigationRequest
import au.sgallitz.pokedex.navigation.Destinations

sealed class HomeNavigationRequest : NavigationRequest {
    data class OpenPokemon(
        val destinations: Destinations,
        val pokemonId: Int
    ) : HomeNavigationRequest() {
        override fun navigate(navController: NavController) {
            navController.navigate(destinations.getDetailsDestination(pokemonId))
        }
    }
}
