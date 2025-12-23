package com.ilkproje.bitirme

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {  // Web server'dan nabız verisini almak için bir GET isteği
    @GET("/") // "temperature" web server'daki endpoint adıdır. Bunu kendi serverınıza göre düzenleyin.
    fun getTemperature(): Call<Map<String, Double>>
}
