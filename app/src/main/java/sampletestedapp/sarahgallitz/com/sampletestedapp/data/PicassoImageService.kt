package sampletestedapp.sarahgallitz.com.sampletestedapp.data

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.ImageService

class PicassoImageService : ImageService {
    override fun loadImageToFit(image: Uri, view: ImageView, placeholder: Int, onError: () -> Unit) {
        val callback = object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                onError.invoke()
            }
        }

        Picasso.get()
                .load(image)
                .placeholder(placeholder)
                .fit()
                .centerCrop()
                .into(view, callback)
    }
}