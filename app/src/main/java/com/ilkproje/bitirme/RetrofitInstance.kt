package com.ilkproje.bitirme

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.67.8:80/" // Web server adresinizi buraya yazın.

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    // Retrofit instance'ını oluşturuyoruz.
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // ApiService instance
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    // Eğer api'yi başka bir yerde kullanmak isterseniz
    fun RetrofitInstance(): Retrofit {
        return retrofit
    }
}