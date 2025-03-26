import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Csv {

    private static final String nombre = "ficheroCSV.csv";

    public static void escribirCSV(ArrayList<HashMap<String, String>> fichero) {
        File file = new File(nombre);
        String path = file.getAbsolutePath();
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            ArrayList<String> cabeceras = new ArrayList<>(fichero.get(0).keySet());
            
            bw.write(String.join(",", cabeceras));
            bw.newLine();
            
            for (HashMap<String, String> fila : fichero) {
                List<String> valores = new ArrayList<>();
                for (String cabecera : cabeceras) {
                    valores.add(fila.getOrDefault(cabecera, ""));
                }
                bw.write(String.join(",", valores));
                bw.newLine();
            }
            System.out.println("CSV creado exitosamente en: " + path);
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}



