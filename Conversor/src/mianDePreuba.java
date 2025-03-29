import java.io.File;

public class mianDePreuba {
    public static GestorInfo gestor = new GestorInfo();
    
    public static File pathCsv = new File("Conversor\\docs\\coches.csv");
    public static File pathJson = new File("Conversor\\docs\\coches.json");
    public static File pathXml = new File("Conversor\\docs\\coches.xml");
    public static void main(String[] args) {


                
                Xml.leerXml(pathXml);
                Xml.escribirXml(Xml.getGestor().getItems()); 

                Csv.leerCsv(pathCsv);
                Csv.escribirCsv(Csv.getGestor().getItems());

                Json.leerJson(pathJson);
                Json.escribirJson(Json.getGestor().getItems());

                

    }

}


