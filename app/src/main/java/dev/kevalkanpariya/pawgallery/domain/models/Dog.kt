package dev.kevalkanpariya.pawgallery.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val imageUrl: String,
    val timestamp: Long
)