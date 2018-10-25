package sampletestedapp.sarahgallitz.com.sampletestedapp.data

import android.net.Uri
import android.util.Log
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiClient
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiResponse
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.PictureRepository

class RedditPictureRepository(
        private val pictureApiClient: RedditApiClient
) : PictureRepository {
    private val tag = PictureRepository::class.java.simpleName

    override fun getPictures(
            sortBy: String,
            onSuccess: (List<Uri>) -> Unit,
            onError: () -> Unit
    ) {
        pictureApiClient.getPictures(
                sortBy = parseSortMode(sortBy) ?: RedditSortMode.new,
                onSuccess = { apiResponse ->
                    val pictures = mapApiResponseToUriList(apiResponse)
                    onSuccess(pictures)
                },
                onError = { exception ->
                    Log.d(tag, "Error calling api", exception)
                    onError()
                })
    }

    private fun parseSortMode(sortBy: String): RedditSortMode? {
        return try {
            RedditSortMode.valueOf(sortBy.toLowerCase())
        } catch (e: Throwable) {
            Log.e(tag, "Unable to parse sort mode", e)
            null
        }
    }

    private fun mapApiResponseToUriList(apiResponse: RedditApiResponse): List<Uri> {
        return apiResponse.data.children.mapNotNull { it.data.url }
    }
}



