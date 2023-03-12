package au.sgallitz.pokedex

import android.app.Application
import au.sgallitz.pokedex.di.DiModules
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(DiModules.allModules)
        }
    }
}
