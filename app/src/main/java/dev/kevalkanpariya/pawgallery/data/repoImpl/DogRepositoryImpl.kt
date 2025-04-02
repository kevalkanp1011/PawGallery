package dev.kevalkanpariya.pawgallery.data.repoImpl

import dev.kevalkanpariya.pawgallery.data.cache.DogCache
import dev.kevalkanpariya.pawgallery.data.datasource.DogRemoteDataSource
import dev.kevalkanpariya.pawgallery.domain.models.Dog
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.DataError
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.Resource
import dev.kevalkanpariya.pawgallery.domain.repository.DogRepository
import dev.kevalkanpariya.pawgallery.utils.toDataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class DogRepositoryImpl(
    private val remoteDataSource: DogRemoteDataSource,
    private val cache: DogCache
) : DogRepository {

    override suspend fun getRandomDog(): Flow<Resource<Dog, DataError>> = flow {
        emit(Resource.Loading)

        when (val result = remoteDataSource.getRandomDog()) {
            is Resource.Success -> {
                val dog = result.data.toDomainModel()
                cache.addDog(dog)
                emit(Resource.Success(dog))
            }
            is Resource.Error -> {
                val cachedDogs = cache.getDogs().firstOrNull().orEmpty()
                if (cachedDogs.isNotEmpty()) {
                    emit(Resource.Success(cachedDogs.first()))
                } else {
                    emit(Resource.Error(result.error))
                }
            }
            else -> Unit
        }
    }.catch { e ->
        emit(Resource.Error(e.toDataError()))
    }

    override suspend fun getRecentDogs(): Flow<List<Dog>> = cache.getDogs()

    override suspend fun clearDogs() {
        cache.clear()
    }
}

