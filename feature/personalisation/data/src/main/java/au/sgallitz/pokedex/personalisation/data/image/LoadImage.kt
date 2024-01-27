package au.sgallitz.pokedex.personalisation.data.image

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.net.URL

suspend fun Context.loadImage(url: URL?): ImageBitmap? {
    url ?: return null

    val loader = Coil.imageLoader(this)
    val request = ImageRequest.Builder(this)
        .data(url.toString())
        .allowHardware(false)
        .build()

    val result = (loader.execute(request) as SuccessResult).drawable
    return (result as? BitmapDrawable)?.bitmap?.asImageBitmap()
}
