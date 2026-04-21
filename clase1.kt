package com.dripn.utools

fun main() {
    println("=== DEMOSTRACIÓN DE ESTRUCTURAS EN KOTLIN ===\n")

    // 1. IF - Decisión simple
    println("--- 1. IF (Decisión Clínica) ---")
    val oxigeno = 85
    val estado = if (oxigeno < 90) "Crítico" else "Estable"
    println("Paciente con $oxigeno% oxígeno: $estado\n")

    // 2. WHEN - Múltiples condiciones
    println("--- 2. WHEN (Clasificación de Sangre) ---")
    val tipoSangre = "A+"
    when (tipoSangre) {
        "A+" -> println("Tipo A+: Compatible con A+ y AB+")
        "O-" -> println("Tipo O-: Donador Universal")
        "AB+" -> println("Tipo AB+: Receptor Universal")
        else -> println("Tipo $tipoSangre: No reconocido")
    }
    println()

    // 3. BUCLE FOR - Monitoreo por Lotes
    println("--- 3. FOR (Monitoreo por Lotes) ---")
    println("Tomando presión arterial a 5 pacientes:")
    for (i in 1..5) {
        println("   Paciente $i: Realizando medición...")
    }

    println("\nSimulación de monitoreo cada 10 segundos:")
    for (segundo in 0..60 step 10) {
        println("   Segundo $segundo: Realizando lectura de sensor...")
    }

    println("\nLista de equipos detectados vía Bluetooth:")
    val dispositivos = listOf("Monitor ECG", "Báscula Digital", "Oxímetro BLE")
    for (dispositivo in dispositivos) {
        println("   • $dispositivo listo para vincular")
    }
    println()

    // 4. WHILE - Bucle Condicional
    println("--- 4. WHILE (Monitoreo en Tiempo Real) ---")
    var pulsoActivo = true
    var contadorSegundos = 0

    // Monitoreamos mientras el sensor esté activo, máximo 5 segundos para la demo
    while (pulsoActivo && contadorSegundos < 5) {
        println("   [Segundo $contadorSegundos] Graficando señal ECG... (Pulso detectado)")
        contadorSegundos++
    }
    println("   Monitoreo completado con éxito.\n")

    // 5. OPERADOR ELVIS - Null Safety
    println("--- 5. OPERADOR ELVIS (Manejo de Fallos) ---")
    // Simulamos que un sensor puede o no estar enviando datos (null)
    val lecturaSensor: Double? = null

    // El operador ?: dice: "Usa el valor del sensor, pero si es null, usa este respaldo"
    val valorFinal = lecturaSensor ?: 0.0

    println("   Lectura recibida: $lecturaSensor")
    println("   Valor procesado por la App: $valorFinal (Respaldo activado para evitar errores)")
    println()

    // 6. SALIDAS ANTICIPADAS - Return y Break (Emergencias)
    println("--- 6. SALIDAS ANTICIPADAS (Protocolos de Salida) ---")

    // Ejemplo de Break: Detener escaneo si encontramos lo que buscamos
    println("Escaneando red en busca del Servidor Galira...")
    for (ip in 1..254) {
        if (ip == 10) {
            println("   ¡Servidor encontrado en la IP .10! Deteniendo búsqueda.")
            break
        }
        // println("   Buscando en IP .$ip...")
    }

    // Ejemplo de Return
    println("\nEvaluación de urgencia:")
    println("   Resultado: ${evaluarEmergencia(45)}")
}

// Función con return anticipado para lógica de urgencias
fun evaluarEmergencia(oxigeno: Int): String {
    if (oxigeno < 85) return "PRIORIDAD ALTA - Administrar Oxígeno"
    return "PACIENTE ESTABLE - Continuar Observación"
}
