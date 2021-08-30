package com.tarmeez.notificationapplication.data.response

data class FirebaseResponse(
    val canonical_ids: Int,
    val failure: Int,
    val multicast_id: Long,
    val success: Int
)