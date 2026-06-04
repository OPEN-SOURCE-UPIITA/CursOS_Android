package com.dripn.hola // Cambia a tu nombre de paquete

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnEntrar = findViewById<Button>(R.id.btnEntrarSistema)

        btnEntrar.setOnClickListener {
            // El estándar industrial para saltar entre Activities
            val intentoNavegacion = Intent(this, MainActivity::class.java)
            startActivity(intentoNavegacion)
            
            // Opcional: Finalizamos esta actividad para que si presionan "Atrás", no regresen aquí
            finish()
        }
    }
}
