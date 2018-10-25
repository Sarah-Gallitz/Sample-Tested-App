package sampletestedapp.sarahgallitz.com.sampletestedapp.data.network

import android.net.Uri
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.RedditSortMode

@RunWith(RobolectricTestRunner::class)
class RedditApiClientTest {
    private lateinit var apiClient: RedditApiClient

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        mockWebServer.start()
        val baseUrl = mockWebServer.url("/")

        apiClient = RedditApiClient(OkHttpClient(), baseUrl.toString())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `on getPictures parses api response`() {
        // Given
        var successData: RedditApiResponse? = null
        var error: Throwable? = null

        mockWebServer.enqueue(MockResponse().setBody(sampleRedditJson))

        // When
        apiClient.getPictures(RedditSortMode.new, { successData = it }, { error = it })
        Thread.sleep(1000) // Wait for call to return

        // Then
        assertEquals(sampleParsedResponse, successData)
        assertNull(error)
    }

    @Test
    fun `on getPictures catches 500 response`() {
        // Given
        var successData: RedditApiResponse? = null
        var error: Throwable? = null

        mockWebServer.enqueue(MockResponse().setResponseCode(500))

        // When
        apiClient.getPictures(RedditSortMode.new, { successData = it }, { error = it })
        Thread.sleep(1000) // Wait for call to return

        // Then
        assertNotNull(error)
        assertNull(successData)
    }

    @Test
    fun `on getPictures catches bad json response`() {
        // Given
        var successData: RedditApiResponse? = null
        var error: Throwable? = null

        mockWebServer.enqueue(MockResponse().setBody("""{ "data": {}"""))

        // When
        apiClient.getPictures(RedditSortMode.new, { successData = it }, { error = it })
        Thread.sleep(1000) // Wait for call to return

        // Then
        assertNotNull(error)
        assertNull(successData)
    }

    val sampleParsedResponse = RedditApiResponse(
            data = RedditApiResponse.RedditData(listOf(
                    RedditApiResponse.RedditPost(RedditApiResponse.RedditPostData(
                            title = "Test Title 1",
                            url = Uri.parse("https://test.test/imageUrl1.jpg")
                    )),
                    RedditApiResponse.RedditPost(RedditApiResponse.RedditPostData(
                            title = "Test Title 2",
                            url = Uri.parse("https://test.test/imageUrl2.jpg")
                    )),
                    RedditApiResponse.RedditPost(RedditApiResponse.RedditPostData(
                            title = "Test Title 3",
                            url = Uri.parse("https://test.test/imageUrl3.jpg")
                    ))
            ))
    )

    val sampleRedditJson = """
        {
            "kind": "Listing",
            "data": {
                "children": [{
                        "data": {
                            "title": "Test Title 1",
                            "thumbnail": "https://test.test/thumbnail1.jpg",
                            "url": "https://test.test/imageUrl1.jpg"
                        }
                    },
                    {
                        "data": {
                            "title": "Test Title 2",
                            "thumbnail": "https://test.test/thumbnail2.jpg",
                            "url": "https://test.test/imageUrl2.jpg"
                        }
                    },
                    {
                        "data": {
                            "title": "Test Title 3",
                            "thumbnail": "https://test.test/thumbnail3.jpg",
                            "url": "https://test.test/imageUrl3.jpg"
                        }
                    }
                ]
            }
        }
    """.trimIndent()
}
