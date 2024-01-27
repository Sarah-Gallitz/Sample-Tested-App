package au.sgallitz.pokedex.core.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

fun createRetrofitClient(url: String): Retrofit {
    val json = Json { ignoreUnknownKeys = true }
    val contentType = "application/json".toMediaType()

    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
}
