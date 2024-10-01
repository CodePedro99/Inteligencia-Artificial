import java.util.PriorityQueue;

class Estado {

Posicion posicion; // Posición actual del robot
int costo; // Costo o bien distancia al objetivo

// Constructor para inicializar el estado con la posición y el costo
Estado(Posicion posicion, int costo) {
    this.posicion = posicion; // Asignar posición
    this.costo = costo; // Asignar el costo desde la posición al objetivo
    }
}

public class busqPrimeroMejor {
    
    public static void main(String[] args) {

    Posicion objetivo = new Posicion(3, 3); // Posición objetivo que el robot debe alcanzar
    PriorityQueue<Estado> cola = new PriorityQueue<>((a, b) -> Integer.compare(a.costo, b.costo)); // Cola de prioridad con los estados a explorar, ordenada en base al costo
        
    // Agregar estado inicial a la cola
    cola.add(new Estado(new Posicion(0, 0), calcularCosto(new Posicion(0, 0), objetivo)));

    // Procesar la cola mientras queden estados por explorar
    while (!cola.isEmpty()) {
        Estado estadoActual = cola.poll(); // Obtener y remover el estado con menor costo
        System.out.println("Procesando posición: " + estadoActual.posicion); // Mostrar posición actual

        // Verificar si se encontró la posición objetivo
        if (estadoActual.posicion.x == objetivo.x && estadoActual.posicion.y == objetivo.y) {
            System.out.println("Posición encontrada: " + estadoActual.posicion); // Mostrar posición encontrada
            break; // Terminar el bucle si se encuentra la posición
         }

            // Generar y agregar nuevos estados
        for (int dx = -1; dx <= 1; dx++) { // Movimientos en el eje X
            for (int dy = -1; dy <= 1; dy++) { // Movimientos en el eje y
                // Evitar movimientos diagonales
                if (Math.abs(dx) != Math.abs(dy)) {
                     Posicion nuevaPos = new Posicion(estadoActual.posicion.x + dx, estadoActual.posicion.y + dy); // Calcular nueva posición
                    int nuevoCosto = calcularCosto(nuevaPos, objetivo); // Calcular el nuevo costo
                    cola.add(new Estado(nuevaPos, nuevoCosto)); // Agregar nuevo estado a la cola
                }
             }
         }
    }
 }

// Método para calcular el costo 
private static int calcularCosto(Posicion actual, Posicion objetivo) {
     return Math.abs(actual.x - objetivo.x) + Math.abs(actual.y - objetivo.y);
}
}
