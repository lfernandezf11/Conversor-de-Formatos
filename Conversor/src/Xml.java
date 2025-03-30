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
            String valor = null;
            boolean dentroDeElemento = false; // Una bandera para saber si estamos dentro de  <...> o no
            
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && !linea.startsWith("<?xml")) { //Trabajamos con las líneas que no están vacías y no son la de inicio de doc. xml
                    // Creamos el mapa al detectar una línea <texto> que no contiene etiqueta de cierre (</)
                    if (linea.startsWith("<") && linea.endsWith(">") && !linea.contains("</")) {
                        br.readLine();
                        elemento = new HashMap<>();
                        dentroDeElemento = true;
                    } 
                    // Cerramos el mapa al detectar una línea correspondiente a etiqueta de cierre (empieza en </)
                    else if (linea.startsWith("</") && dentroDeElemento) {
                        gestorXml.addItem(elemento); // Añadimos al ArrayList el mapa y marcamos el boolean como falso.
                        elemento = null;
                        dentroDeElemento = false;
                    } 
                    // Una vez dentro del elemento, rellenamos clave y valor sustrayendo por índices.
                    else if (dentroDeElemento && linea.startsWith("<") && linea.contains("</")) {
                        int tagApertura = linea.indexOf(">");
                        int tagCierre = linea.indexOf("</");
                        if (tagApertura != -1 && tagCierre != -1 && tagApertura < tagCierre) {
                            key = linea.substring(1, tagApertura);
                            valor = linea.substring(tagApertura + 1, tagCierre);
                            elemento.put(key, valor);
                        }
                    }
                    
                }
            } 
        }  catch (IOException e) {
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
    public static boolean escribirXml(List<HashMap<String, String>> estDatos, File archivoSalida) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {
            bw.write("<elementos>\n");
            for (HashMap<String, String> elemento : estDatos) {
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
