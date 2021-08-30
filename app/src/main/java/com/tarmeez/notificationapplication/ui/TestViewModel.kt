package com.tarmeez.notificationapplication.ui

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.tarmeez.notificationapplication.data.NetworkResult
import com.tarmeez.notificationapplication.data.NotificationBody
import com.tarmeez.notificationapplication.data.response.NotificationResponse
import com.tarmeez.notificationapplication.repository.Repository
import kotlinx.coroutines.launch

import retrofit2.Response

class TestViewModel @ViewModelInject
constructor(private val repository: Repository, application: Application)
    :AndroidViewModel(application){

    var notificationResponse: MutableLiveData<NetworkResult<NotificationResponse>> = MutableLiveData()

    fun sendNotification (id: List<String?>){

        viewModelScope.launch {
            notificationResponse.value = NetworkResult.Loading()

            try {
                val response = repository.remote.sendNotification(NotificationBody(notification = null, registrationIds = id))
                Log.d("onMessageReceived", "sendNotification: ${response.body()?.success}")

            }catch (e: Exception){
                notificationResponse.value = NetworkResult.Failure(" Not Found")

            }
        }
    }

    fun getToken(username: String) = viewModelScope.launch {
        notificationResponse.value = NetworkResult.Loading()

        try {
            val response = repository.remote.getToke(username)

            Log.d("onMessageReceived", "getToken: ${response.body()?.token}")
            sendNotification(listOf("${response.body()?.token}",
                "cHH9WCeXRV2RLXLw2kTq4E:APA91bHZ3NUCZ5m_2wlGHQQCzFccg0E_BfMcft91ouAK3ftotUE8PZx1SRdnNl3u4juJkwN1VlisuC2O8K6_60p-LsmNpxNfMMiyMlOhkwsIay8Ad5KYAnpVijiUK8eejOepYKvCfIEI"))


        }catch (e: Exception){
            notificationResponse.value = NetworkResult.Failure(" Not Found")
        }


    }

    fun getNotification() = viewModelScope.launch {
        notificationResponse.value = NetworkResult.Loading()

        try {
            val response = repository.remote.getNotification()
            notificationResponse.value = handleResponse(response)

            Log.d("onMessageReceived", "getNotification: ${notificationResponse.value!!.data}")

        }catch (e: Exception){
            notificationResponse.value = NetworkResult.Failure(" Not Found")
        }


    }


    private fun handleResponse(response: Response<NotificationResponse>): NetworkResult<NotificationResponse>{
        when {
            response.message().toString().contains("timeout") ->
                return NetworkResult.Failure("Timeout")

            response.code()== 402 -> {
                return NetworkResult.Failure("API KEY Limited")
            }

            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe!!)
            }

            else -> {
                return NetworkResult.Failure(response.message())
            }
        }
    }
}