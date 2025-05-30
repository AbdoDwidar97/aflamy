package me.dwidar.aflamy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import me.dwidar.aflamy.shell.local_storage_manager.LocalStorageManagerImpl

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val localStorageManager = LocalStorageManagerImpl()
        localStorageManager.init()
    }
}