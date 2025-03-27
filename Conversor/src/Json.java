import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
/**
* @author Lucía Fernández Florencio  
*/

abstract class Json{
    private static final String ficheroJson = "ficheroJson.json";
    private static GestorInfo gestorJson = new GestorInfo();
    
    public static GestorInfo getGestor() {//Para llamada a gestor desde el main
        return gestorJson;
    }
    
    /**
    * Lee los datos de un archivo .json y los introduce en una estructura de datos. 
    * 
    * @param ficheroInput archivo del que se extraen los datos.
    * @return La instancia de GestorInfo estático de la clase con los datos leídos.
    */
    public static GestorInfo leerJson(File ficheroInput) {
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroInput))) {
            HashMap <String, String> item;
            for(int i=1; i<ficheroInput.length(); i++)
                item = new HashMap<>();
                if(br.readLine().equals("{")){
                    do{
                        br.readLine();
                        String [] pares = br.readLine().trim().split(":");
                        item.put(pares[0], pares[1]);
                    } while(br.readLine() != "}");
                    gestorJson.addItem(item);
                }
            } 
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } return gestorJson;
    }
    
    /**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * 
    * @param fichero Lista de HashMaps con los datos a escribir.
    * @return true si se escribe correctamente; false en caso contrario.
    */
    /*public static boolean escribirCsv(List<HashMap<String, String>> fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ficheroCSV), true))) {
            String newLine = "";
            //Implementar comprobación ficheroCSV.length() == 0 and exists.
            // Escribir cabeceras
            if (!fichero.isEmpty()) { //Escribimos sólo si fichero no está vacío.
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
            } return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;*/
        }
    } 
}
    



    /*
     * Logica para despues volver a este metodo un auto incremental
     * 
     * private static final String ficheroCSV = "ficheroCSV";
        private static int contador = 1;

        este bucle viene ya dntro del metodo

        do {
        ficheroCSVArchivo = ficheroCSV + "_" + contador + ".csv";
        file = new File(ficheroCSVArchivo);
        contador++;
    
        } while (file.exists());
     */




