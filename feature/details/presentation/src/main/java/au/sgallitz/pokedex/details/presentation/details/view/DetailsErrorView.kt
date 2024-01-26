package au.sgallitz.pokedex.details.presentation.details.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.sgallitz.pokedex.core.domain.ErrorReason
import au.sgallitz.pokedex.details.presentation.R
import au.sgallitz.pokedex.theme.Spacing
import au.sgallitz.pokedex.theme.ThemedPreview

class DetailsErrorView {
    companion object {
        @Composable
        fun Render(
            errorReason: ErrorReason,
            paddingValues: PaddingValues
        ) {
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(Spacing.xLarge)
            ) {
                val resId = when (errorReason) {
                    is ErrorReason.GeneralError -> R.string.general_error
                    is ErrorReason.NoNetwork -> R.string.no_network
                }
                Text(
                    stringResource(resId),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Preview("General Error")
    @Composable
    private fun PreviewRenderGeneralError() = ThemedPreview {
        Render(ErrorReason.GeneralError(), PaddingValues(0.dp))
    }

    @Preview("No Network Error")
    @Composable
    private fun PreviewRenderNoNetwork() = ThemedPreview {
        Render(ErrorReason.NoNetwork, PaddingValues(0.dp))
    }
}
