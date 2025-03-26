import java.util.ArrayList;
import java.util.HashMap;

public class mianDePreuba {
    public static void main(String[] args) {
            ArrayList<HashMap<String, String>> ficheroPalEjemplo = new ArrayList<>();


    HashMap<String, String> fila1 = new HashMap<>();
    fila1.put("nombre", "angel");
    fila1.put("edad", "21");
    fila1.put("Ciudad", "merida");


    HashMap<String, String> fila2 = new HashMap<>();
    fila2.put("nombre", "lucia");
    fila2.put("Edad", "29");
    fila2.put("ciudad", "los angeles");


    ficheroPalEjemplo.add(fila1);
    ficheroPalEjemplo.add(fila2);


    Csv.escribirCSV(ficheroPalEjemplo);
    }

}
