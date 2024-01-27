package au.sgallitz.pokedex

import au.sgallitz.pokedex.core.data.DiCoreData
import au.sgallitz.pokedex.details.data.DiDetailsData
import au.sgallitz.pokedex.details.domain.DiDetailsDomain
import au.sgallitz.pokedex.details.presentation.DiDetailsPresentation
import au.sgallitz.pokedex.home.data.DiHomeData
import au.sgallitz.pokedex.home.domain.DiHomeDomain
import au.sgallitz.pokedex.home.presentation.DiHomePresentation
import au.sgallitz.pokedex.navigation.Destinations
import au.sgallitz.pokedex.personalisation.data.DiPersonalisationData
import au.sgallitz.pokedex.personalisation.domain.DiPersonalisationDomain
import org.koin.core.module.Module
import org.koin.dsl.module

object Di {
    private val appModule = module {
        single<Destinations> { AppDestinations() }
    }

    private val dataModules = listOf(
        DiCoreData.module,
        DiHomeData.module,
        DiDetailsData.module,
        DiPersonalisationData.module
    )

    private val domainModules = listOf(
        DiHomeDomain.module,
        DiDetailsDomain.module,
        DiPersonalisationDomain.module
    )

    private val presentationModules = listOf(
        DiHomePresentation.module,
        DiDetailsPresentation.module
    )

    val allModules: List<Module> = appModule
        .plus(dataModules)
        .plus(domainModules)
        .plus(presentationModules)
}
