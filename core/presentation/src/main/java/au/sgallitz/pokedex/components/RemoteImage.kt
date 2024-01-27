package au.sgallitz.pokedex.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import java.net.URL

@Composable
fun RemoteImage(
    url: URL,
    modifier: Modifier = Modifier,
    contentDescription: String?
) {
    AsyncImage(
        model = url.getImageRequest(),
        modifier = modifier,
        contentDescription = contentDescription
    )
}
