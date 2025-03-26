import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Csv {

    private static final String nombre = "ficheroCSV.csv";

    /**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * @author Angel Villorina
    * @param fichero Lista de HashMaps con los datos a escribir.
    */
    public static void escribirCSV(ArrayList<HashMap<String, String>> fichero) {
        String path = "ficheroCSV.csv";
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            // Escribir cabeceras
            if (!fichero.isEmpty()) {
                HashMap<String, String> primeraFila = fichero.get(0);
                for (String cabecera : primeraFila.keySet()) {
                    bw.write(cabecera + ",");
                }
                bw.newLine();
            }
            for (HashMap<String, String> fila : fichero) {
                for (String valor : fila.values()) {
                    bw.write(valor + ",");
                }
                bw.newLine();
            }
    
            System.out.println("CSV creado exitosamente en: " + path);
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
    
    
    
    }
    

    /*
     * Logica para despues volver a este metodo un auto incremental
     * 
     * private static final String nombre = "ficheroCSV";
        private static int contador = 1;

        este bucle viene ya dntro del metodo

        do {
        nombreArchivo = nombre + "_" + contador + ".csv";
        file = new File(nombreArchivo);
        contador++;
    
        } while (file.exists());
     */




