package com.tarmeez.notificationapplication.network

import com.tarmeez.notificationapplication.data.response.FirebaseResponse
import com.tarmeez.notificationapplication.data.NotificationBody
import com.tarmeez.notificationapplication.data.response.NotificationResponse
import com.tarmeez.notificationapplication.data.response.TokenResponse
import retrofit2.Response
import javax.inject.Inject


class RemoteSourceData @Inject constructor(private val testApi: TestApi, private val firebaseApi: FirebaseApi) {

    suspend fun getNotification(): Response<NotificationResponse>{
            return testApi.getNotification()
    }

    suspend fun getToke(username: String): Response<TokenResponse>{
        return testApi.getToke(username)
    }

    suspend fun sendNotification(notificationBody: NotificationBody): Response<FirebaseResponse>{
        return firebaseApi.sendNotification(notificationBody)
    }
}