package au.sgallitz.pokedex.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.BaseActivity
import au.sgallitz.pokedex.home.domain.model.HomeItem
import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.home.domain.usecase.GetHomeList
import au.sgallitz.pokedex.theme.Spacing
import org.koin.android.ext.android.inject

class HomeActivity : BaseActivity() {
    private val getHomeList: GetHomeList by inject()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render() {
        val homeItems = getHomeList.execute().collectAsState(initial = emptyList())

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.home_title)) }
                )
            }
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 144.dp),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
                horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
                contentPadding = PaddingValues(
                    top = Spacing.Medium + paddingValues.calculateTopPadding(),
                    bottom = Spacing.Medium + paddingValues.calculateBottomPadding(),
                    start = Spacing.Medium + paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    end = Spacing.Medium + paddingValues.calculateEndPadding(LocalLayoutDirection.current)
                )
            ) {
                items(homeItems.value) { item: HomeItem ->
                    when (item) {
                        is PokemonHomeItem -> PokemonView.RenderPokemonItem(item)
                    }
                }
            }
        }
    }
}
