import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;
import org.opencv.core.CvType;

public class hough_recta {

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

        // Definición de los parámetros para la Transformada de Hough
        double rho = 1; // Resolución en píxeles
        double theta = Math.PI / 180; // Resolución en radianes
        int threshold = 100; // Umbral para la detección de líneas

        // Matriz para almacenar las líneas detectadas
        Mat lines = new Mat();
        Imgproc.HoughLines(edges, lines, rho, theta, threshold);

        // Dibuja las líneas detectadas sobre la imagen original
        for (int i = 0; i < lines.rows(); i++) {
            double[] line = lines.get(i, 0);
            double rhoVal = line[0];
            double thetaVal = line[1];

            double a = Math.cos(thetaVal);
            double b = Math.sin(thetaVal);
            double x0 = a * rhoVal;
            double y0 = b * rhoVal;
            double x1 = Math.round(x0 + 1000 * (-b));
            double y1 = Math.round(y0 + 1000 * (a));
            double x2 = Math.round(x0 - 1000 * (-b));
            double y2 = Math.round(y0 - 1000 * (a));

            Imgproc.line(image, new Point(x1, y1), new Point(x2, y2), new Scalar(0, 0, 255), 2);
        }

        // Guarda la imagen con las líneas detectadas
        Imgcodecs.imwrite("output_lineas.png", image);
    }
}
