package au.sgallitz.pokedex.home.domain.repository

import au.sgallitz.pokedex.home.domain.model.HomeItem

interface HomeRepository {
    suspend fun get(pageNumber: Int, pageSize: Int): List<HomeItem>
}
