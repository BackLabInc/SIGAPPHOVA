package com.example.sigapphova.data

import android.content.Context

class SharedPreferences(val context: Context) {
    val SHARED_NAME = "SIGdtb"
    val SHARED_USER_NAME = "user"
    val SHARED_USER_ID = "id"
    val SHARED_USER_LOGIN = "isLogin"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveUser(name:String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveIdUser(id:Int){
        storage.edit().putInt(SHARED_USER_ID, id).apply()
    }

    fun saveLoginUser(islogin:Boolean){
        storage.edit().putBoolean(SHARED_USER_LOGIN, islogin).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_NAME, "")!!
    }
    fun getId():Int{
        return storage.getInt(SHARED_USER_ID, 0)
    }

    fun getIsLogin():Boolean{
        return storage.getBoolean(SHARED_USER_LOGIN, false)
    }


}