package au.sgallitz.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val lightScheme = lightColorScheme()
    private val darkScheme = darkColorScheme()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme(
                colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme
            ) {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Pokedex") }) }
                ) { defaultPadding ->
                    Column(
                        Modifier
                            .padding(defaultPadding)
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                    ) {
                        Card() {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 24.dp,
                                        horizontal = 16.dp
                                    ),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text("Hello World!", style = MaterialTheme.typography.titleLarge)
                                Text("This is a little demonstration app")
                            }
                        }
                    }
                }
            }
        }
    }
}
