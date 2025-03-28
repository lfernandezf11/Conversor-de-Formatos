import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Xml {
    
    private static final String ficheroXML = "ficheroXML.xml";
    private static GestorInfo gestorXml = new GestorInfo();

    public static GestorInfo getGestor() {//Para llamada a gestor desde el main de prueba
        return gestorXml;
    }

    public static GestorInfo leerXml(File ficheroInput) {
        GestorInfo gestorXml = new GestorInfo();
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroInput))) {
            String linea;
            HashMap<String, String> item = null;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.startsWith("<elemento>")) {
                    item = new HashMap<>();
                } else if (linea.startsWith("</elemento>")) {
                    if (item != null && !item.isEmpty()) {
                        gestorXml.addItem(item);
                    }
                    item = null;
                } else if (item != null && linea.startsWith("<") && linea.endsWith(">")) {
                    int inicioEtiqueta = linea.indexOf("<") + 1;
                    int finEtiqueta = linea.indexOf(">");
                    int inicioEtiquetaCierre = linea.lastIndexOf("</") + 2;
                    
                    if (inicioEtiqueta < finEtiqueta && finEtiqueta < inicioEtiquetaCierre) {
                        String clave = linea.substring(inicioEtiqueta, finEtiqueta);
                        String valor = linea.substring(finEtiqueta + 1, inicioEtiquetaCierre - 1);
                        item.put(clave, valor);
                    }
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer XML: " + e.getMessage());
        }return gestorXml;
        
    }




    public static boolean escribirXml(List<HashMap<String, String>> fichero) {
        if (fichero.isEmpty()) {
            
            return false;
        }
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroXML))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bw.newLine();
            
            // Abrir el elemento raíz
            bw.write("<elementos>");
            bw.newLine();
            
            for (HashMap<String, String> fila : fichero) {
                bw.write("  <elemento>");
                bw.newLine();
                
                for (Map.Entry<String, String> entry : fila.entrySet()) {
                    String clave = entry.getKey();
                    String valor = entry.getValue();
                    bw.write("    <" + clave + ">" + valor + "</" + clave + ">");
                    bw.newLine();
                }
                
                bw.write("  </elemento>");
                bw.newLine();
            }
            
            // Cerrar el elemento raíz
            bw.write("</elementos>");
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
            return false;
        }
    }
    


}
