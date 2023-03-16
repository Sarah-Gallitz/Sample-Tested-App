package au.sgallitz.pokedex.testsetup.shared

import org.junit.Rule

abstract class UnitTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
}
