package au.sgallitz.pokedex.details.presentation.details.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.theme.Spacing
import au.sgallitz.pokedex.theme.ThemedPreview

class DetailsLoadingView {
    companion object {
        @Composable
        fun Render(paddingValues: PaddingValues) {
            Box(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .padding(all = Spacing.xLarge),
                contentAlignment = Alignment.TopCenter
            ) {
                CircularProgressIndicator()
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewRender() = ThemedPreview {
        Render(PaddingValues(0.dp))
    }
}
