package com.tarmeez.notificationapplication.network


import com.tarmeez.notificationapplication.data.response.NotificationResponse
import com.tarmeez.notificationapplication.data.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TestApi {

    @GET("/getNotification")
    suspend fun getNotification(): Response<NotificationResponse>

    @GET("/getToken")
    suspend fun getToke(@Query("username") username: String): Response<TokenResponse>
}
