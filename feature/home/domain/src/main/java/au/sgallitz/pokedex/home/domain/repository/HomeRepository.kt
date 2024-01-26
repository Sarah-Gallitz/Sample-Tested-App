package au.sgallitz.pokedex.home.domain.repository

import au.sgallitz.pokedex.home.domain.model.HomeItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun get(pageNumber: Int, pageSize: Int): Flow<List<HomeItem>>
}
