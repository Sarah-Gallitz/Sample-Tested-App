package au.sgallitz.pokedex.home.presentation.homescreen

import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.mvi.UiState

sealed class HomeUiState : UiState {
    data class HasData(val data: List<HomeItem>, val isLoadingNextPage: Boolean) : HomeUiState()
    object Loading : HomeUiState()

    data class HasError(val errorReason: ErrorReason) : HomeUiState()
}
