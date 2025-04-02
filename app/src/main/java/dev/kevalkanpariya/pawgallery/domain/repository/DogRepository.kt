package dev.kevalkanpariya.pawgallery.domain.repository

import dev.kevalkanpariya.pawgallery.domain.models.Dog
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.DataError
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.Resource
import kotlinx.coroutines.flow.Flow

interface DogRepository {

    suspend fun getRandomDog(): Flow<Resource<Dog, DataError>>

    suspend fun getRecentDogs(): Flow<List<Dog>>

    suspend fun clearDogs()
}