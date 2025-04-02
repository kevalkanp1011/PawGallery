package dev.kevalkanpariya.pawgallery.data.cache

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.kevalkanpariya.pawgallery.domain.models.Dog
import dev.kevalkanpariya.pawgallery.domain.repository.SettingsRepository
import dev.kevalkanpariya.pawgallery.utils.dogsDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

interface DogCache {
    suspend fun addDog(dog: Dog)
    suspend fun getDogs(): Flow<List<Dog>>
    suspend fun clear()
}

class DogCacheImpl(
    private val context: Context,
    private val settings: SettingsRepository
) : DogCache {
    private var maxSize = 20
    private val dogs = mutableListOf<Dog>()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        scope.launch {
            settings.settings.collect {
                maxSize = it.cacheSize
            }
        }
    }

    override suspend fun addDog(dog: Dog) {
        dogs.add(0, dog)
        if (dogs.size > maxSize) dogs.removeAt(dogs. lastIndex)
        saveToDataStore()
    }

    override suspend fun getDogs(): Flow<List<Dog>> {
        loadFromDataStore()
        return flow { emit(dogs.toList()) }
    }

    override suspend fun clear() {
        dogs.clear()
        saveToDataStore()
    }

    private suspend fun saveToDataStore() {
        context.dogsDataStore.edit { preferences ->
            preferences[DOGS_KEY] = Json.encodeToString(ListSerializer(Dog.serializer()), dogs)
        }
    }

    private suspend fun loadFromDataStore() {
        val json = context.dogsDataStore.data
            .map { it[DOGS_KEY] ?: "[]" }
            .first()
        dogs.clear()
        dogs.addAll(Json.decodeFromString<List<Dog>>(json))
    }

    companion object {
        val DOGS_KEY = stringPreferencesKey("recent_dogs")
    }
}