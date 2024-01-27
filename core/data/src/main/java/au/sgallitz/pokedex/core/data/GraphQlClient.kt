package au.sgallitz.pokedex.core.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory

fun createGraphQlClient(
    url: String
): ApolloClient {
    val memoryFirstThenSqlCacheFactory = MemoryCacheFactory(10 * 1024 * 1024)
        .chain(SqlNormalizedCacheFactory("apollo.db"))

    return ApolloClient.Builder()
        .serverUrl(url)
        .normalizedCache(memoryFirstThenSqlCacheFactory)
        .fetchPolicy(FetchPolicy.CacheAndNetwork)
        .build()
}
