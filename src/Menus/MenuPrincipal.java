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
            // DISEÑO PROFESIONAL DEL MENU PRINCIPAL
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                 MENU PRINCIPAL                │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│                 [1] CLIENTES                  │");
            System.out.println("│                 [2] EMPLEADOS                 │");
            System.out.println("│                 [3] USUARIOS                  │");
            System.out.println("│                 [4] PRODUCTOS                 │");
            System.out.println("│                 [5] VENTAS                    │");
            System.out.println("│                 [6] MOVIMIENTOS               │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│                 [0] SALIR                     │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("\nSELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt())
            {
                System.out.println("\nINGRESE UNA OPCION VALIDA: ");
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
                    System.out.println("┌─────────────────────────────────────────────────┐");
                    System.out.println("│           GRACIAS POR USAR EL SISTEMA           │");
                    System.out.println("└─────────────────────────────────────────────────┘\n");
                break;

                default:
                    System.out.println("\nOPCION INVALIDA INGRESE UNA OPCION VALIDA: \n");
            }
        }
        while (opcion != 0);
    }
}