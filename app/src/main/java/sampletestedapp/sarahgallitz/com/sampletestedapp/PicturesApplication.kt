package sampletestedapp.sarahgallitz.com.sampletestedapp

import android.app.Application
import okhttp3.OkHttpClient
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.PicassoImageService
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.RedditPictureRepository
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiClient
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.ImageService
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.PictureRepository

class PicturesApplication : Application() {
    var pictureUrl = "https://www.reddit.com/r/succulents/"

    val okHttpClient = OkHttpClient()

    val picturesRepository: PictureRepository by lazy {
        // Url here is dynamic to allow UI tests to inject it
        RedditPictureRepository(RedditApiClient(okHttpClient, { pictureUrl }))
    }

    var imageService: ImageService = PicassoImageService()
}