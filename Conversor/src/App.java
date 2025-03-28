
import java.util.Scanner;


public class App {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int opcion;
        
        
        do {

            System.out.println(Menu1());
            
            opcion = sc.nextInt();


            switch (opcion) {
                
                case 1 -> {System.out.println("Caso 1");}
                case 2 -> System.out.println("Saliendo del programa...");   
                default -> System.out.println("Opcion no valida");
            }

            
        } while (opcion != 2);

    }


    public static String Menu1(){
        String tAmarillo = "\u001B[33m";
        String reset = "\u001B[0m";
        String menu= tAmarillo +"----------------------------------------------------------\n"
                            + "|                                                        |\n"
                            + "|  "+"       Bienvenido a tu conversor de formatos" + reset + tAmarillo+"          |\n"
                            + "|                                                        |\n"
                            + "|--------------------------------------------------------|\n"
                            + "|      1. Seleccionar carpeta                            |\n" 
                            + "|      2. Salir                                          |\n"
                            + "|                                                        |\n"
                            + "----------------------------------------------------------\n"

                            + "Contenido de la carpeta seleccionada: " 
                            + "\n\nRuta de la carpeta seleccionada: "
                            + "\n\nElija una opción:\n" + reset;
        return menu;
    } 

    
}
