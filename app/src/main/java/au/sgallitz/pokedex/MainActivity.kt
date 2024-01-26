package au.sgallitz.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import au.sgallitz.pokedex.details.presentation.NavGraphDetails.pokemonDetailsGraph
import au.sgallitz.pokedex.home.presentation.NavGraphHome.homeGraph
import au.sgallitz.pokedex.navigation.Destinations
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val destinations: Destinations by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = destinations.getHomeDestination()
            ) {
                homeGraph(navController)
                pokemonDetailsGraph(navController)
            }
        }
    }
}
