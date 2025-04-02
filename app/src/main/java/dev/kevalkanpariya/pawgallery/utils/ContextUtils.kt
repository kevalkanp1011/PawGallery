package dev.kevalkanpariya.pawgallery.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
val Context.dogsDataStore: DataStore<Preferences> by preferencesDataStore(name = "recent_dogs")