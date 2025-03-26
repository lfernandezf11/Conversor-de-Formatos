import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class GestorInfo {
    List<HashMap <String, String>> items;

public GestorInfo(){
    items = new ArrayList<HashMap<String, String>>();
}

public void addItem(HashMap<String, String> item){
    try {
        items.add(item);
    } catch (Exception e) {
        System.err.println("Error" + e.getMessage());
    }
}

public int searchByIndex(int index){
    try {
        int buscar = -1;
        for(int i=0; i<items.size(); i++){
            
        }
    } catch (Exception e) {
        // TODO: handle exception
    }
}

}
