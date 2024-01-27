package au.sgallitz.pokedex.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.times
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import java.net.URL

@Composable
fun ScalableRemoteImage(
    url: URL,
    scale: ScaleFactor,
    modifier: Modifier = Modifier,
    contentDescription: String?
) {
    val imageSize = remember { mutableStateOf(Size(0f, 0f)) }
    AsyncImage(
        model = url.getImageRequest(),
        transform = { state ->
            if (state is AsyncImagePainter.State.Success) {
                imageSize.value = state.painter.intrinsicSize
            }
            state
        },
        modifier = modifier.then(
            if (imageSize.value.width != 0f) {
                val scaledSize = imageSize.value.times(scale)
                Modifier.size(DpSize(scaledSize.width.dp, scaledSize.height.dp))
            } else {
                Modifier
            }
        ),
        contentDescription = contentDescription,
        contentScale = if (imageSize.value.width == 0f) {
            ContentScale.None
        } else {
            ContentScale.Fit
        }
    )
}
