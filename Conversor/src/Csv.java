import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
/**
* @author Angel Villorina
* @author Lucía Fernández Florencio  
*/

public class Csv{
    private static final String outputCSV = "ficheroCSV.csv";
    
    /**
    * Lee los datos de un archivo .csv y los introduce en una estructura de datos. 
    * 
    * @param ficheroCSV archivo del que se extraen los datos.
    */
    public boolean leerCsv(File ficheroCSV) {
        GestorInfo gestor = new GestorInfo();
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroCSV))) {
            /* REGEX: coma + Lookahead para contar comillas: verifica que, desde ese punto hasta el final de la cadena, el número de comillas dobles sea par.
             * Si la coma está dentro de un campo entrecomillado, esto no se cumple, no se ha de utilizar esta coma para separar.
             * [^\"]* -> Cualquier cantidad (también cero) de caracteres no comillas.
             */
            String [] claves = br.readLine().trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            while(br.readLine() != null){
                HashMap <String, String> item = new HashMap<>();
                String [] valores = br.readLine().trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for(int i=0; i<claves.length; i++){
                    item.put(claves[i], valores[i]);
                }
                gestor.addItem(item);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
            return false;
        }
    }
    
    /**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * 
    * @param fichero Lista de HashMaps con los datos a escribir.
    */
    public static boolean escribirCsv(List<HashMap<String, String>> fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputCSV))) {
            String newLine = "";
            // Escribir cabeceras
            if (!fichero.isEmpty()) {
                HashMap<String, String> primeraFila = fichero.get(0);
                
                for (String cabecera : primeraFila.keySet()) {
                    newLine += cabecera + ",";
                }
                bw.write(newLine.substring(0, newLine.length()-1) + "\n");
                newLine = "";
            } 
           
            for (HashMap<String, String> fila : fichero) {
                for (String valor : fila.values()) {
                    newLine += valor + ",";
                }
                bw.write(newLine.substring(0, newLine.length()-1) + "\n");
                newLine = "";
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
     * private static final String outputCSV = "ficheroCSV";
        private static int contador = 1;

        este bucle viene ya dntro del metodo

        do {
        outputCSVArchivo = outputCSV + "_" + contador + ".csv";
        file = new File(outputCSVArchivo);
        contador++;
    
        } while (file.exists());
     */




