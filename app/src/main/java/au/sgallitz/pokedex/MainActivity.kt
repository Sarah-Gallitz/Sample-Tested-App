package au.sgallitz.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import au.sgallitz.pokedex.navigation.ModuleDestinations
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val destinations: ModuleDestinations by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeIntent = destinations.getHomeIntent(this)
        this.startActivity(homeIntent)

        finish()
    }
}
