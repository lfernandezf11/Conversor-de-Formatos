import java.io.File;
import java.util.HashMap;
import java.util.List;


public class mianDePreuba {
    public static GestorInfo gestor = new GestorInfo();
   
    public static File pathCsv = new File("docs\\coches.csv");
    public static File pathJson = new File("docs\\coches.json");
    public static File pathXml = new File("docs\\coches.xml");
    public static void main(String[] args) {


                
                Xml.leerXml(pathXml);
                Xml.escribirXml(Xml.getGestor().getItems()); 

                Csv.leerCsv(pathCsv);
                Csv.escribirCsv(Csv.getGestor().getItems());

                Json.leerJson(pathJson);
                Json.escribirJson(Json.getGestor().getItems());

                

    /*HashMap<String, String> fila1 = new HashMap<>();
    fila1.put("nombre", "angel");
    fila1.put("edad", "21");
    fila1.put("Ciudad", "merida");


    HashMap<String, String> fila2 = new HashMap<>();
    fila2.put("nombre", "lucia");
    fila2.put("Edad", "29");
    fila2.put("ciudad", "los angeles");

    HashMap<String, String> fila3 = new HashMap<>();
    fila3.put("nombre", "Carlos");
    fila3.put("edad", "35");
    fila3.put("ciudad", "Madrid");

    HashMap<String, String> fila4 = new HashMap<>();
    fila4.put("nombre", "María");
    fila4.put("edad", "28");
    fila4.put("ciudad", "Barcelona");

    HashMap<String, String> fila5 = new HashMap<>();
    fila5.put("nombre", "Pedro");
    fila5.put("edad", "40");
    fila5.put("ciudad", "Valencia");

    HashMap<String, String> fila6 = new HashMap<>();
    fila6.put("nombre", "Ana");
    fila6.put("edad", "32");
    fila6.put("ciudad", "Sevilla");

    HashMap<String, String> fila7 = new HashMap<>();
    fila7.put("nombre", "Luis");
    fila7.put("edad", "27");
    fila7.put("ciudad", "Bilbao");


    gestor.addItem(fila1);
    gestor.addItem(fila2);
    gestor.addItem(fila3);
    gestor.addItem(fila4);
    gestor.addItem(fila5);
    gestor.addItem(fila6);
    gestor.addItem(fila7);*/


        /*Csv.leerCsv(pathCsv);
        gestor = Csv.getGestor();
        
        System.out.println(gestor.toString());
        Csv.escribirCsv(gestor.getItems());
        
        
        if(pathXml.exists())
        xmlPruena.leerXml(pathXml);
        gestor = xmlPruena.getGestor();

        System.out.println(gestor.toString());
        Xml.escribirXml(gestor.getItems());

        /*if (pathXml.exists()) {
            System.out.println("Archivo encontrado: " + pathXml.getAbsolutePath());
            Xml.leerXml(pathXml);
            gestor = Xml.getGestor();
        
            // Verificar si hay datos después de leer
            System.out.println("Datos leídos del XML: " + gestor.getItems());
        
            // Escribir solo si hay datos
            if (!gestor.getItems().isEmpty()) {
                Xml.escribirXml(gestor.getItems());
                System.out.println("Se ha escrito el XML correctamente.");
            } else {
                System.out.println("No hay datos para escribir.");
            }
        } else {
            System.out.println("El archivo XML no existe: " + pathXml.getAbsolutePath());
        }*/
        
        /*Xml.leerXml(pathXml);
        gestor = Xml.getGestor();
        
        System.out.println(gestor.toString());
        Xml.escribirXml(gestor.getItems());

        /*File ficheroInput = new File("docs\\coches.xml");
        List<HashMap<String, String>> resultado = Xml.leerXml(ficheroInput);

        // Imprimir el resultado
        for (HashMap<String, String> item : resultado) {
            System.out.println(item);
        }*/

        /*File xmlFile = new File("docs\\coches.xml");
        List<HashMap<String, String>> items = Xml.leerXml(xmlFile);
        
        if (!Xml.escribirXml(items)) {
            System.out.println("no sirve");
            
        }else{
            System.out.println("si sirve");
        }
        
        // Imprimir los resultados
        for (HashMap<String, String> item : items) {
            System.out.println(item);
        }*/
        /*xmlPruena.leerXml(pathXml);
        System.out.println(gestor.toString());

        /*Xml.escribirXml(gestor.getItems());*/
    }



 
}


