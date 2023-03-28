package com.example.sigapphova.data

import com.example.sigapphova.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @Headers("Content-Type: application/json")
    @POST("login")

    suspend fun loginUser(@Body loginreques: LoginRequest): Response<List<LoginResponse>>

    @POST("getPermisos")
    suspend fun getPermisos(@Body permisosUser: BiPermisosRequest): Response<List<Biresponse>>
}
