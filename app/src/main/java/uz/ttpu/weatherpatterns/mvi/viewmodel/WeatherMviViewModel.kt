package uz.ttpu.weatherpatterns.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.ttpu.weatherpatterns.data.repository.WeatherRepository
import uz.ttpu.weatherpatterns.mvi.model.WeatherIntent
import uz.ttpu.weatherpatterns.mvi.model.WeatherState

class WeatherMviViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow(WeatherState())
    val state: StateFlow<WeatherState> = _state.asStateFlow()

    fun onIntent(intent: WeatherIntent) {
        when (intent) {
            is WeatherIntent.SelectCity -> load(intent.city)
            WeatherIntent.DismissError -> {
                // TODO 3: a new state without the error
                _state.value = _state.value.copy(error = null)
            }
        }
    }

    private fun load(city: String) {
        // TODO 1: publish state with isLoading = true
        _state.value = _state.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            repository.getWeather(city)
                .onSuccess { weather ->
                    // TODO 2: publish state with weather and isLoading = false
                    _state.value = _state.value.copy(isLoading = false, weather = weather)
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(isLoading = false, error = error.message ?: "Unknown error")
                }
        }
    }
}