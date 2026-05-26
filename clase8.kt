package com.dripn.hola // Modifica el paquete al que hayas configurado

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Variable para el contador de procesos
    private var contadorProcesos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // SOLUCIÓN A ROTACIÓN: Recuperamos el dato si la pantalla se recreó
        if (savedInstanceState != null) {
            contadorProcesos = savedInstanceState.getInt("NUESTRO_CONTADOR")
        }
        
        val botonEjecutar = findViewById<Button>(R.id.btnEjecuta)
        val textoLinea1: String = findViewById(R.id.linea1)
        botonEjecutar.setOnClickListener {
            contadorProcesos++
            textoLinea1.text = "Procesos: $contadorProcesos"
    }

    // Solución a la rotación: Guardamos el dato en la mochila antes de que la pantalla muera
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("NUESTRO_CONTADOR", contadorProcesos)
    }
}
