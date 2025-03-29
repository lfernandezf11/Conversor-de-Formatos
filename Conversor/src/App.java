import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);
    static String ruta = ""; 
    static File carpeta = null; 
    static File ficheroSeleccionado = null; 
    static String contenidoFichero = ""; 

    public static void main(String[] args) throws Exception {
        int opcion;
        do {
            opcion = Menu1();
            switch (opcion) {
                case 1 -> SeleccionarCarpeta();
                case 2 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }

            if (carpeta != null && opcion != 2) {
                Menu2(); 
            }
        } while (opcion != 2);
    }

    public static int Menu1() {
        System.out.println("\n-------------------- MENÚ  --------------------");
        MostrarEstadoActual(); 
        System.out.println("\n\n1. Seleccionar carpeta");
        System.out.println("2. Salir");
        System.out.print("Elija una opción: ");
        return sc.nextInt();
    }

    public static void SeleccionarCarpeta() {
        sc.nextLine(); // Limpiar buffer
        System.out.print("\nIntroduce la ruta de la carpeta: ");
        ruta = sc.nextLine();

        carpeta = new File(ruta);
        if (carpeta.exists() && carpeta.isDirectory()) {
            System.out.println("\nLa carpeta '" + ruta + "' existe y es válida.\n");
        } else {
            System.out.println("\nLa ruta especificada no existe o no es una carpeta válida.\n");
            carpeta = null; // Reiniciar para evitar problemas :(
        }
    }

    public static void Menu2() {
        int opcion;
        do {
            System.out.println("\n-------------------- MENÚ  --------------------");
            MostrarEstadoActual(); 
            System.out.println("\n\n1. Seleccionar fichero");
            System.out.println("2. Lectura de fichero");
            System.out.println("3. Volver al menú anterior");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> SeleccionarFichero();
                case 2 -> LeerFichero();
                case 3 -> System.out.println("Volviendo al menú anterior...");
                case 4 -> System.exit(0);
                default -> System.out.println("Opción no válida.");
            }

            if (ficheroSeleccionado != null && opcion == 1) {
                Menu3(); 
            }
        } while (opcion != 3);
    }

    public static void SeleccionarFichero() {
        if (carpeta == null) {
            System.out.println("\nPrimero debes seleccionar una carpeta en el Menú 1.");
            return;
        }

    /* MostrarDetallesCarpeta(carpeta);*/

        sc.nextLine(); // Limpiar buffer
        System.out.print("\nIntroduce el nombre del fichero dentro de la carpeta seleccionada: ");
        String nombreFichero = sc.nextLine();

        File fichero = new File(carpeta, nombreFichero);
        if (fichero.exists() && fichero.isFile()) {
            ficheroSeleccionado = fichero;
            System.out.println("\nFichero '" + nombreFichero + "' seleccionado correctamente.");
        } else {
            System.out.println("\nEl fichero especificado no existe o no es válido.");
            ficheroSeleccionado = null; // Reiniciar para evitar problemas
        }
    }

    public static void LeerFichero() {
        if (ficheroSeleccionado == null) {
            System.out.println("\nPrimero debes seleccionar un fichero en el Menú 2.");
            return;
        }

        contenidoFichero = ""; // Reiniciar contenido de antes

        try (Scanner lectorFichero = new Scanner(ficheroSeleccionado)) {
            while (lectorFichero.hasNextLine()) {
                contenidoFichero += lectorFichero.nextLine() + "\n";
            }
            System.out.println("\nFichero leido correctamente.");
        } catch (IOException e) {
            System.out.println("\nError al leer el fichero: " + e.getMessage());
        }
    }

    public static void Menu3() {
        int opcion;
        do {
            System.out.println("\n-------------------- MENÚ  --------------------");
            MostrarEstadoActual(); 
            System.out.println("\n\n1. Conversión a CSV");
            System.out.println("2. Conversión a JSON");
            System.out.println("3. Conversión a XML");
            System.out.println("4. Volver al menú anterior");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> ConvertirFormato("csv");
                case 2 -> ConvertirFormato("json");
                case 3 -> ConvertirFormato("xml");
                case 4 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    public static void ConvertirFormato(String formato) {
        if (contenidoFichero.isEmpty()) {
            System.out.println("\nPrimero debes leer un fichero en el Menú 2.");
            return;
        }

        sc.nextLine(); // Limpiar el puto buffer
        System.out.print("\nIntroduce el nombre del archivo de salida (sin extensión): ");
        String nombreSalida = sc.nextLine();

        File archivoSalida = new File(carpeta, nombreSalida + "." + formato);

        try {
            if (archivoSalida.createNewFile()) {
                java.nio.file.Files.writeString(archivoSalida.toPath(), contenidoFichero);
                System.out.println("\nArchivo convertido y guardado como: " + archivoSalida.getAbsolutePath());
            } else {
                System.out.println("\nNo se pudo crear el archivo de salida.");
            }
        } catch (IOException e) {
            System.out.println("\nError al crear el archivo: " + e.getMessage());
        }
    }


    public static void MostrarEstadoActual() {
        
        String rutaCarpetaSeleccionada = ruta.isEmpty() ? "" : ruta;
        String ficheroSeleccionadoNombre = (ficheroSeleccionado == null) ? "" : ficheroSeleccionado.getName();
    
        System.out.println("\nEstado actual:");
        System.out.println("Ruta de la carpeta seleccionada: " + rutaCarpetaSeleccionada);
        System.out.println("Fichero seleccionado: " + ficheroSeleccionadoNombre);
        System.out.println("Contenido de la carpeta seleccionada:");
        if (carpeta == null) {
            System.out.println("");
            return;
        }
    
        File[] archivos = carpeta.listFiles(); 
        if (archivos != null && archivos.length > 0) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    System.out.println("[Carpeta] " + archivo.getName());
                } else {
                    System.out.println("[Archivo] " + archivo.getName());
                }
            }
        } else {
            System.out.println("La carpeta está vacía.");
        }
    }
    
}
