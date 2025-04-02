package dev.kevalkanpariya.pawgallery.data.datasource

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object SettingsKeys {
    val CACHE_SIZE = intPreferencesKey("cache_size")
    val IMAGE_QUALITY = stringPreferencesKey("image_quality")
    val AUTO_REFRESH = booleanPreferencesKey("auto_refresh")
}