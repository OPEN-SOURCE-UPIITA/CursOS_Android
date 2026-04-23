// El Plano (Clase). Esta es la clase
// Lo que está entre paréntesis se llama "Constructor", es la lista de requisitos obligatorios que el objeto necesita para "nacer"
class SensorTermico(val id: String, val ubicacion: String) {

// Este es un atributo. Cada sensor tiene un dato de temperatura que puede variar (var)
    var temperatura: Double = 36.5 

  // Este es un método. ¿Qué acción hará? Imprimir en pantalla un mensaje con la temperatura y ubicación de un sensor cada vez que se ejecute.
    fun alerta() { 
        println("Alerta en $ubicacion: $temperatura°C")
    }
}

// Función principal
fun main() {
  
    // Los Objetos Reales (Instancias), creados a partir de la clase.
    val sensor1 = SensorTermico("S-001", "Cama 10") // Observa cómo se llama a la clase SensorTermico y se le envían los requisitos que se piden.
    val sensor2 = SensorTermico("S-002", "Cama 12")

    sensor1.temperatura = 38.5 // Aquí asignamos al atributo del objeto sensor1 el valor de 38.5. Esto se puede hacer con la medida que obtengamos de un sensor real.
    sensor1.alerta() // Mandamos llamar al método sensor1.alerta. En una implementación real, lo hacemos con alguna de las estructuras de control.
}
