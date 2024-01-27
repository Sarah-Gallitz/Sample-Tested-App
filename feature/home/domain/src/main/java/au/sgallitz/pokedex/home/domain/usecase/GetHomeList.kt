package au.sgallitz.pokedex.home.domain.usecase

import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.home.domain.repository.HomeRepository
import au.sgallitz.pokedex.personalisation.domain.usecase.PreGeneratePokemonColors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class GetHomeList(
    private val homeRepository: HomeRepository,
    private val preGeneratePokemonColors: PreGeneratePokemonColors
) {
    companion object {
        private const val PAGE_SIZE = 20
    }

    private val items = MutableStateFlow(emptyList<HomeItem>())
    private var currentPage = 1

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun execute(): Flow<List<HomeItem>> {
        fetchNext()
        return items
    }

    suspend fun fetchNext() {
        println("XXXXXX Fetching $currentPage")
        val page = homeRepository
            .get(
                pageNumber = currentPage,
                pageSize = PAGE_SIZE
            )
            .first()

        items.emit(items.value.plus(page))

        preGeneratePokemonColors.execute(
            page.filterIsInstance<PokemonHomeItem>().map { it.pokemonId }
        )

        println("XXXXXX Page $currentPage with items ${page.size}")

        currentPage++
    }
}
