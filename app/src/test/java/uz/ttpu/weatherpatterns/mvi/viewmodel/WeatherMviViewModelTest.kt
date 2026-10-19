package uz.ttpu.weatherpatterns.mvi.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import uz.ttpu.weatherpatterns.data.api.FakeWeatherApi
import uz.ttpu.weatherpatterns.data.repository.WeatherRepository
import uz.ttpu.weatherpatterns.mvi.model.WeatherIntent

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherMviViewModelTest {

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun newViewModel() = WeatherMviViewModel(
        WeatherRepository(FakeWeatherApi(latencyMs = 0))
    )

    @Test
    fun select_city_fetches_weather_and_updates_state() {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Tashkent"))

        val currentState = viewModel.state.value
        assertEquals("Tashkent", currentState.weather?.city)
        assertEquals(false, currentState.isLoading)
        assertEquals(null, currentState.error)
    }

    @Test
    fun dismiss_error_clears_error_state() {
        val viewModel = newViewModel()
        viewModel.onIntent(WeatherIntent.SelectCity("Atlantis")) // causes error

        viewModel.onIntent(WeatherIntent.DismissError)

        val currentState = viewModel.state.value
        assertEquals(null, currentState.error)
    }
}