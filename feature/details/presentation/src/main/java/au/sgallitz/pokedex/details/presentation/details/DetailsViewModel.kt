package au.sgallitz.pokedex.details.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import au.sgallitz.pokedex.core.domain.DomainException
import au.sgallitz.pokedex.details.domain.usecase.GetPokemonDetails
import au.sgallitz.pokedex.mvi.MviViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getDetails: GetPokemonDetails,
    savedStateHandle: SavedStateHandle
) : MviViewModel<DetailsUiState, DetailsUiEvent, DetailsNavigationRequest>() {
    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    override val uiState: StateFlow<DetailsUiState> = _uiState

    private val pokemonId: Int = checkNotNull(savedStateHandle["pokemonId"])

    init {
        viewModelScope.launch {
            try {
                getDetails.execute(pokemonId).collect {
                    _uiState.value = DetailsUiState.HasData(it)
                }
            } catch (e: DomainException) {
                _uiState.value = DetailsUiState.HasError(e.errorReason)
            }
        }
    }
    override fun process(event: DetailsUiEvent) {
        when (event) {
            is DetailsUiEvent.BackPressed -> navigate(DetailsNavigationRequest.CloseDetails)
            is DetailsUiEvent.BottomOfListReached -> {}
        }
    }
}
