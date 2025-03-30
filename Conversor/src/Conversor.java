import java.io.File;
import java.util.Scanner;
/**
 * @author Ángel Andrés Villorina
 * @author Lucía Fernández Florencio
 * @version 1.0
 */

public class Conversor {

    static Scanner sc = new Scanner(System.in);
    static String ruta = null; 
    static File carpeta = null; 
    static File ficheroSeleccionado = null; 
    static String contenidoFichero = ""; 
    static GestorInfo gestorActual = null;


    public static void main(String[] args) throws Exception {
        int opcion;
        do {
            opcion = menu1();
            switch (opcion) {
                case 1 -> seleccionarCarpeta();
                case 2 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }

            if (carpeta != null && opcion != 2) {
                menu2(); 
            }
        } while (opcion != 2);
    }

    public static int menu1() {
        mostrarEstadoActual();
        System.out.println(" ____________________________________________________________");
        System.out.println("|___________________________ MENÚ ___________________________|");
        System.out.println("| 1. Seleccionar carpeta.                                    |");
        System.out.println("| 2. Salir                                                   |");
        System.out.println("|------------------------------------------------------------|");
        System.out.println("| Elija una opción:                                          | ");
        System.out.println("|____________________________________________________________|");
        String input = sc.nextLine();
    
        /*Para controlar que también se manejan inputs no numéricas como default, recibimos la input como String
          e intentamos transformarla a Integer. Si no es posible, se asigna -1 a opción para que entre en el default.  */     
        int opcion;
        try {
            opcion = Integer.parseInt(input); //
        } catch (NumberFormatException e) {
            opcion = -1;
        }
        return opcion;
    }


    public static void seleccionarCarpeta() {
        System.out.print("\nIntroduce la ruta de la carpeta: ");
        ruta = sc.nextLine();
        try {
            carpeta = new File(ruta);
            if (carpeta.exists() && carpeta.isDirectory()) {
                System.out.println("\nLa carpeta '" + ruta + "' existe y es válida.\n");
            } else {
                System.out.println("\nLa ruta especificada no existe o no es una carpeta válida.\n");
                carpeta = null; 
                ruta = null;// Reiniciar para evitar problemas :(
            }
        } catch (Exception e) {
            System.err.println("Error en la búsqueda del directorio: " + e.getMessage());
        }
    }

    public static void menu2() {
        int opcion;
        do {
            mostrarEstadoActual();
            System.out.println(" ____________________________________________________________");
            System.out.println("|___________________________ MENÚ ___________________________|");
            System.out.println("| 1. Leer un fichero dentro de la carpeta especificada.      |");
            System.out.println("| 2. Seleccionar otra carpeta.                               |");
            System.out.println("| 3. Volver al menú anterior.                                |");
            System.out.println("| 4. Salir.                                                  |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("| Elija una opción:                                          |");
            System.out.println("|____________________________________________________________|");
            String input = sc.nextLine();
            try {
                opcion = Integer.parseInt(input); //
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1 -> seleccionarFichero();
                case 2 -> {
                            seleccionarCarpeta();
                            if (carpeta == null) {
                                System.out.println("La carpeta seleccionada no es válida. Regresando al menú principal.");
                            return; 
                            }  
                          }
                case 3 -> System.out.println("Volviendo al menú anterior...");
                case 4 -> System.exit(0);
                default -> System.out.println("Opción no válida.");
            }
            if (ficheroSeleccionado != null && opcion != 3) {
                menu3(); 
            } 
        } while (opcion != 3);
    }

    public static void seleccionarFichero() {
        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.print("\nIntroduce el nombre del fichero dentro de la carpeta seleccionada:\n ");
            try {
                String nombreFichero = sc.nextLine();

                File fichero = new File(carpeta, nombreFichero);
                if (fichero.exists() && fichero.isFile()) {
                    ficheroSeleccionado = fichero;
                    System.out.println("\nFichero '" + nombreFichero + "' seleccionado correctamente.");
                    leerFichero();
                } else {
                    System.out.println("\nEl fichero especificado no existe o no es válido.");
                    ficheroSeleccionado = null; // Reiniciar para evitar problemas
                }
            } catch (Exception e) {
                System.err.println("Error al seleccionar el fichero " + e.getMessage());
            } 
        }
    }

