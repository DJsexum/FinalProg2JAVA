package Menus;

import java.util.Scanner;
import Clases.Cliente;

public class MenuCliente
{
    /*
    Muestra el menú de clientes y ejecuta la opción seleccionada.
    Es un método estático para poder llamarlo desde MenuPrincipal sin crear instancias.
    */
    public static void mostrarMenu()
    {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                MENU DE CLIENTES               │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│           [1] ALTA DE CLIENTE                 │");
            System.out.println("│           [2] LISTAR CLIENTES                 │");
            System.out.println("│           [3] BUSCAR CLIENTE POR DNI          │");
            System.out.println("│           [4] MODIFICAR CLIENTE               │");
            System.out.println("│           [5] BAJA DE CLIENTE                 │");
            System.out.println("│           [0] VOLVER AL MENU PRINCIPAL        │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt()) 
            {
                System.out.print("INGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                    new Cliente().altaPersona();
                break;

                case 2:
                    new Cliente().listarPersona();
                break;

                case 3:
                    System.out.print("INGRESE DNI: ");
                    int dni = leerEntero(scanner);
                    new Cliente().buscaPersona(dni);
                break;

                case 4:
                    System.out.print("INGRESE DNI DEL CLIENTE A MODIFICAR: ");
                    int dniMod = leerEntero(scanner);
                    Cliente clienteMod = (Cliente) new Cliente().buscaPersona(dniMod);
                    if (clienteMod != null)
                    {
                        clienteMod.modificarPersona();
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO CLIENTE CON ESE DNI.");
                        }
                break;

                case 5:
                    System.out.print("INGRESE DNI DEL CLIENTE A DAR DE BAJA: ");
                    int dniBaja = leerEntero(scanner);
                    Cliente clienteBaja = (Cliente) new Cliente().buscaPersona(dniBaja);
                    if (clienteBaja != null)
                    {
                        clienteBaja.bajaPersona(clienteBaja);
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO CLIENTE CON ESE DNI.");
                        }
                break;

                case 0:
                    System.out.println("\n┌───────────────────────────────────────────────┐");
                    System.out.println("│           VOLVIENDO AL MENU PRINCIPAL         │");
                    System.out.println("└───────────────────────────────────────────────┘\n");
                break;

                default:
                    System.out.println("OPCION INVALIDA. INTENTE DE NUEVO.");
            }
        }
        while (opcion != 0);
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero(Scanner scanner)
    {
        while (!scanner.hasNextInt()) 
        {
            System.out.print("INGRESE UN NUMERO VALIDO: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}