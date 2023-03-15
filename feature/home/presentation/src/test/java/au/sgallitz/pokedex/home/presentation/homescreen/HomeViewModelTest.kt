package au.sgallitz.pokedex.home.presentation.homescreen

import au.sgallitz.pokedex.home.domain.model.PokemonHomeItem
import au.sgallitz.pokedex.home.domain.usecase.GetHomeList
import au.sgallitz.pokedex.testsetup.shared.UnitTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.URL
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : UnitTest() {
    private lateinit var subjectUnderTest: HomeViewModel
    private fun create(): HomeViewModel {
        return HomeViewModel(getHomeList)
    }

    private val getHomeList = mockk<GetHomeList>()

    @Test
    fun `given home view model, when success, then the ui shows the data`() = runTest {
        val mockData = listOf(
            PokemonHomeItem(123, "test123", URL("https://test.test"), URL("https://test.test")),
            PokemonHomeItem(234, "test234", URL("https://test.test"), URL("https://test.test"))
        )

        coEvery { getHomeList.execute() } returns flowOf(mockData)

        subjectUnderTest = create()

        assertEquals(
            expected = HomeUiState.HasData(mockData),
            actual = subjectUnderTest.uiState.value
        )
    }

    @Test
    fun `given home view model, when error, then the ui shows the error`() = runTest {
        coEvery { getHomeList.execute() } throws RuntimeException("Unable to reach the API")

        subjectUnderTest = create()

        assertEquals(expected = HomeUiState.HasError, actual = subjectUnderTest.uiState.value)
    }

    @Test
    fun `given home view model, when back pressed, then the screen is closed`() = runTest {
        subjectUnderTest = create()

        subjectUnderTest.process(HomeUiEvent.BackPressed)

        assertEquals(expected = HomeNavigationRequest.CloseHome, actual = subjectUnderTest.navigationRequests.first())
    }
}
