package CERFID;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CERFID addterapeuta = new CERFID();
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Registrar Terapeuta");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Programar Cita");
            System.out.println("4. Verificar Historia Clinica");
            System.out.println("5. Reporte de Citas");
            System.out.println("0. Salir");
            System.out.print("\n seleccionar opción : ");

            if (scan.hasNextInt()) {
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("DNI del Terapeuta: ");
                        String numDNI = scan.next();
                        System.out.println("Nombre del Terapeuta: ");
                        String nombre = scan.next();
                        System.out.println("Apellido del Terapeuta: ");
                        String apellidos = scan.next();
                        System.out.println("Codigo de Trabaajdor: ");
                        String codigoTrabajador = scan.next();
                        addterapeuta.agregarpersonas(new Terapeutas(numDNI,nombre,apellidos,codigoTrabajador));
                        // Add logic to handle adding an item
                        break;
                    case 2:
                        System.out.println("You chose to view items.");
                        // Add logic to handle viewing items
                        break;
                    case 3:
                        System.out.println("You chose to delete an item.");
                        // Add logic to handle deleting an item
                        break;
                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        addterapeuta.imprimirListaTrabajadores();

    }

}
