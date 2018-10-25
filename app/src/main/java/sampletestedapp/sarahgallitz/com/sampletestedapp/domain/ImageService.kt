package sampletestedapp.sarahgallitz.com.sampletestedapp.domain

import android.net.Uri
import android.support.annotation.DrawableRes
import android.widget.ImageView

interface ImageService {
    fun loadImageToFit(image: Uri, view: ImageView, @DrawableRes placeholder: Int, onError: () -> Unit)
}