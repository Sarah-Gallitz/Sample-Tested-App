package au.sgallitz.pokedex.mvi

import androidx.navigation.NamedNavArgument
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

abstract class MviScreen<state : UiState, event : UiEvent, nav : NavigationRequest> :
    MviComponent<state, event, nav>, KoinScopeComponent {

    override val scope: Scope by lazy { createScope(this) }

    abstract val route: String
    open val routeArguments: List<NamedNavArgument> = emptyList()
}
