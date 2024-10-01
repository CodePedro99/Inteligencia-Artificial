import java.util.ArrayList;
import java.util.List;

class Posicion {
    int x; // Coordenada X de la posición
    int y; // Coordenada Y de la posición

// Constructor para inicializar la posición con coordenadas X e Y
    Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Posicion(" + x + ", " + y + ")"; // Representación de la posición en forma de cadena
    }
}

public class busqProfu {
    
    static List<Posicion> posicionesProbadas = new ArrayList<>(); // Lista para almacenar las posiciones ya probadas

    public static void main(String[] args) {
        Posicion objetivo = new Posicion(2, 4); // Posición objetivo que el robot debe alcanzar
        buscarPosicion(new Posicion(0, 0), objetivo, 6); // Iniciar búsqueda desde la posición (0, 0) con un límite de 6 movimientos
    }

 // Método recursivo para buscar la posición objetivo
    private static boolean buscarPosicion(Posicion actual, Posicion objetivo, int limite) {

        // Verificar si la posición actual es la posición objetivo
        if (actual.x == objetivo.x && actual.y == objetivo.y) {
            System.out.println("Posición encontrada: " + actual); // Mostrar si se encontró la posición objetivo
            return true; // Devuelve verdadero si se encuentra la posición
        }
        
        // Verificar si se alcanzó el límite de búsqueda
        if (limite <= 0) {
            return false; // Devuelve falso si se ha agotado el límite
        }

        // Prueba nuevas posiciones (movimientos válidos)
        for (int dx = -1; dx <= 1; dx++) { // Movimientos en el eje X
            for (int dy = -1; dy <= 1; dy++) { // Movimientos en el eje y
                // Evitar movimientos diagonales
                if (Math.abs(dx) != Math.abs(dy)) { 
                    Posicion nuevaPos = new Posicion(actual.x + dx, actual.y + dy); // Calcular nueva posición
                    // Verificar si la nueva posición ya fue probada
                    if (!posicionesProbadas.contains(nuevaPos)) {
                        posicionesProbadas.add(nuevaPos); // Agregar nueva posición a la lista de probadas
                        // Llamada recursiva a la función
                        if (buscarPosicion(nuevaPos, objetivo, limite - 1)) {
                            return true; // Devuelve verdadero si se encontró la posición
                        }
                        posicionesProbadas.remove(nuevaPos); // Retrocede si no se encuentra
                    }
                }
            }
        }

        return false; // Devuelve falso si no se encontró la posición
    }
}
