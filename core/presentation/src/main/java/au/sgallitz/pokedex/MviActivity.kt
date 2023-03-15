package au.sgallitz.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import au.sgallitz.pokedex.mvi.MviComponentProvider
import au.sgallitz.pokedex.mvi.MviScreen
import au.sgallitz.pokedex.theme.Styles.Companion.PokedexTheme

abstract class MviActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = screen.route
            ) {
                composable(screen.route, arguments = listOf()) {
                    val componentProvider = MviComponentProvider(navController, screen.scope)
                    PokedexTheme {
                        componentProvider.ShowComponent(component = screen)
                    }
                }
            }
        }
    }

    abstract val screen: MviScreen<*, *, *>
}
