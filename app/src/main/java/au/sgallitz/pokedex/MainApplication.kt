package au.sgallitz.pokedex

import android.app.Application
import au.sgallitz.pokedex.components.createRemoteImageLoader
import coil.ImageLoader
import coil.ImageLoaderFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application(), ImageLoaderFactory {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(Di.allModules)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return createRemoteImageLoader(this)
    }
}
