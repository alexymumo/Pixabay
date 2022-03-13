package com.example.pixabay

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PixabayApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}

/*
* It is the parent component of the application meaning other components can access the dependency
* It application component available
* Hilt currently supports following android classes
* Application(@HiltAndroidApp)
* ViewModel(@HiltViewModel)
* Activity
* Fragment
* View
* Service
* BroadcastReceiver
* */

/*
* @AndroidEntryPoint
* -Generates a individual Hilt component for each android class in your project
* @Inject annotation on the constructor of a class to tell Hilt how to provide instances of that class
* @Module - It informs Hilt how to provide  instances of certain types
* @InstallIn - tell Hilt which Android class each module will be used or installed in
* @Provides
* */

