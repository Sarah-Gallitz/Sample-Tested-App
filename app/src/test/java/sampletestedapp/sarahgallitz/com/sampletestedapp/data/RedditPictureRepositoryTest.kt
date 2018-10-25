package sampletestedapp.sarahgallitz.com.sampletestedapp.data

import android.net.Uri
import com.nhaarman.mockito_kotlin.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiClient
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiResponse
import sampletestedapp.sarahgallitz.com.sampletestedapp.data.network.RedditApiResponse.*

@Suppress("UNCHECKED_CAST")
@RunWith(RobolectricTestRunner::class)
class RedditPictureRepositoryTest {
    private lateinit var apiClientMock: RedditApiClient
    private lateinit var repository: RedditPictureRepository

    @Before
    fun setup() {
        apiClientMock = mock()
        repository = RedditPictureRepository(apiClientMock)
    }

    @Test
    fun `on getPictures handles "New" sort mode`() {
        // Given
        val sortMode = "New"

        // When
        repository.getPictures(sortMode, {}, {})

        // Then
        val expectedSortMode = RedditSortMode.new
        verify(apiClientMock).getPictures(eq(expectedSortMode), any(), any())
    }

    @Test
    fun `on getPictures handles "NEW" all caps sort mode`() {
        // Given
        val sortMode = "NEW"

        // When
        repository.getPictures(sortMode, {}, {})

        // Then
        val expectedSortMode = RedditSortMode.new
        verify(apiClientMock).getPictures(eq(expectedSortMode), any(), any())
    }

    @Test
    fun `on getPictures handles "top" sort mode`() {
        // Given
        val sortMode = "top"

        // When
        repository.getPictures(sortMode, {}, {})

        // Then
        val expectedSortMode = RedditSortMode.top
        verify(apiClientMock).getPictures(eq(expectedSortMode), any(), any())
    }

    @Test
    fun `on getPictures handles "Hot" sort mode`() {
        // Given
        val sortMode = "Hot"

        // When
        repository.getPictures(sortMode, {}, {})

        // Then
        val expectedSortMode = RedditSortMode.hot
        verify(apiClientMock).getPictures(eq(expectedSortMode), any(), any())
    }

    @Test
    fun `on getPictures handles unsupported sort mode and defaults to new`() {
        // Given
        val sortMode = "ThisIsNotValid"

        // When
        repository.getPictures(sortMode, {}, {})

        // Then
        val expectedSortMode = RedditSortMode.new
        verify(apiClientMock).getPictures(eq(expectedSortMode), any(), any())
    }

    @Test
    fun `on getPictures calls success callback with full data`() {
        // Given
        whenever(apiClientMock.getPictures(any(), any(), any()))
                .thenAnswer { callOnMock ->
                    val successCallback = callOnMock.arguments[1] as ((RedditApiResponse) -> Unit)
                    successCallback(mockedApiFullResponse)
                }

        var successData: List<Uri>? = null
        var errorCalled = false

        // When
        repository.getPictures("New", { successData = it }, { errorCalled = true })

        // Then
        val expectedData = listOf(
                Uri.parse("http://test.test/image1"),
                Uri.parse("http://test.test/image2"),
                Uri.parse("http://test.test/image3")
        )
        assertEquals(expectedData, successData)
        assertFalse(errorCalled)
    }

    @Test
    fun `on getPictures calls success callback with empty data`() {
        // Given
        whenever(apiClientMock.getPictures(any(), any(), any()))
                .thenAnswer { callOnMock ->
                    val successCallback = callOnMock.arguments[1] as ((RedditApiResponse) -> Unit)
                    successCallback(mockedApiEmptyResponse)
                }

        var successData: List<Uri>? = null
        var errorCalled = false

        // When
        repository.getPictures("New", { successData = it }, { errorCalled = true })

        // Then
        assertEquals(listOf<Uri>(), successData)
        assertFalse(errorCalled)
    }

    @Test
    fun `on getPictures bubbles up api error`() {
        whenever(apiClientMock.getPictures(any(), any(), any()))
                .thenAnswer { callOnMock ->
                    val errorCallback = callOnMock.arguments[2] as ((Throwable?) -> Unit)
                    errorCallback(null)
                }

        var successCalled = false
        var errorCalled = false

        repository.getPictures("New", { successCalled = true }, { errorCalled = true })

        assertTrue(errorCalled)
        assertFalse(successCalled)
    }

    private val mockedApiFullResponse = RedditApiResponse(
            data = RedditData(listOf(
                    RedditPost(RedditPostData(
                            title = "Image 1",
                            url = Uri.parse("http://test.test/image1")
                    )),
                    RedditPost(RedditPostData(
                            title = "Image 2",
                            url = Uri.parse("http://test.test/image2")
                    )),
                    RedditPost(RedditPostData(
                            title = "Image 3",
                            url = Uri.parse("http://test.test/image3")
                    ))
            ))
    )

    private val mockedApiEmptyResponse = RedditApiResponse(
            data = RedditData(listOf())
    )
}



