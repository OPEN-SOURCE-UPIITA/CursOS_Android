package com.dripn.hola

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    // Variable para que cada alumno coloque su nombre exacto
    private val miIdentificador = "Daniel"

    private lateinit var tvTemperatura: TextView
    private lateinit var tvHumedad: TextView
    private lateinit var tvPresion: TextView
    private lateinit var tvEstadoPost: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a las vistas del Clima
        tvTemperatura = findViewById(R.id.tvTemperatura)
        tvHumedad = findViewById(R.id.tvHumedad)
        tvPresion = findViewById(R.id.tvPresion)
        val btnActualizarClima = findViewById<Button>(R.id.btnActualizarClima)

        // Referencias a las vistas de los Actuadores
        val btnEncender = findViewById<Button>(R.id.btnEncender)
        val btnApagar = findViewById<Button>(R.id.btnApagar)
        tvEstadoPost = findViewById(R.id.tvEstadoPost)

        // Listeners
        btnActualizarClima.setOnClickListener {
            obtenerDatosClimaGET()
        }

        btnEncender.setOnClickListener {
            enviarComandoLedPOST(1)
        }

        btnApagar.setOnClickListener {
            enviarComandoLedPOST(0)
        }
    }

    private fun obtenerDatosClimaGET() {
        thread {
            try {
                val url = URL("https://api.sputniklab.xyz/api/clima")
                val conexion = url.openConnection() as HttpURLConnection
                conexion.requestMethod = "GET"

                if (conexion.responseCode == HttpURLConnection.HTTP_OK) {
                    val lector = BufferedReader(InputStreamReader(conexion.inputStream))
                    val respuesta = lector.readText()
                    lector.close()

                    // Procesamiento del JSON
                    val json = JSONObject(respuesta)
                    val temp = json.getString("temperatura")
                    val hum = json.getString("humedad")
                    val pres = json.getString("presion")

                    // Actualización de la Interfaz (Hilo principal)
                    runOnUiThread {
                        tvTemperatura.text = "Temperatura: $temp °C"
                        tvHumedad.text = "Humedad: $hum %"
                        tvPresion.text = "Presión: $pres hPa"
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tvTemperatura.text = "Error de conexión GET"
                }
            }
        }
    }

    private fun enviarComandoLedPOST(estado: Int) {
        tvEstadoPost.text = "Enviando comando..."

        thread {
            try {
                val url = URL("https://api.sputniklab.xyz/api/led")
                val conexion = url.openConnection() as HttpURLConnection

                conexion.requestMethod = "POST"
                conexion.setRequestProperty("Content-Type", "application/json")
                conexion.doOutput = true

                // Construcción del Body con el formato acordado
                val cuerpoJson = "{\"id\": \"$miIdentificador\", \"encendido\": $estado}"

                val escritor = OutputStreamWriter(conexion.outputStream)
                escritor.write(cuerpoJson)
                escritor.flush()
                escritor.close()

                val codigoRespuesta = conexion.responseCode

                runOnUiThread {
                    if (codigoRespuesta == HttpURLConnection.HTTP_OK || codigoRespuesta == HttpURLConnection.HTTP_CREATED) {
                        tvEstadoPost.text = "Comando enviado: LED $estado"
                    } else {
                        tvEstadoPost.text = "Error del servidor: $codigoRespuesta"
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    tvEstadoPost.text = "Error de conexión POST"
                }
            }
        }
    }
}
