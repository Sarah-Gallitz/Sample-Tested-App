package au.sgallitz.pokedex.details.presentation.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import au.sgallitz.pokedex.details.presentation.details.view.DetailsErrorView
import au.sgallitz.pokedex.details.presentation.details.view.DetailsLoadingView
import au.sgallitz.pokedex.mvi.MviScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope

class DetailsScreen : MviScreen<DetailsUiState, DetailsUiEvent, DetailsNavigationRequest>() {
    companion object {
        fun destination(pokemonId: Int): String {
            return "details/$pokemonId"
        }
    }

    override val route = "details/{pokemonId}"
    override val routeArguments = listOf(
        navArgument("pokemonId") { type = NavType.IntType }
    )

    @Composable
    override fun getViewModel(scope: Scope): DetailsViewModel {
        return koinViewModel(scope = scope)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(uiState: DetailsUiState, emit: (DetailsUiEvent) -> Unit) {
        BackHandler(enabled = true, onBack = { emit(DetailsUiEvent.BackPressed) })
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "") }) }
        ) { paddingValues ->
            when (uiState) {
                is DetailsUiState.Loading -> DetailsLoadingView.Render(paddingValues)
                is DetailsUiState.HasData -> Box(Modifier.padding(paddingValues)) {
                    Text(uiState.data.pokemonId.toString() + " " + uiState.data.name)
                }
                is DetailsUiState.HasError -> DetailsErrorView.Render(
                    errorReason = uiState.errorReason,
                    paddingValues = paddingValues
                )
            }
        }
    }
}
