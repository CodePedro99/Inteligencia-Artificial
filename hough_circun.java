import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;
import org.opencv.core.CvType;

public class hough_circun {

    public static void main(String[] args) {
        // Carga la librería de OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Carga la imagen 
        Mat image = Imgcodecs.imread("motor.png", Imgcodecs.IMREAD_GRAYSCALE);

        // Verifica si la imagen se cargó correctamente
        if (image.empty()) {
            System.out.println("Error al cargar la imagen");
            return;
        }

        // Pone el filtro Canny para detectar bordes
        Mat edges = new Mat();
        Imgproc.Canny(image, edges, 50, 150);

        // Detecta los círculos usando la Transformada de Hough para circunferencias
        Mat circles = new Mat();
        int radius = 50; // Radio conocido
        Imgproc.HoughCircles(edges, circles, Imgproc.CV_HOUGH_GRADIENT, 1, edges.rows() / 8, 100, 30, radius - 10, radius + 10);

        // Dibuja los círculos detectados sobre la imagen original
        for (int i = 0; i < circles.cols(); i++) {
            double[] circle = circles.get(0, i);
            int x = (int) circle[0];
            int y = (int) circle[1];
            int r = (int) circle[2];

            // Dibujo del círculo
            Imgproc.circle(image, new Point(x, y), r, new Scalar(0, 0, 255), 2);
            Imgproc.circle(image, new Point(x, y), 3, new Scalar(0, 255, 0), 3); // Centro del círculo
        }

        // Guarda la imagen con los círculos detectados
        Imgcodecs.imwrite("output_circulos.png", image);
    }
}
