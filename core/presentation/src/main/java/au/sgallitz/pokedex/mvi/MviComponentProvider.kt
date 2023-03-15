package au.sgallitz.pokedex.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.koin.core.scope.Scope

class MviComponentProvider(
    private val navController: NavController,
    private val scope: Scope
) {
    @Composable
    fun <stateType : UiState, eventType : UiEvent, navigation : NavigationRequest> ShowComponent(
        component: MviComponent<stateType, eventType, navigation>
    ) {
        val viewModel = component.getViewModel(scope = scope)
        val uiEventStream = Channel<eventType>(capacity = Channel.UNLIMITED)

        LaunchedEffect(viewModel) {
            uiEventStream.consumeEach {
                viewModel.process(it)
            }
        }

        LaunchedEffect(navController, viewModel) {
            viewModel.navigationRequests.collect {
                it.navigate(navController)
            }
        }

        val uiState = viewModel.uiState.collectAsState()

        component.Render(uiState = uiState.value, emit = { uiEventStream.trySend(it) })
    }
}
