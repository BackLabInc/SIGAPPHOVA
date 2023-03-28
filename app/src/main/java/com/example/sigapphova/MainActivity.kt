package com.example.sigapphova

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    val DURACION: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide();
        this.window.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN)
        val logo = findViewById<ImageView>(R.id.dsiSplash)
        Glide.with(this).load(R.drawable.dsi_logowithtext).into(logo)

        cambiarActivity()
    }

    private fun cambiarActivity() {
        android.os.Handler().postDelayed(Runnable {
            val intent = Intent(this, dsi_login::class.java)
            startActivity(intent)
        }, DURACION)
    }
}