package au.sgallitz.pokedex

import au.sgallitz.pokedex.details.presentation.details.DetailsScreen
import au.sgallitz.pokedex.home.presentation.homescreen.HomeScreen
import au.sgallitz.pokedex.navigation.Destinations

class AppDestinations : Destinations {
    override fun getHomeDestination(): String {
        return HomeScreen.destination()
    }

    override fun getDetailsDestination(pokemonId: Int): String {
        return DetailsScreen.destination(pokemonId)
    }
}
