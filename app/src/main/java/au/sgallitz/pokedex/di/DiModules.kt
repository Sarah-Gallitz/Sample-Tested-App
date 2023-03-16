package au.sgallitz.pokedex.di

import au.sgallitz.pokedex.home.data.DiHomeData
import au.sgallitz.pokedex.home.domain.DiHomeDomain
import au.sgallitz.pokedex.home.presentation.DiHomePresentation
import au.sgallitz.pokedex.navigation.AppModuleDestinations
import au.sgallitz.pokedex.navigation.ModuleDestinations
import org.koin.core.module.Module
import org.koin.dsl.module

object DiModules {
    private val appModule = module {
        single<ModuleDestinations> { AppModuleDestinations() }
    }

    private val dataModules = listOf(
        DiHomeData.module
    )

    private val domainModules = listOf(
        DiHomeDomain.module
    )

    private val presentationModules = listOf(
        DiHomePresentation.module
    )

    val allModules: List<Module> = appModule
        .plus(dataModules)
        .plus(domainModules)
        .plus(presentationModules)
}
