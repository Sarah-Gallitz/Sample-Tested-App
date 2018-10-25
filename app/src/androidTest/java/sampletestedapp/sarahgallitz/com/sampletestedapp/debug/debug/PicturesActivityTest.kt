package sampletestedapp.sarahgallitz.com.sampletestedapp.debug.debug

import PhoneUtils
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.nhaarman.mockito_kotlin.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sampletestedapp.sarahgallitz.com.sampletestedapp.PicturesApplication
import sampletestedapp.sarahgallitz.com.sampletestedapp.R
import sampletestedapp.sarahgallitz.com.sampletestedapp.debug.debug.utils.RecyclerViewItemCountAssertion.Companion.withItemCount
import sampletestedapp.sarahgallitz.com.sampletestedapp.debug.debug.utils.SystemAnimations
import sampletestedapp.sarahgallitz.com.sampletestedapp.domain.ImageService
import sampletestedapp.sarahgallitz.com.sampletestedapp.presentation.PicturesActivity

@Suppress("UNCHECKED_CAST")
@RunWith(AndroidJUnit4::class)
class PicturesActivityTest {
    @JvmField
    @Rule
    val mActivityRule = object : ActivityTestRule<PicturesActivity>(
            PicturesActivity::class.java,
            true,
            false
    ) {}

    private lateinit var server: MockWebServer
    private lateinit var mockImageService: ImageService
    private lateinit var idlingResource: IdlingResource

    val animations = SystemAnimations()

    @Before
    fun setup() {
        val application = (InstrumentationRegistry.getTargetContext().applicationContext as PicturesApplication)

        server = MockWebServer()
        server.start()

        animations.disableAll()

        idlingResource = OkHttp3IdlingResource.create("OkHttp", application.okHttpClient)
        IdlingRegistry.getInstance().register(idlingResource)

        mockImageService = mock()

        val baseUrl = server.url("/reddit/test")
        application.pictureUrl = baseUrl.toString()

        application.imageService = mockImageService
    }

    @Test
    fun on_launch_shows_loading_screen() {
        // Don't wait for loading to finish this time
        IdlingRegistry.getInstance().unregister(idlingResource)

        mActivityRule.launchActivity(null)

        onView(withId(R.id.loading_view)).check(matches(isDisplayed()))

        PhoneUtils.takeScreenshot(mActivityRule.activity, "Loading-Screen")

        IdlingRegistry.getInstance().register(idlingResource)
    }

    @Test
    fun on_success_shows_succulents() {
        server.enqueue(MockResponse().setBody(mockRedditResponseWith3Images))

        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl1.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_1")) }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl2.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_2")) }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl3.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_3")) }

        mActivityRule.launchActivity(null)

        onView(withId(R.id.images_view)).check(matches(isDisplayed()))
        onView(withId(R.id.gridview)).check(withItemCount(3))

        PhoneUtils.takeScreenshot(mActivityRule.activity, "Succulents-Screen")

        verify(mockImageService, times(3)).loadImageToFit(any(), any(), any(), any())
    }

    @Test
    fun on_change_sort_mode_reloads_succulents() {
        server.enqueue(MockResponse().setBody(mockRedditResponseWith3Images))
        server.enqueue(MockResponse().setBody(mockRedditResponseWith3Images))

        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl1.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_1")) }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl2.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_2")) }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl3.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_3")) }

        mActivityRule.launchActivity(null)

        onView(withId(R.id.images_view)).check(matches(isDisplayed()))

        onView(withId(R.id.sort_mode_button)).perform(click())
        PhoneUtils.takeScreenshot(mActivityRule.activity, "Succulents-Sort-Modes-Screen")

        onView(withText("Top")).perform(click())

        onView(withId(R.id.gridview)).check(withItemCount(3))
        verify(mockImageService, times(6)).loadImageToFit(any(), any(), any(), any())
    }

    @Test
    fun on_image_fetch_error_removes_item_from_view() {
        server.enqueue(MockResponse().setBody(mockRedditResponseWith3Images))

        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl1.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_1")) }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl2.jpg")), any(), any(), any()))
                .thenAnswer {
                    val errorCallback = it.arguments[3] as (() -> Unit)
                    errorCallback.invoke()
                }
        whenever(mockImageService.loadImageToFit(eq(Uri.parse("https://test.test/imageUrl3.jpg")), any(), any(), any()))
                .thenAnswer { (it.arguments[1] as ImageView).setImageBitmap(PhoneUtils.getTestBitmap("test_image_3")) }

        mActivityRule.launchActivity(null)

        onView(withId(R.id.images_view)).check(matches(isDisplayed()))
        onView(withId(R.id.gridview)).check(withItemCount(2))
    }

    @Test
    fun on_error_shows_error_screen_and_retry_works() {
        server.enqueue(MockResponse().setResponseCode(500))

        mActivityRule.launchActivity(null)

        onView(withText(R.string.error_description)).check(matches(isDisplayed()))

        PhoneUtils.takeScreenshot(mActivityRule.activity, "Error-Screen")

        server.enqueue(MockResponse().setBody(mockRedditResponseWith3Images))

        onView(withId(R.id.retry_btn)).perform(click())

        onView(withId(R.id.images_view)).check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        server.shutdown()

        IdlingRegistry.getInstance().unregister(idlingResource)

        animations.enableAll()
    }

    val mockRedditResponseWith3Images = """
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
    """
}