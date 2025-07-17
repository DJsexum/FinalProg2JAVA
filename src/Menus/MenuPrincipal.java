package Menus;

import java.util.Scanner;

public class MenuPrincipal
{
    // Scanner único y estático para toda la clase
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. MENU DE CLIENTES");
            System.out.println("2. MENU DE EMPLEADOS");
            System.out.println("3. MENU DE USUARIOS");
            System.out.println("4. MENU DE PRODUCTOS");
            System.out.println("5. MENU DE VENTAS");
            System.out.println("6. MENU DE MOVIMIENTOS");
            System.out.println("0. SALIR");
            System.out.print("SELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt())
            {
                System.out.print("\nINGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                    MenuCliente.mostrarMenu();
                break;

                case 2:
                    MenuEmpleado.mostrarMenu();
                break;

                case 3:
                    MenuUsuario.mostrarMenu();
                break;

                case 4:
                    MenuProducto.mostrarMenu();
                break;

                case 5:
                    MenuVenta.mostrarMenu();
                break;

                case 6:
                    MenuMovimiento.mostrarMenu();
                break;

                case 0:
                    System.out.println("\nGRACIAS POR USAR NUESTRO SISTEMA.\n");
                break;

                default:
                    System.out.println("\nOPCION INVALIDA. INTENTE NUEVAMENTE.\n");
            }
        }
        while (opcion != 0);
    }
}