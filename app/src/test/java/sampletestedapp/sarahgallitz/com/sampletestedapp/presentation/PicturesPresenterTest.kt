package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.net.Uri
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import sampletestedapp.sarahgallitz.com.sampletestedapp.argThatIs
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.PictureRepository

@RunWith(RobolectricTestRunner::class)
class PicturesPresenterTest {
    lateinit var presenter: PicturesPresenter

    lateinit var view: PicturesView
    lateinit var repository: PictureRepository

    @Before
    fun setup() {
        view = mock()
        repository = mock()

        presenter = PicturesPresenter(view, repository, { it() })
    }

    @Test
    fun `on start loads pictures and sets initial sort mode`() {
        presenter.start()

        verify(view).showSortMode("New")
        verify(repository).getPictures(eq("New"), any(), any())
    }

    @Test
    fun `on setSortMode loads pictures and updates sort mode`() {
        presenter.setSortMode("Test")

        verify(view).showSortMode("Test")
        verify(repository).getPictures(eq("Test"), any(), any())
    }

    @Test
    fun `on loadPictures handles success`() {
        // Given
        val mockResponse = listOf(
                Uri.parse("http://test.test/test1"),
                Uri.parse("http://test.test/test2"),
                Uri.parse("http://test.test/test3"),
                Uri.parse("http://test.test/test4")
        )

        whenever(repository.getPictures(any(), any(), any()))
                .thenAnswer {
                    val successCallback = it.argThatIs<(List<Uri>) -> Unit>()
                    successCallback.invoke(mockResponse)
                }

        // When
        presenter.loadPictures()

        // Then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showPictures(eq(mockResponse))
    }


    @Test
    fun `on loadPictures handles error`() {
        // Given
        whenever(repository.getPictures(any(), any(), any()))
                .thenAnswer {
                    val errorCallback = it.argThatIs<() -> Unit>()
                    errorCallback.invoke()
                }

        // When
        presenter.loadPictures()

        // Then
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showError()
    }
}
