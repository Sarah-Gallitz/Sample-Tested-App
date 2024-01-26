package au.sgallitz.pokedex.home.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import au.sgallitz.pokedex.extensions.buildGraph
import au.sgallitz.pokedex.home.presentation.homescreen.HomeScreen

object NavGraphHome {
    fun NavGraphBuilder.homeGraph(navController: NavController) {
        buildGraph(navController, listOf(HomeScreen()))
    }
}
