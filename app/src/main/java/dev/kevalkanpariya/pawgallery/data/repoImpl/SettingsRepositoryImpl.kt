package dev.kevalkanpariya.pawgallery.data.repoImpl

import dev.kevalkanpariya.pawgallery.data.datasource.SettingsDataSource
import dev.kevalkanpariya.pawgallery.domain.models.UserSettings
import dev.kevalkanpariya.pawgallery.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataSource: SettingsDataSource
) : SettingsRepository {

    override val settings: Flow<UserSettings> = dataSource.settings

    override suspend fun updateCacheSize(size: Int) {
        dataSource.updateCacheSize(size)
    }

    override suspend fun updateImageQuality(quality: String) {
        if(quality in setOf("sd", "hd")) {
            dataSource.updateImageQuality(quality)
        }
    }

    override suspend fun updateAutoRefresh(enabled: Boolean) {
        dataSource.updateAutoRefresh(enabled)
    }
}