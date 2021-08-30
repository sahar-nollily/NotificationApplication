package com.tarmeez.notificationapplication.data

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.tarmeez.notificationapplication.notification_util.WorkManagerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){
    @Inject
    lateinit var workManagerFactory: WorkManagerFactory

    override fun onCreate() {
        super.onCreate()

        WorkManager.initialize(this, Configuration.Builder().setWorkerFactory(workManagerFactory).build())
    }
}