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
    public static GestorInfo leerJson(File ficheroInput){
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroInput))) {
            String linea;
            while((linea = br.readLine()) != null){
                if(linea.trim().equals("{")){
                    HashMap <String, String> item = new HashMap<>();
                    while((linea = br.readLine()) != null && !linea.trim().equals("},")){
                        String [] pares = linea.trim().split(":", 2);
                        if(pares.length == 2){ // Sólo asigna si la longitud del array es dos. Se saltará ']'.
                            String key = pares[0].trim().replaceAll("[\",\\.]", "");
                            String value = pares[1].trim().replaceAll("[\",\\.]", "");
                            item.put(key, value);
                        }
                    }gestorJson.addItem(item);   
                }
            } 

        }catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        } return gestorJson;
    }
    
    /**
    * Escribe los datos de una lista de HashMaps a un archivo JSON.
    * 
    * @param fichero Lista de HashMaps con los datos a escribir.
    * @return true si se escribe correctamente; false en caso contrario.
    */
    public static boolean escribirJson(List<HashMap<String, String>> fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ficheroJson), true))) {
            bw.write("[\n");
            if (!fichero.isEmpty()) { //Escribimos sólo si fichero no está vacío.
                for(int i=0; i<fichero.size(); i++){
                    bw.write(" {\n");
                    HashMap<String, String> elemento = fichero.get(i);
                    int contador = 0;
                    for(String par : elemento.keySet()){
                        bw.write("    \"" + par + "\": \"" + elemento.get(par) + "\"");
                        contador++;
                        if(contador == elemento.size()){
                            bw.write("\n }");
                        }else{
                            bw.write(",\n");
                        }
                    }
                    if(i == fichero.size()-1){
                        bw.write("\n]");
                    }else{
                        bw.write(",\n");
                    }
                }
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
     * private static final String ficheroCSV = "ficheroCSV";
        private static int contador = 1;

        este bucle viene ya dntro del metodo

        do {
        ficheroCSVArchivo = ficheroCSV + "_" + contador + ".csv";
        file = new File(ficheroCSVArchivo);
        contador++;
    
        } while (file.exists());
     */





