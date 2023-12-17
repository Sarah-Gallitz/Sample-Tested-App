package au.sgallitz.pokedex.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.net.URL

@Composable
fun RemoteImage(
    url: URL,
    modifier: Modifier = Modifier,
    contentDescription: String?
) {
    if (url.toString().endsWith(".svg", ignoreCase = true)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url.toString())
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            modifier = modifier,
            contentDescription = contentDescription
        )
    } else {
        AsyncImage(
            model = url.toString(),
            modifier = modifier,
            contentDescription = contentDescription
        )
    }
}
