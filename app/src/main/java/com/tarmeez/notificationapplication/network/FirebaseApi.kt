package com.tarmeez.notificationapplication.network

import com.tarmeez.notificationapplication.data.response.FirebaseResponse
import com.tarmeez.notificationapplication.data.NotificationBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FirebaseApi {

    @POST("send")
    suspend fun sendNotification(@Body notificationBody: NotificationBody): Response<FirebaseResponse>
}