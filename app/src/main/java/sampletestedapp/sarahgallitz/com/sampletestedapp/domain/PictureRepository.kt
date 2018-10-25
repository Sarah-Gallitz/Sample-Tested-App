package sampletestedapp.sarahgallitz.com.sampletestedapp.domain

import android.net.Uri


interface PictureRepository {
    fun getPictures(sortBy: String, onSuccess: (List<Uri>) -> Unit, onError: () -> Unit)
}

