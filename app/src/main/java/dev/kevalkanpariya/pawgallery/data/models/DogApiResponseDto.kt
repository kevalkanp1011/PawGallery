package dev.kevalkanpariya.pawgallery.data.models

import dev.kevalkanpariya.pawgallery.domain.models.Dog
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogApiResponseDto(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
) {
    fun toDomainModel() = Dog(
        imageUrl = this.message,
        timestamp = System.currentTimeMillis()
    )
}