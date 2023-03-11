package au.sgallitz.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import au.sgallitz.pokedex.navigation.AppModuleDestinations

class MainActivity : ComponentActivity() {
    private val destinations = AppModuleDestinations()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeIntent = destinations.getHomeIntent(this)
        this.startActivity(homeIntent)

        finish()
    }
}
