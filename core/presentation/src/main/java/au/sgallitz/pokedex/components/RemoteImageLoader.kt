package au.sgallitz.pokedex.components

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.ImageRequest
import java.net.URL

fun createRemoteImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.2)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .components {
            add(SvgDecoder.Factory())
        }
        .build()
}

@Composable
fun URL.getImageRequest(): ImageRequest {
    val url = this.toString()
    val builder = ImageRequest.Builder(LocalContext.current).data(url)

    if (url.endsWith(".svg", ignoreCase = true)) {
        builder.decoderFactory(SvgDecoder.Factory())
    } else if (url.endsWith(".gif", ignoreCase = true)) {
        builder.decoderFactory(
            if (Build.VERSION.SDK_INT >= 28) {
                ImageDecoderDecoder.Factory()
            } else {
                GifDecoder.Factory()
            }
        )
    }

    return builder.build()
}
