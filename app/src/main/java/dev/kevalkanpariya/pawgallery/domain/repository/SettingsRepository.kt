package dev.kevalkanpariya.pawgallery.domain.repository

import dev.kevalkanpariya.pawgallery.domain.models.UserSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val settings: Flow<UserSettings>
    suspend fun updateCacheSize(size: Int)
    suspend fun updateImageQuality(quality: String)
    suspend fun updateAutoRefresh(enabled: Boolean)
}