package com.tarmeez.notificationapplication.notification_util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class NotificationUtil : FirebaseMessagingService() {

    var des: MutableLiveData<String> = MutableLiveData()
    var title: MutableLiveData<String> = MutableLiveData()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("onMessageReceived", "onMessageReceived: reciveed ${remoteMessage.notification?.body}")



        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val request = OneTimeWorkRequest.Builder(WorkManager::class.java)
            .setConstraints(constraints)

        val workManager = androidx.work.WorkManager.getInstance(this)
        workManager.enqueue(request.build())

//        GlobalScope.launch {
//            des.value = repository.remote.getNotification().body()?.notification?.get(0)?.description!!
//            title.value = repository.remote.getNotification().body()?.notification?.get(0)?.title!!
//        }
//
//        val notificationHelper = NotificationHelper(this, des.value!!, title.value!!)
//        val nb = notificationHelper.channelNotification
//        notificationHelper.manager!!.notify(5678, nb.build())


        super.onMessageReceived(remoteMessage)
    }


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }
}