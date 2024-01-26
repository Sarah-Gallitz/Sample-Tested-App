package au.sgallitz.pokedex.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class MviViewModel<stateType : UiState, eventType : UiEvent, navType : NavigationRequest> : ViewModel() {
    abstract val uiState: StateFlow<stateType>

    abstract fun process(event: eventType)

    private val _navigationRequests = Channel<navType>(capacity = Channel.UNLIMITED)
    val navigationRequests: Flow<navType> by lazy { _navigationRequests.receiveAsFlow() }
    fun navigate(request: navType) {
        _navigationRequests.trySend(request)
    }
}
