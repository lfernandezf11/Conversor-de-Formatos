import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
* @author Angel Andres Villorina  
*/

abstract class Xml {
    
    private static final String ficheroXml = "ficheroXml.xml";
    private static GestorInfo gestorXml = new GestorInfo();

    public static GestorInfo getGestor() { // Para llamada a gestor desde el main
        return gestorXml;
    }

    /**
     * Lee los datos de un archivo .xml y los introduce en una estructura de datos.
     *
     * @param ficheroInput archivo del que se extraen los datos.
     * @return La instancia de GestorInfo estático de la clase con los datos leídos.
     */
    public static GestorInfo leerXml(File ficheroInput) {
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroInput))) {
            String linea;
            HashMap<String, String> elemento = null;
            String key = null;
            boolean dentroDeElemento = false; // Para rastrear si estamos dentro de un bloque <...> (ej: <coche>)
            
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.isEmpty() || linea.startsWith("<?xml") || 
                    (linea.startsWith("<") && linea.endsWith(">") && !linea.contains("</"))) {
                    continue;
                }
                
                if (linea.startsWith("<") && !linea.startsWith("</") && !dentroDeElemento) {
                    elemento = new HashMap<>();
                    dentroDeElemento = true;
                } 

                else if (linea.startsWith("</") && dentroDeElemento) {
                    gestorXml.addItem(elemento);
                    elemento = null;
                    dentroDeElemento = false;
                } 

                else if (dentroDeElemento && linea.startsWith("<") && linea.contains("</")) {
                    key = linea.substring(1, linea.indexOf(">"));
                    String valor = linea.substring(linea.indexOf(">") + 1, linea.indexOf("</"));
                    elemento.put(key, valor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer: " + e.getMessage());
        }
        return gestorXml;
    }
    /**
     * Escribe los datos de una lista de HashMaps a un archivo XML.
     *
     * @param fichero Lista de HashMaps con los datos a escribir.
     * @return true si se escribe correctamente; false en caso contrario.
     */
    public static boolean escribirXml(List<HashMap<String, String>> fichero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(ficheroXml), true))) {
            bw.write("<elemento>\n");
            for (HashMap<String, String> elemento : fichero) {
                bw.write("  <elemento>\n");
                for (String key : elemento.keySet()) {
                    bw.write("    <" + key + ">" + elemento.get(key) + "</" + key + ">\n");
                }
                bw.write("  </elemento>\n");
            }
            bw.write("</elementos>\n");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
        }
    }


}
