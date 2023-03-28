package com.example.sigapphova.model

import com.google.gson.annotations.SerializedName

data class Biresponse(
    @SerializedName("permiso_id")
    var permiso_id: String,
    @SerializedName("permiso_detalles")
    var permiso_detalles: String
)

data class BiPermisosRequest(
    @SerializedName("usu_id")
    var usu_id: String
)