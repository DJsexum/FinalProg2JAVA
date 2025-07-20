package Menus;

import Clases.Proveedor;
import java.util.Scanner;

public class MenuProveedor
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│              MENU DE PROVEEDORES              │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│          [1] ALTA DE PROVEEDOR                │");
            System.out.println("│          [2] LISTAR PROVEEDORES               │");
            System.out.println("│          [3] BUSCAR PROVEEDOR POR DNI         │");
            System.out.println("│          [4] MODIFICAR PROVEEDOR              │");
            System.out.println("│          [5] BAJA DE PROVEEDOR                │");
            System.out.println("│          [0] VOLVER AL MENU PRINCIPAL         │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt())
            {
                System.out.print("INGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion)
            {
                case 1:
                    // Alta de proveedor
                    new Proveedor().altaPersona();
                break;

                case 2:
                    // Listar proveedores
                    new Proveedor().listarPersona();
                break;

                case 3:
                    // Buscar proveedor por DNI
                    System.out.print("INGRESE DNI DE PROVEEDOR: ");
                    int dniBuscar = Integer.parseInt(scanner.nextLine());
                    new Proveedor().buscaPersona(dniBuscar);
                break;

                case 4:
                    // Modificar proveedor
                    new Proveedor().modificarPersona();
                break;

                case 5:
                    // Baja de proveedor
                    System.out.print("INGRESE DNI DE PROVEEDOR A ELIMINAR: ");
                    int dniBaja = Integer.parseInt(scanner.nextLine());
                    Proveedor proveedorBaja = (Proveedor) new Proveedor().buscaPersona(dniBaja);
                    if (proveedorBaja != null)
                    {
                        new Proveedor().bajaPersona(proveedorBaja);
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO PROVEEDOR CON ESE DNI.");
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
}