package au.sgallitz.pokedex.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
            LazyColumn(
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = Spacing.Medium),
                verticalArrangement = Arrangement.spacedBy(Spacing.Medium)
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
