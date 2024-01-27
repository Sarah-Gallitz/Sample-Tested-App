package au.sgallitz.pokedex.details.presentation.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import au.sgallitz.pokedex.details.presentation.details.view.DetailsErrorView
import au.sgallitz.pokedex.details.presentation.details.view.DetailsLoadingView
import au.sgallitz.pokedex.details.presentation.details.view.DetailsView
import au.sgallitz.pokedex.extensions.shadowOnScroll
import au.sgallitz.pokedex.mvi.MviScreen
import au.sgallitz.pokedex.theme.Styles.ContentColoredTheme
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
        ContentColoredTheme((uiState as? DetailsUiState.HasData)?.data?.themeImage) {
            BackHandler(enabled = true, onBack = { emit(DetailsUiEvent.BackPressed) })
            val scrollState = rememberScrollState()
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "") },
                        modifier = Modifier.shadowOnScroll(scrollState),
                        navigationIcon = {
                            IconButton(onClick = { emit(DetailsUiEvent.BackPressed) }) {
                                Icon(
                                    Icons.Rounded.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->
                when (uiState) {
                    is DetailsUiState.Loading -> DetailsLoadingView.Render(paddingValues)
                    is DetailsUiState.HasData -> DetailsView.Render(
                        details = uiState.data,
                        scrollState = scrollState,
                        paddingValues = paddingValues
                    )

                    is DetailsUiState.HasError -> DetailsErrorView.Render(
                        errorReason = uiState.errorReason,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}
