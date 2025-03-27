/*import java.io.BufferedReader;
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

/*abstract class Json{
    private static final String outputJson = "ficheroJson.json";
    
    /**
    * Lee los datos de un archivo .json y los introduce en una estructura de datos. 
    * 
    * @param ficheroJson archivo del que se extraen los datos.
    */
    /*public static GestorInfo leerJson(File ficheroJson) {
        GestorInfo gestor = new GestorInfo();
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroJson))) {
            while((br.readLine()) != "{"){
                br.readLine();
            }
            if((br.readLine()) == "{"){
                HashMap <String, String> item = new HashMap<>();
                String[] par = br.readLine().trim().split(":");
                item.put(par[0], par[1]);   
            }else if(br.readLine() == "}")
                
                String [] valores = linea.trim().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for(int i=0; i<claves.length; i++){
                    item.put(claves[i], valores[i]);
                }
                gestor.addItem(item);
                System.out.println(item.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } return gestor;
    }
    
    /**
    * Escribe los datos de una lista de HashMaps a un archivo CSV.
    * 
    * @param fichero Lista de HashMaps con los datos a escribir.
    */
    /*public static boolean escribirCsv(List<HashMap<String, String>> fichero) {
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
            } return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
        }
    } 
}*/