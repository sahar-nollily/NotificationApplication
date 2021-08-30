package com.tarmeez.notificationapplication.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class HeadersInterceptor :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        requestBuilder.addHeader("Authorization", "key=AAAARsb1ioI:APA91bFMCToXzlKdZjSn22CHKeGvFrW6mE6kcK0bl3vE8W3duYdY5DNdB_qAF6NfwBoXbwiGNt-ECS77DZi_5siBjBV7jVk-K7QhkJ1z9cK9mi0vgBVrSsinRBCcMnkLQhcoNDbrQYU4")


        return chain.proceed(requestBuilder.build())
    }
}