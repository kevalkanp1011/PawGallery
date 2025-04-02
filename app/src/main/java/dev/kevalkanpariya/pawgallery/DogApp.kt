package dev.kevalkanpariya.pawgallery

import android.app.Application
import dev.kevalkanpariya.pawgallery.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DogApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DogApp)
            modules(appModule)
        }
    }
}