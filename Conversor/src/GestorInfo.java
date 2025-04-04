import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Lucía Fernández Florencio
 */ 

public class GestorInfo {
    List<HashMap <String, String>> items;

public GestorInfo(){
    items = new ArrayList<HashMap<String, String>>();
}

public List<HashMap<String,String>> getItems() {
    return this.items;
}

public void setItems(List<HashMap<String,String>> items) {
    this.items = items;
}

public boolean addItem(HashMap<String, String> item){
    try {
        items.add(item);
        return true;
    } catch (Exception e) {
        System.err.println("Error" + e.getMessage());
        return false;
    }
}

public int searchByIndex(int index){
    int buscar = -1;
    try {
        for(int i=0; i<items.size(); i++){
            if(items.indexOf(items.get(i)) == index){
                buscar = i;
            }
        }
    } catch (Exception e) {
        System.err.println("Error en la operación de búsqueda: " + e.getMessage());
    }return buscar;
}

public boolean modifyItem(int index, HashMap<String, String> map){
    if(searchByIndex(index) != -1){
        try {
            items.set(index, map);
            return true;
        } catch (Exception e) {
            System.err.println("Error en la operación de modificación: " + e.getMessage());
        }
    }return false;
}

public boolean deleteItem(int index){
    if(searchByIndex(index) != -1){
        try {
            items.remove(index);
            return true;
        } catch (Exception e) {
            System.err.println("Error en la operación de borrado: " + e.getMessage());
        }
    }return false;
}

@Override
public String toString(){
    String arrayString = "";
    try{
        for(HashMap <String, String> elemento : items){
        arrayString += elemento + "\n";             
        } 
    } catch (Exception e) {
        System.err.println("Error en la extracción de datos: " + e.getMessage());
    }
    return arrayString;
    }

    /**
     * Limpia todos los items almacenados en el gestor.
     * esto lo usaremos en los case de switch, al final de cada 
     * escritura, para poder asegurarnos de que no nunca este lleno
     */
    public void clear() {
        items.clear();  // Vacía la lista de items
    }
}
















