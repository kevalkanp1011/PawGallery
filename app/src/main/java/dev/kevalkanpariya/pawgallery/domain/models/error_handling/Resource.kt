package dev.kevalkanpariya.pawgallery.domain.models.error_handling

typealias RootError = Error

sealed interface Resource<out D, out E: RootError> {
    object Loading : Resource<Nothing, Nothing>
    data class Success<out D, out E: RootError>(val data: D): Resource<D, E>
    data class Error<out D, out E: RootError>(val error: E): Resource<D, E>
}