package com.tarmeez.notificationapplication.data.response

data class Notification(
    val description: String,
    val id: String,
    val title: String,
    val type: String,
    val user_id: String,
    val token: String
)