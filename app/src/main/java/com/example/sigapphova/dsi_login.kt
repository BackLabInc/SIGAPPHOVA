package com.example.sigapphova

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.sigapphova.bi.Hv_dsi_main_bi
import com.example.sigapphova.data.APIService
import com.example.sigapphova.data.SharedPreferences
import com.example.sigapphova.databinding.ActivityDsiLoginBinding
import com.example.sigapphova.model.LoginRequest
import com.example.sigapphova.model.LoginResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class dsi_login : AppCompatActivity() {
    companion object{
        lateinit var pref:SharedPreferences
    }
    private lateinit var binding: ActivityDsiLoginBinding
    private var CAMERA_PERMISSION_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDsiLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = SharedPreferences(baseContext)

        veryfyLoginUser()
        requestPermisionsDSI()
        binding.btnLoginSig.setOnClickListener {
            Log.e("SIGAPP", "INGRESANDO A LA APP")
            val psw = binding.edPswUserSig.text
            val user = binding.etUserSig.text
            if(!psw.isEmpty()){
                val loginData: LoginRequest = LoginRequest(
                    usu_psw = psw.toString(),
                    usu_alias = user.toString()
                )
                gotoLogin(loginData)
            }else{

                Log.e("SIG_APP", "NO PUEDES ACCEDER SI NO USAS CREDENCIALES")
            }
        }

    }

    private fun veryfyLoginUser() {
        if(pref.getIsLogin()){
            var intent = Intent(this, Hv_dsi_main_bi::class.java)
            startActivity(intent)
        }
    }

    private fun gotoLogin(data: LoginRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<List<LoginResponse>> = getRetrofit().create(APIService::class.java).loginUser(data)
            val users:List<LoginResponse>? = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    Log.e("SIG_APP", "TODO BIEN EN EL INICIO DE SESIÓN")
                    var usuario = users?.get(0)?.usu_nom.toString()
                    var id_Usuario = users?.get(0)?.usu_id.toString()
                    pref.saveLoginUser(true)
                    pref.saveUser(usuario)
                    pref.saveIdUser(id_Usuario.toInt())
                    //Log.e("SIG_APP_LOGIN", "NOMBRE "+usuario+" ID USUARIO "+ id_Usuario)
                    gotoMain()
                }else{
                    Log.e("SIG_APP", "TODO MAL EN EL INICIO DE SESIÓN")
                }
            }
        }
    }

    private fun requestPermisionsDSI() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // El permiso ya ha sido concedido
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION), CAMERA_PERMISSION_CODE)
        }
        Toast.makeText(this, "Solicitar permisos", Toast.LENGTH_LONG)
    }

    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://8ba7-201-103-212-176.ngrok.io/sig_app/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun gotoMain(){
        var intent = Intent(this, Hv_dsi_main_bi::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // El permiso ha sido concedido
                } else {
                    // El permiso ha sido denegado
                }
                return
            }
            // Aquí se pueden manejar otras solicitudes de permisos
            else -> {
                // No se reconoce el código de solicitud
            }
        }
    }
}

