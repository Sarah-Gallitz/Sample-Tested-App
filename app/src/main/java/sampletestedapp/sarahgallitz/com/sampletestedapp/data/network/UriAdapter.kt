package sampletestedapp.sarahgallitz.com.sampletestedapp.data.network

import android.net.Uri
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

class UriAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Uri? {
        return if (reader.peek() == JsonReader.Token.NULL) {
            reader.nextNull()
        } else {
            Uri.parse(reader.nextString())
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Uri?) {
        writer.value(value?.toString())
    }
}