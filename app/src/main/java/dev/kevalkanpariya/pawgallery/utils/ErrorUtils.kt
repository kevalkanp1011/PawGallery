package dev.kevalkanpariya.pawgallery.utils

import dev.kevalkanpariya.pawgallery.domain.models.error_handling.DataError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode
import java.io.IOException

fun Throwable.toDataError(): DataError = when (this) {
    is ClientRequestException -> when (response.status) {
        HttpStatusCode.RequestTimeout -> DataError.Network.REQUEST_TIMEOUT
        HttpStatusCode.TooManyRequests -> DataError.Network.TOO_MANY_REQUESTS
        HttpStatusCode.PayloadTooLarge -> DataError.Network.PAYLOAD_TOO_LARGE
        else -> DataError.Network.UNKNOWN
    }
    is ServerResponseException -> DataError.Network.SERVER_ERROR
    is IOException -> DataError.Network.NO_INTERNET
    else -> DataError.Network.UNKNOWN
}

fun DataError.toUserMessage(): String {
    return when (this) {
        DataError.Network.NO_INTERNET -> "No internet connection."
        DataError.Network.REQUEST_TIMEOUT -> "Request timed out."
        DataError.Network.SERVER_ERROR -> "Server error occurred."
        DataError.Network.SERIALIZATION -> "Failed to process data."
        else -> "Unknown error occurred."
    }
}