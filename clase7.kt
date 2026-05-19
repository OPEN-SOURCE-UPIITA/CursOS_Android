package com.dripn.hola // Ajustar esta sentencia a su propio paquete

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button // Con esto importamos la biblioteca para que nuestro botón funcione

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Enlazamos el botón del XML usando su ID
        val botonEjecutar = findViewById<Button>(R.id.btnEjecutar)

        // Programamos la acción del clic
        botonEjecutar.setOnClickListener {
            finish() // Le dice a Android que cierre la actividad actual
        }
    }
}
