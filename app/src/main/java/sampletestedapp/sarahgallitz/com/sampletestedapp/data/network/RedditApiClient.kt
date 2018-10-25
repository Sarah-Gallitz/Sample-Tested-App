package sampletestedapp.sarahgallitz.com.sampletestedapp.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.RedditSortMode
import java.io.IOException

class RedditApiClient(private val okHttpClient: OkHttpClient, private val baseUrl: () -> String) {
    fun getPictures(
            sortBy: RedditSortMode,
            onSuccess: (RedditApiResponse) -> Unit,
            onError: (error: Throwable?) -> Unit
    ) {

        val request = Request
            .Builder()
            .url("${baseUrl()}$sortBy.json")
            .build()

        okHttpClient.newCall(request).enqueue(
            object : Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    try {
                        onSuccess(parseResponse(response?.body()?.string()))
                    } catch (e: Throwable) {
                        onError(e)
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    onError(e)
                }
            }
        )
    }

    private fun parseResponse(json: String?): RedditApiResponse {
        val jsonConverter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(UriAdapter())
            .build()

        return jsonConverter.adapter<RedditApiResponse>(
            RedditApiResponse::class.java)
            .fromJson(json ?: "null")
            ?: throw NullPointerException("Unable to map API response")
    }
}

