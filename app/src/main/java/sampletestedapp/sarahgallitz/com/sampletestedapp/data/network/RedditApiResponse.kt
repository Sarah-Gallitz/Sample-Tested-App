package sampletestedapp.sarahgallitz.com.sampletestedapp.data.network

import android.net.Uri

data class RedditApiResponse(
        val data: RedditData
) {
    data class RedditData(
            val children: List<RedditPost> = emptyList()
    )

    data class RedditPost(
            val data: RedditPostData
    )

    data class RedditPostData(
            val title: String,
            val url: Uri?
    )
}