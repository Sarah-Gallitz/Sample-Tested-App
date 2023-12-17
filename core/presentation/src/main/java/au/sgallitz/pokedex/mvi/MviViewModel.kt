package au.sgallitz.pokedex.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class MviViewModel<stateType : UiState, eventType : UiEvent, navType : NavigationRequest> : ViewModel() {
    abstract val uiState: StateFlow<stateType>
    abstract val navigationRequests: Flow<navType>

    abstract fun process(event: eventType)
}
