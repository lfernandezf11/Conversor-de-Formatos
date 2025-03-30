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

abstract class Csv{
    private static GestorInfo gestorCsv = new GestorInfo();
    
    public static GestorInfo getGestor() {//Para llamada a gestor desde el main
        return gestorCsv;
    }

    /**
    * Lee los datos de un archivo .csv y los introduce en una estructura de datos. 
    * 
    * @param ficheroInput archivo del que se extraen los datos.
    * @return La instancia de GestorInfo estático de la clase con los datos leídos.
    */
    public static GestorInfo leerCsv(File ficheroInput) {
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroInput))) {
            /* REGEX: coma + Lookahead para contar comillas: verifica que, desde ese punto hasta el final de la cadena, el número de comillas dobles sea par.
             * Si la coma está dentro de un campo entrecomillado, esto no se cumple, no se ha de utilizar esta coma para separar.
             * [^\"]* -> Cualquier cantidad (también cero) de caracteres no comillas.
             */
            String [] claves = br.readLine().trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String linea;
            while((linea = br.readLine()) != null){
                HashMap <String, String> item = new HashMap<>();
                String [] valores = linea.trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for(int i=0; i<claves.length; i++){
                    item.put(claves[i], valores[i]);
                }
                gestorCsv.addItem(item);
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } return gestorCsv;
    }
    
    /**
     *
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * 
    * @param estDatos Lista de HashMaps con los datos a escribir.
    * @return true si se escribe correctamente; false en caso contrario.
    */
    public static boolean escribirCsv(List<HashMap<String, String>> estDatos, File ficheroOutput) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroOutput))) {
            String newLine = "";
            // Escribir cabeceras
            if (!estDatos.isEmpty()) { //Escribimos sólo si fichero no está vacío.
                HashMap<String, String> primeraFila = estDatos.get(0);
                
                for (String cabecera : primeraFila.keySet()) {
                    newLine += cabecera + ",";
                }
                bw.write(newLine.substring(0, newLine.length()-1) + "\n");
                newLine = "";
            } 
            for (HashMap<String, String> fila : estDatos) {
                for (String valor : fila.values()) {
                    newLine += valor + ",";
                }
                bw.write(newLine.substring(0, newLine.length()-1) + "\n");
                newLine = "";
            } return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
        }
    } 
}
    

   




