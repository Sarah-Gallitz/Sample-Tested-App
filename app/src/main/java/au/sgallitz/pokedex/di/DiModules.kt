package au.sgallitz.pokedex.di

import au.sgallitz.pokedex.navigation.AppModuleDestinations
import au.sgallitz.pokedex.navigation.ModuleDestinations
import org.koin.core.module.Module
import org.koin.dsl.module

object DiModules {
    private val appModule = module {
        single<ModuleDestinations> { AppModuleDestinations() }
    }

    private val dataModules = listOf<Module>()

    private val domainModules = listOf<Module>()

    private val presentationModules = listOf<Module>(

    )

    val allModules: List<Module> = appModule
        .plus(dataModules)
        .plus(domainModules)
        .plus(presentationModules)
}
