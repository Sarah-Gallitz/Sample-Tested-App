package au.sgallitz.pokedex.mvi

import androidx.compose.runtime.Composable
import org.koin.core.scope.Scope

interface MviComponent<stateType : UiState, eventType : UiEvent, navigation : NavigationRequest> {
    @Composable
    fun getViewModel(scope: Scope): MviViewModel<stateType, eventType, navigation>

    @Composable
    fun Render(uiState: stateType, emit: (eventType) -> Unit)
}
