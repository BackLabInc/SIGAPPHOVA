package com.example.sigapphova.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetwrokDSI {
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://40ca-187-189-114-64.ngrok.io/sig_app/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}