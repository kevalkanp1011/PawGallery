package dev.kevalkanpariya.pawgallery.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import dev.kevalkanpariya.pawgallery.domain.models.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsDataSource {
    val settings: Flow<UserSettings>
    suspend fun updateCacheSize(size: Int)
    suspend fun updateImageQuality(quality: String)
    suspend fun updateAutoRefresh(enabled: Boolean)
}

class SettingsDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : SettingsDataSource {

    override val settings: Flow<UserSettings> = dataStore.data
        .map { prefs ->
            UserSettings(
                cacheSize = prefs[SettingsKeys.CACHE_SIZE] ?: 20,
                imageQuality = prefs[SettingsKeys.IMAGE_QUALITY] ?: "hd",
                autoRefresh = prefs[SettingsKeys.AUTO_REFRESH] ?: false
            )
        }

    override suspend fun updateCacheSize(size: Int) {
        dataStore.edit { prefs ->
            prefs[SettingsKeys.CACHE_SIZE] = size.coerceIn(10, 50)
        }
    }

    override suspend fun updateImageQuality(quality: String) {
        dataStore.edit { prefs ->
            prefs[SettingsKeys.IMAGE_QUALITY] = quality
        }
    }

    override suspend fun updateAutoRefresh(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[SettingsKeys.AUTO_REFRESH] = enabled
        }
    }
}