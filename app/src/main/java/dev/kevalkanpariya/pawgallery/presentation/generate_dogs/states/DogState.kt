package dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states

import dev.kevalkanpariya.pawgallery.domain.models.Dog

data class DogState(
    val isLoading: Boolean = false,
    val currentDog: Dog? = null,
    val errorMessage: String? = null,
    val recentDogs: List<Dog> = emptyList()
)

sealed class DogUiEvent {
    object GenerateDogClicked : DogUiEvent()
    object ClearDogsClicked : DogUiEvent()
}