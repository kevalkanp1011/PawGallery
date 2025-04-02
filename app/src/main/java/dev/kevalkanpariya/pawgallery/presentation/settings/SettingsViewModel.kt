package dev.kevalkanpariya.pawgallery.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kevalkanpariya.pawgallery.domain.models.UserSettings
import dev.kevalkanpariya.pawgallery.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    val settings = repository.settings
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            UserSettings()
        )

    fun updateCacheSize(size: Int) {
        viewModelScope.launch {
            repository.updateCacheSize(size)
        }
    }

    fun toggleAutoRefresh(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateAutoRefresh(enabled)
        }
    }

    fun updateImageQuality(quality: String) {
        viewModelScope.launch {
            repository.updateImageQuality(quality)
        }
    }
}