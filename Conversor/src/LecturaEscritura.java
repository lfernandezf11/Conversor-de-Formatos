import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface LecturaEscritura {
    public boolean leer(File path);
    public boolean escribir(List<HashMap<String, String>> fichero);

}
