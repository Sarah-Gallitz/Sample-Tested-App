package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.lifecycle.viewModelScope
import au.sgallitz.pokedex.home.domain.usecase.GetHomeList
import au.sgallitz.pokedex.mvi.MviViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeList: GetHomeList
) : MviViewModel<HomeUiState, HomeUiEvent, HomeNavigationRequest>() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    override val uiState: StateFlow<HomeUiState> = _uiState

    private val _navigationRequests = Channel<HomeNavigationRequest>(capacity = Channel.UNLIMITED)
    override val navigationRequests: Flow<HomeNavigationRequest> by lazy {
        _navigationRequests.receiveAsFlow()
    }

    init {
        viewModelScope.launch {
            try {
                getHomeList.execute().collect {
                    _uiState.value = HomeUiState.HasData(it)
                }
            } catch (e: Throwable) {
                _uiState.value = HomeUiState.Loading
            }
        }
    }

    override fun process(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.BackPressed ->
                _navigationRequests.trySend(HomeNavigationRequest.CloseHome)
        }
    }
}
