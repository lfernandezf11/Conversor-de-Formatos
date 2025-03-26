import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * @author Angel Villorina
    * @author Lucía Fernández Florencio
    * @param fichero Lista de HashMaps con los datos a escribir.
    */
public class Csv implements LecturaEscritura{

    private static final String nombre = "ficheroCSV.csv";

    
   
        
    

    @Override
    public boolean leer(File path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
        }
    }
    
    /**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * 
    * @param fichero Lista de HashMaps con los datos a escribir.
    */
    @Override
    public boolean escribir(List<HashMap<String, String>> fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
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
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
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




