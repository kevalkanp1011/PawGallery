package dev.kevalkanpariya.pawgallery.di

import dev.kevalkanpariya.pawgallery.data.cache.DogCache
import dev.kevalkanpariya.pawgallery.data.cache.DogCacheImpl
import dev.kevalkanpariya.pawgallery.data.datasource.DogRemoteDataSource
import dev.kevalkanpariya.pawgallery.data.datasource.DogRemoteDataSourceImpl
import dev.kevalkanpariya.pawgallery.data.datasource.SettingsDataSource
import dev.kevalkanpariya.pawgallery.data.datasource.SettingsDataSourceImpl
import dev.kevalkanpariya.pawgallery.data.repoImpl.DogRepositoryImpl
import dev.kevalkanpariya.pawgallery.data.repoImpl.SettingsRepositoryImpl
import dev.kevalkanpariya.pawgallery.domain.repository.DogRepository
import dev.kevalkanpariya.pawgallery.domain.repository.SettingsRepository
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.DogViewModel
import dev.kevalkanpariya.pawgallery.presentation.settings.SettingsViewModel
import dev.kevalkanpariya.pawgallery.utils.settingsDataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    // Network
    single<HttpClient> {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    // Data Sources
    single<DogRemoteDataSource> { DogRemoteDataSourceImpl(get()) }
    single<SettingsDataSource> {
        SettingsDataSourceImpl(
            dataStore = androidContext().settingsDataStore
        )
    }

    // Cache
    single<DogCache> { DogCacheImpl(androidContext(), get()) }

    // Repository
    single<DogRepository> { DogRepositoryImpl(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    // ViewModels
    viewModelOf(::DogViewModel)
    viewModelOf(::SettingsViewModel)
}