    public static void leerFichero() {
        if (ficheroSeleccionado == null) {
            System.out.println("\nNo se ha seleccionado ningún fichero.");
            return;
        }
        if (ficheroSeleccionado.exists() && ficheroSeleccionado.isFile()) {
            gestorActual = null;
            String nombre = ficheroSeleccionado.getName();
            String extension = nombre.substring(nombre.lastIndexOf('.') + 1).toLowerCase();
    
            switch (extension) {
                case "csv" -> {
                    Csv.leerCsv(ficheroSeleccionado);
                    gestorActual = Csv.getGestor();}
                
                case "xml" -> {
                    Xml.leerXml(ficheroSeleccionado);
                    gestorActual = Xml.getGestor();}
                
                case "json" -> {
                    Json.leerJson(ficheroSeleccionado);
                    gestorActual = Json.getGestor();}
                
                default ->
                    {System.out.println("\nFormato no soportado: " + extension);}    
            }
    
        }
        
        if (gestorActual != null && !gestorActual.getItems().isEmpty()) {
            System.out.println("\nDatos del fichero leidos y cargados correctamente en memoria");
            System.out.println(gestorActual.toString());
        } else {
            System.out.println("\nError al cargar los datos.");
        }
    }
    

    public static void menu3() {
        int opcion;
        do {
            mostrarEstadoActual();
            System.out.println(" ____________________________________________________________ ");
            System.out.println("|_____________________  MENÚ CONVERSOR ______________________|");
            System.out.println("| 1. Convertir el fichero a CSV.                             |");
            System.out.println("| 2. Convertir el fichero a JSON.                            |");
            System.out.println("| 3. Convertir el fichero a XML.                             |");
            System.out.println("| 4. Elegir otro fichero para leer.                          |");
            System.out.println("| 5. Volver al menú anterior                                 |");
            System.out.println("|------------------------------------------------------------|");
            System.out.println("| Elija una opción:                                          | ");
            System.out.println("|____________________________________________________________|");
            String input = sc.nextLine();
            try {
                opcion = Integer.parseInt(input); //
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            switch (opcion) {
                case 1 -> convertirFormato("csv");
                case 2 -> convertirFormato("json");
                case 3 -> convertirFormato("xml");
                case 4 -> {
                            seleccionarFichero();
                            if (ficheroSeleccionado == null) {
                                System.out.println("El fichero seleccionado no es válido. Regresando al menú anterior.");
                            return; 
                            }  
                          }
                case 5 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    public static void convertirFormato(String formato) {
        int contadorArchivos = 0;
        File ficheroSalida = null;
        if (gestorActual == null || gestorActual.getItems().isEmpty()) {
            System.out.println("\nPrimero debes leer un fichero válido");
            seleccionarFichero();
        }
        else{
            try {
                System.out.print("\nNombre del archivo de salida (sin extensión): ");
                String nombreSalida = sc.nextLine();
        
                ficheroSalida = new File(carpeta, nombreSalida + "." + formato.toLowerCase());
                while(ficheroSalida.exists()){
                    System.out.println("El nombre del fichero ya existe en esta ruta. Le asignaremos un número para distinguirlo del original:");
                    ficheroSalida = new File (carpeta, nombreSalida + (++contadorArchivos) + "." + formato.toLowerCase());
                }
            } catch (Exception e) {
                System.err.println("Error al formatear el archivo de salida.");
            }

        }
        boolean exito = false;
    
        switch (formato.toLowerCase()) {
            case "csv":
                exito = Csv.escribirCsv(gestorActual.getItems(), ficheroSalida);
                break;
            case "json":
                exito = Json.escribirJson(gestorActual.getItems(), ficheroSalida);
                break;
            case "xml":
                exito = Xml.escribirXml(gestorActual.getItems(), ficheroSalida);
                break;
        }
    
        if (exito) {
            System.out.println("\nConversión exitosa: " + ficheroSalida.getAbsolutePath());
        } else {
            System.out.println("\nError en la conversión");
        }
    }
    


    public static void mostrarEstadoActual() {
        String rutaCarpetaSeleccionada = (ruta == null || ruta.isEmpty()) ? "" : ruta;
        String ficheroSeleccionadoNombre = (ficheroSeleccionado == null) ? "" : ficheroSeleccionado.getName();
    
        System.out.println("\nESTADO ACTUAL:");
        System.out.println(" - Ruta de la carpeta: " + rutaCarpetaSeleccionada);
        System.out.println(" - Fichero seleccionado: " + ficheroSeleccionadoNombre);
        System.out.println(" - Contenido de la carpeta:");
        if (carpeta == null) {
            System.out.println("");
            return;
        }
    
        File[] archivos = carpeta.listFiles(); 
        if (archivos != null && archivos.length > 0) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("\t[Carpeta] " + archivo.getName());
                } else {
                    System.out.println("\t[Archivo] " + archivo.getName());
                }
            }
        } else {
            System.out.println("La carpeta está vacía.");
        }
    }
    
}
