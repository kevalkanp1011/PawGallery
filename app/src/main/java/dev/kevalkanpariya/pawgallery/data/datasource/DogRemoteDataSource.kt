package dev.kevalkanpariya.pawgallery.data.datasource

import dev.kevalkanpariya.pawgallery.data.models.DogApiResponseDto
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.DataError
import dev.kevalkanpariya.pawgallery.domain.models.error_handling.Resource
import dev.kevalkanpariya.pawgallery.utils.toDataError
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import java.io.IOException

interface DogRemoteDataSource {
    suspend fun getRandomDog(): Resource<DogApiResponseDto, DataError>
}

class DogRemoteDataSourceImpl(private val client: HttpClient) : DogRemoteDataSource {
    override suspend fun getRandomDog(): Resource<DogApiResponseDto, DataError> {
        return try {
            val response = client.get("https://dog.ceo/api/breeds/image/random")
            Resource.Success(response.body<DogApiResponseDto>())
        } catch (e: Exception) {
            Resource.Error(e.toDataError())
        }
    }



}