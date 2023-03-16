package au.sgallitz.pokedex.home.presentation

import au.sgallitz.pokedex.MviActivity
import au.sgallitz.pokedex.home.presentation.homescreen.HomeScreen

class HomeActivity : MviActivity() {
    override val screen = HomeScreen()
}
