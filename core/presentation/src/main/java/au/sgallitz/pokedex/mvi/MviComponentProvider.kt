package au.sgallitz.pokedex.mvi

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import org.koin.core.scope.Scope

class MviComponentProvider(
    private val navController: NavController,
    private val scope: Scope
) {
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun <stateType : UiState, eventType : UiEvent, navigation : NavigationRequest> ShowComponent(
        component: MviComponent<stateType, eventType, navigation>
    ) {
        val viewModel = component.getViewModel(scope = scope)

        LaunchedEffect(viewModel, navController) {
            viewModel.navigationRequests.collect {
                it.navigate(navController)
            }
        }

        val uiState = viewModel.uiState.collectAsState()

        component.Render(uiState = uiState.value, emit = { viewModel.process(it) })
    }
}
