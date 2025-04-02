package dev.kevalkanpariya.pawgallery.presentation.generate_dogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.Resource
import dev.kevalkanpariya.pawgallery.domain.repository.DogRepository
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogState
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogUiEvent
import dev.kevalkanpariya.pawgallery.utils.toUserMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DogViewModel(
    private val repository: DogRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DogState())
    val state: StateFlow<DogState> = _state.asStateFlow()

    init {
        loadRecentDogs()
    }

    fun onEvent(event: DogUiEvent) {
        when (event) {
            DogUiEvent.GenerateDogClicked -> generateDog()
            DogUiEvent.ClearDogsClicked -> clearDogs()
        }
    }

    private fun generateDog() {
        viewModelScope.launch {
            repository.getRandomDog().collect { resource ->
                _state.update { currentState ->
                    when (resource) {
                        is Resource.Loading -> currentState.copy(
                            isLoading = true,
                            errorMessage = null
                        )
                        is Resource.Success -> currentState.copy(
                            isLoading = false,
                            currentDog = resource.data,
                            errorMessage = null
                        )
                        is Resource.Error -> currentState.copy(
                            isLoading = false,
                            errorMessage = resource.error.toUserMessage()
                        )
                    }
                }
            }
        }
    }

    private fun loadRecentDogs() {
        viewModelScope.launch {
            repository.getRecentDogs()
                .catch { e -> _state.update { it.copy(errorMessage = e.localizedMessage) } }
                .collect { dogs -> _state.update { it.copy(recentDogs = dogs) }
            }
        }
    }

    private fun clearDogs() {
        viewModelScope.launch {
            repository.clearDogs()
            _state.update { it.copy(recentDogs = emptyList()) }
        }
    }
}