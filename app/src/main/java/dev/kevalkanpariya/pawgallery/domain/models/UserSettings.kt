package dev.kevalkanpariya.pawgallery.domain.models

data class UserSettings(
    val cacheSize: Int = 20,         // Max dogs to store
    val imageQuality: String = "hd", // "sd" or "hd"
    val autoRefresh: Boolean = false
)