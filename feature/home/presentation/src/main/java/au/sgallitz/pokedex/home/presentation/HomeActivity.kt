package au.sgallitz.pokedex.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import au.sgallitz.pokedex.BaseActivity
import au.sgallitz.pokedex.theme.Spacing

class HomeActivity : BaseActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Render() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.home_title)) }
                )
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = Spacing.Medium)
            ) {
                Text("This will be the homepage")
            }
        }
    }
}
