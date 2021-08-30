package com.tarmeez.notificationapplication.data

import com.google.gson.annotations.SerializedName
import com.tarmeez.notificationapplication.data.response.NotificationX

data class NotificationBody(
        val notification: NotificationX? = null,
        @SerializedName("registration_ids") val registrationIds : List<String?> = listOf()
)