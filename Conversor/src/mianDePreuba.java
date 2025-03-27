import java.util.List;
import java.util.HashMap;

public class mianDePreuba {
    public static GestorInfo gestor = new GestorInfo();
    public static void main(String[] args) {

    HashMap<String, String> fila1 = new HashMap<>();
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
    gestor.addItem(fila7);
    


    Csv.escribirCsv(gestor.getItems());
    

    System.out.println(gestor.toString());
    }

}
