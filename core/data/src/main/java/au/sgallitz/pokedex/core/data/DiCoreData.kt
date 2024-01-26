package au.sgallitz.pokedex.core.data

import org.koin.dsl.module

object DiCoreData {
    val module = module {
        single { createGraphQlClient() }
    }
}
