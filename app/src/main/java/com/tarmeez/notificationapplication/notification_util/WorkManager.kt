package com.tarmeez.notificationapplication.notification_util

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import com.tarmeez.notificationapplication.repository.Repository
import javax.inject.Inject

class WorkManager @Inject constructor(
        private val repository: Repository,
        private val context: Context,
        private val params: WorkerParameters): CoroutineWorker(context,params) {
    override suspend fun doWork(): Result {
        Log.d("onMessageReceived", "doWork: ")
        val des = repository.remote.getNotification().body()?.notification?.get(0)?.description!!
        val title = repository.remote.getNotification().body()?.notification?.get(0)?.title!!

        val notificationHelper = NotificationHelper(context, des, title)
        val nb = notificationHelper.channelNotification
        notificationHelper.manager!!.notify(5678, nb.build())
        return Result.success()
    }

    class Factory @Inject constructor(
        private val repository: Repository
    ): ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return WorkManager(repository ,appContext, params)
        }
    }
}