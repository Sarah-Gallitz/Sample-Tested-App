package au.sgallitz.pokedex.mvi

import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

abstract class MviScreen<stateType : UiState, eventType : UiEvent, navigation : NavigationRequest> :
    MviComponent<stateType, eventType, navigation>, KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    abstract val route: String
}
