package au.sgallitz.pokedex.home.presentation.homescreen

import androidx.activity.compose.BackHandler
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import au.sgallitz.pokedex.home.presentation.R
import au.sgallitz.pokedex.home.presentation.views.HomeListView
import au.sgallitz.pokedex.mvi.MviScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.scope.Scope

class HomeScreen : MviScreen<HomeUiState, HomeUiEvent, HomeNavigationRequest>() {
    override val route = "home"

    @Composable
    override fun getViewModel(scope: Scope): HomeViewModel {
        return koinViewModel(scope = scope)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render(uiState: HomeUiState, emit: (HomeUiEvent) -> Unit) {
        BackHandler(enabled = true, onBack = { emit(HomeUiEvent.BackPressed) })
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.home_title)) }
                )
            }
        ) { paddingValues ->
            when (uiState) {
                is HomeUiState.Loading -> {}
                is HomeUiState.HasData -> HomeListView.Render(
                    homeItems = uiState.data,
                    paddingValues = paddingValues
                )
                is HomeUiState.HasError -> {}
            }
        }
    }
}
