package com.tarmeez.notificationapplication.di

import com.tarmeez.notificationapplication.data.Constant.Companion.BASE_URL
import com.tarmeez.notificationapplication.data.Constant.Companion.FIREBASE_URL
import com.tarmeez.notificationapplication.network.FirebaseApi
import com.tarmeez.notificationapplication.network.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideClient(headersInterceptor: HeadersInterceptor,
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }


    @Singleton
    @Provides
    fun provideHeadersInterceptor(): HeadersInterceptor{
        return HeadersInterceptor()
    }


    @Singleton
    @Provides
    @Named("provideRetrofit")
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }


    @Singleton
    @Provides
    @Named("provideFireBaseRetrofit")
    fun provideFireBaseRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(FIREBASE_URL)
                .client(okHttpClient)
                .build()
    }
    @Singleton
    @Provides
    @Named("provideApiService")
    fun provideApiService(retrofit: Retrofit): TestApi {
        return retrofit.create(TestApi::class.java)
    }


    @Singleton
    @Provides
    @Named("provideFirebaseApiService")
    fun provideFirebaseApiService(retrofit: Retrofit): FirebaseApi {
        return retrofit.create(FirebaseApi::class.java)
    }


    @Provides
    fun testApi (): TestApi {
        return provideApiService(provideRetrofit())
    }

    @Provides
    fun firebaseApi (): FirebaseApi {
        return  provideFirebaseApiService(provideFireBaseRetrofit(provideClient(provideHeadersInterceptor())))
    }

}