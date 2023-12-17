package au.sgallitz.pokedex.mvi

import androidx.navigation.NavController

interface NavigationRequest {
    fun navigate(navController: NavController)
}
