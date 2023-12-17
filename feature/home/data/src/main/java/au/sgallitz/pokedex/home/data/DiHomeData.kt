package au.sgallitz.pokedex.home.data

import au.sgallitz.pokedex.home.data.repository.GraphqlHomeRepository
import au.sgallitz.pokedex.home.domain.repository.HomeRepository
import org.koin.dsl.module

object DiHomeData {
    val module = module {
        single<HomeRepository> { GraphqlHomeRepository(graphQlClient = get()) }
    }
}
