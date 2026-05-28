package com.dripn.hola

import android.Manifest
import android.content.Intent // Para lanzar la app de la cámara
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore // Para acceder al recurso de captura
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        val botonEjecutar = findViewById<Button>(R.id.btnEjecutar)
        val textoLinea1 = findViewById<TextView>(R.id.linea1)

        // Función auxiliar para abrir la vista de la cámara sin guardar nada
        fun abrirVistaCamara() {
            val intentCamara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intentCamara)
        }

        // El "Lanzador" que espera la respuesta del usuario (Camino del NO que pasa a SOLICITAR)
        val solicitarPermisoLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { requestGranted ->
            if (requestGranted) {
                // CAMINO SÍ: El usuario aceptó por primera vez, abrimos la imagen
                textoLinea1.text = "¡Acceso concedido al Hardware!"
                abrirVistaCamara()
            } else {
                // CAMINO NO: El usuario rechazó
                textoLinea1.text = "Permiso denegado por seguridad"
            }
        }

        botonEjecutar.setOnClickListener {
            // VERIFICAR: ¿Ya tenemos el permiso aprobado en el teléfono?
            val estadoPermiso = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            if (estadoPermiso == PackageManager.PERMISSION_GRANTED) {
                // CAMINO SÍ DIRECTO: Como ya estaba aprobado de antes, abrimos la imagen de inmediato
                textoLinea1.text = "El hardware ya estaba activo"
                abrirVistaCamara()
            } else {
                // CAMINO NO: No lo tiene, disparamos la ventana flotante del sistema
                solicitarPermisoLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}
