package com.example.sigapphova.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("usu_nom")
    var usu_nom: String,
    @SerializedName("usu_id")
    var usu_id: String
)
data class LoginList(
    val user: List<LoginResponse>
)
data class LoginRequest(
    @SerializedName("usu_alias")
    var usu_alias: String,
    @SerializedName("usu_psw")
    var usu_psw: String
)