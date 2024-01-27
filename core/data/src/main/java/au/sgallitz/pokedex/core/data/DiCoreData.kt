package au.sgallitz.pokedex.core.data

import com.apollographql.apollo3.ApolloClient
import org.koin.dsl.module
import retrofit2.Retrofit

object DiCoreData {
    val module = module {
        single<ApolloClient> {
            createGraphQlClient("https://graphql-pokeapi.graphcdn.app/graphql")
        }
        single<Retrofit> {
            createRetrofitClient("https://pokeapi.co/api/v2/")
        }
    }
}
