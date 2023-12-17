package au.sgallitz.pokedex.home.domain.usecase

import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class GetHomeList(
    private val homeRepository: HomeRepository
) {
    companion object {
        private const val PAGE_SIZE = 20
    }

    private val items = MutableStateFlow(emptyList<HomeItem>())
    private var currentPage = 1

    suspend fun execute(): Flow<List<HomeItem>> {
        fetchNext()
        return items
    }

    suspend fun fetchNext() {
        val page = homeRepository.get(
            pageNumber = currentPage,
            pageSize = PAGE_SIZE
        )

        items.value = items.value.plus(page)
        currentPage++
    }
}
