package com.tarmeez.notificationapplication.repository


import com.tarmeez.notificationapplication.network.RemoteSourceData
import javax.inject.Inject

class Repository @Inject constructor(private val remoteSourceData: RemoteSourceData) {

    val remote = remoteSourceData
}