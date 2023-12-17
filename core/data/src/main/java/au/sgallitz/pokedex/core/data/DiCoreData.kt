package au.sgallitz.pokedex.core.data

import com.apollographql.apollo3.ApolloClient
import org.koin.dsl.module

object DiCoreData {
    val module = module {
        single {
            ApolloClient.Builder()
                .serverUrl("https://graphql-pokeapi.graphcdn.app/graphql")
                .build()
        }
    }
}
