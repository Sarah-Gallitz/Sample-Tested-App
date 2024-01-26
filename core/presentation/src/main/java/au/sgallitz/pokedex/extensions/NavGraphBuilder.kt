package au.sgallitz.pokedex.extensions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import au.sgallitz.pokedex.mvi.MviComponentProvider
import au.sgallitz.pokedex.mvi.MviScreen
import au.sgallitz.pokedex.theme.Styles

fun NavGraphBuilder.buildGraph(navController: NavController, screens: List<MviScreen<*, *, *>>) {
    screens.forEach { screen ->
        composable(screen.route, arguments = screen.routeArguments) {
            val componentProvider = MviComponentProvider(navController, screen.scope)
            Styles.PokedexTheme {
                componentProvider.ShowComponent(component = screen)
            }
        }
    }
}
