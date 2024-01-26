package au.sgallitz.pokedex.details.presentation.details.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.details.domain.model.PokemonDetails
import au.sgallitz.pokedex.extensions.isAtBottom
import au.sgallitz.pokedex.extensions.totalItems
import au.sgallitz.pokedex.theme.Spacing
import au.sgallitz.pokedex.theme.ThemedPreview

class DetailsView {
    companion object {
        @Composable
        fun Render(
            details: PokemonDetails,
            paddingValues: PaddingValues
        ) {
            val gridState = rememberLazyGridState()
            val isAtBottom = gridState.isAtBottom()
            val totalItems = gridState.totalItems()

            LaunchedEffect(isAtBottom, totalItems) {
                if (isAtBottom) {
                    // onBottomOfListReached()
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 144.dp),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
                contentPadding = PaddingValues(
                    top = Spacing.Medium + paddingValues.calculateTopPadding(),
                    bottom = Spacing.Medium + paddingValues.calculateBottomPadding(),
                    start = Spacing.Medium + paddingValues.calculateStartPadding(
                        LocalLayoutDirection.current
                    ),
                    end = Spacing.Medium + paddingValues.calculateEndPadding(
                        LocalLayoutDirection.current
                    )
                ),
                state = gridState
            ) {
//                items(homeItems) { item: DetailsItem ->
//                    when (item) {
//                        is PokemonDetailsItem -> PokemonView.Render(item)
//                    }
//                }
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewRender() = ThemedPreview {
//        Render(
//            homeItems = listOf(
//                PokemonDetailsItem(
//                    1,
//                    "bulbasaur",
//                    URL("http://test.test"),
//                    URL("http://test.test")
//                ),
//                PokemonDetailsItem(
//                    1,
//                    "bulbasaur",
//                    URL("http://test.test"),
//                    URL("http://test.test")
//                ),
//                PokemonDetailsItem(
//                    1,
//                    "bulbasaur",
//                    URL("http://test.test"),
//                    URL("http://test.test")
//                ),
//                PokemonDetailsItem(
//                    1,
//                    "bulbasaur",
//                    URL("http://test.test"),
//                    URL("http://test.test")
//                )
//            ),
//            onBottomOfListReached = {},
//            paddingValues = PaddingValues(all = 0.dp)
//        )
    }
}
