package au.sgallitz.pokedex.details.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import au.sgallitz.pokedex.details.presentation.details.DetailsScreen
import au.sgallitz.pokedex.extensions.buildGraph

object NavGraphDetails {
    fun NavGraphBuilder.pokemonDetailsGraph(navController: NavController) {
        buildGraph(navController, listOf(DetailsScreen()))
    }
}
