public class Hopfield {
    private int[][] pesos; // Matriz de pesos para la red neuronal
    private int tamano; // Tamaño de la red (número de neuronas)

    // Constructor de la red de Hopfield que inicializa la matriz de pesos
    public Hopfield(int tamano) {
        this.tamano = tamano; // Establece el tamaño de la red
        this.pesos = new int[tamano][tamano]; // Inicializa la matriz de pesos
    }

    // Método para entrenar la red con un patrón
    public void entrenar(int[] patron) {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (i != j) { // Evita la auto-conexión
                    pesos[i][j] += patron[i] * patron[j]; // Actualiza la matriz de pesos
                }
            }
        }
    }

    // Método para recuperar un patrón a partir de un input
    public int[] recuperar(int[] input) {
        int[] output = input.clone(); // Clona el input para iniciar el output
        boolean cambio;

        do {
            cambio = false; // Bandera para verificar si hay cambios
            for (int i = 0; i < tamano; i++) {
                int sum = 0; // Suma de las conexiones
                for (int j = 0; j < tamano; j++) {
                    sum += pesos[i][j] * (output[j] == 1 ? 1 : -1); // Calcula la suma de los productos
                }
                // Establece el nuevo valor según la suma
                int newValue = sum > 0 ? 1 : 0; // Umbral de activación
                if (newValue != output[i]) { // Si el valor ha cambiado
                    output[i] = newValue; // Actualiza el output
                    cambio = true; // Marca que hubo un cambio
                }
            }
        } while (cambio); // Repite hasta que no haya cambios

        return output; // Devuelve el patrón recuperado
    }

    public static void main(String[] args) {
        // Define el patrón del aro (10x10)
        int[] patron = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
            0, 1, 1, 0, 0, 1, 1, 0, 0, 0,
            0, 1, 0, 0, 0, 0, 0, 1, 0, 0,
            0, 1, 0, 0, 0, 0, 0, 1, 0, 0,
            0, 1, 1, 0, 0, 1, 1, 0, 0, 0,
            0, 0, 1, 1, 1, 1, 1, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        };

        // Agregar ruido
        int[] ruidoInput = patron.clone();
        ruidoInput[20] = 0; // Cambiar un valor para simular ruido

        // Crea una instancia de la red de Hopfield
        Hopfield hopfield = new Hopfield(100);
        hopfield.entrenar(patron); // Entrena la red con el patrón del aro

        // Recupera el patrón para demostrar que funciona
        int[] output = hopfield.recuperar(ruidoInput); // Usar el input alterado

        // Imprime el patrón recuperado
        System.out.println("Patrón recuperado:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(output[i * 10 + j] + " "); 
            }
            System.out.println(); 
        }
    }
}
