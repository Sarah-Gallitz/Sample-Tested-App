package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.lifecycle.viewModelScope
import au.sgallitz.pokedex.core.domain.DomainException
import au.sgallitz.pokedex.home.domain.usecase.GetHomeList
import au.sgallitz.pokedex.mvi.MviViewModel
import au.sgallitz.pokedex.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val destinations: Destinations,
    private val getHomeList: GetHomeList
) : MviViewModel<HomeUiState, HomeUiEvent, HomeNavigationRequest>() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    override val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            try {
                getHomeList.execute().collect {
                    _uiState.value = HomeUiState.HasData(it, false)
                }
            } catch (e: DomainException) {
                _uiState.value = HomeUiState.HasError(e.errorReason)
            }
        }
    }

    private fun loadNextPage() {
        viewModelScope.launch {
            try {
                (_uiState.value as? HomeUiState.HasData)?.let {
                    _uiState.value = it.copy(isLoadingNextPage = true)
                }
                getHomeList.fetchNext()
                (_uiState.value as? HomeUiState.HasData)?.let {
                    _uiState.value = it.copy(isLoadingNextPage = false)
                }
            } catch (e: DomainException) {
                (_uiState.value as? HomeUiState.HasData)?.let {
                    _uiState.value = it.copy(isLoadingNextPage = false)
                }
                // do nothing
            }
        }
    }

    override fun process(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.BottomOfListReached ->
                loadNextPage()
            is HomeUiEvent.PokemonPressed ->
                navigate(HomeNavigationRequest.OpenPokemon(destinations, event.pokemonId))
        }
    }
}
