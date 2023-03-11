package au.sgallitz.pokedex.navigation

import android.content.Context
import android.content.Intent

interface ModuleDestinations {
    fun getHomeIntent(context: Context): Intent
}
