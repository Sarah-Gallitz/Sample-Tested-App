package au.sgallitz.pokedex.home.data.repository

import au.sgallitz.pokedex.home.data.graphql.ListPokemonQuery
import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.repository.HomeRepository
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GraphqlHomeRepository(
    private val graphQlClient: ApolloClient
) : HomeRepository {
    override suspend fun get(pageNumber: Int, pageSize: Int): List<HomeItem> {
        return withContext(Dispatchers.IO) {
            graphQlClient
                .query(
                    ListPokemonQuery(
                        limit = Optional.present(pageSize),
                        offset = Optional.present(pageSize * pageNumber)
                    )
                )
                .toFlow()
                .map { it.data.toDomain() }
                .first()
        }
    }
}
