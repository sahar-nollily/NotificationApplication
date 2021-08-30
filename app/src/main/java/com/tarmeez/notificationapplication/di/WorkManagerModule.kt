package com.tarmeez.notificationapplication.di

import androidx.work.ListenableWorker
import com.tarmeez.notificationapplication.notification_util.ChildWorkerFactory
import com.tarmeez.notificationapplication.notification_util.WorkManager
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerKey(val value: KClass<out ListenableWorker>)

@Module
@InstallIn(ApplicationComponent::class)
interface WorkManagerModule {
    @Binds
    @IntoMap
    @WorkerKey(WorkManager::class)
    fun bindWorkManager(factory: WorkManager.Factory): ChildWorkerFactory
}