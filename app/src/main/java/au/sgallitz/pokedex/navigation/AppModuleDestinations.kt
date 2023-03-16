package au.sgallitz.pokedex.navigation

import android.content.Context
import android.content.Intent
import au.sgallitz.pokedex.home.presentation.HomeActivity

class AppModuleDestinations : ModuleDestinations {
    override fun getHomeIntent(context: Context): Intent {
        return Intent(context, HomeActivity::class.java)
    }
}
