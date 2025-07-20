package Menus;

import Clases.Empleado;
import java.util.Scanner;

public class MenuEmpleado
{
    // Scanner estático para que pueda usarse en métodos estáticos
    private static Scanner scanner = new Scanner(System.in);

    /*
    Muestra el menú de empleados y ejecuta la opción seleccionada.
    Es un método estático para poder llamarlo desde MenuPrincipal sin crear instancias.
    */
    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│               MENU DE EMPLEADOS               │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│         [1] ALTA DE EMPLEADO                  │");
            System.out.println("│         [2] LISTAR EMPLEADOS                  │");
            System.out.println("│         [3] BUSCAR EMPLEADO POR LEGAJO        │");
            System.out.println("│         [4] MODIFICAR EMPLEADO                │");
            System.out.println("│         [5] BAJA DE EMPLEADO                  │");
            System.out.println("│         [0] SALIR                             │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");
            opcion = leerEntero();

            switch (opcion)
            {
                case 1:
                    altaEmpleado();
                break;

                case 2:
                    listarEmpleados();
                break;

                case 3:
                    buscarEmpleado();
                break;

                case 4:
                    modificarEmpleado();
                break;

                case 5:
                    bajaEmpleado();
                break;

                case 0:
                    System.out.println("\n┌───────────────────────────────────────────────┐");
                    System.out.println("│       SALIENDO DEL MENU DE EMPLEADOS...      │");
                    System.out.println("└───────────────────────────────────────────────┘\n");
                break;

                default:
                    System.out.println("OPCION NO VALIDA.");
            }
        }
        while (opcion != 0);
    }

    // Opción 1: Alta de empleado
    private static void altaEmpleado()
    {
        Empleado empleado = new Empleado();
        empleado.altaPersona();
    }

    // Opción 2: Listar empleados
    private static void listarEmpleados()
    {
        Empleado empleado = new Empleado();
        empleado.listarPersona();
    }

    // Opción 3: Buscar empleado por legajo
    private static void buscarEmpleado()
    {
        System.out.print("INGRESE LEGAJO DEL EMPLEADO A BUSCAR: ");
        int legajo = leerEntero();
        Empleado empleado = new Empleado();
        empleado.buscaPersona(legajo);
    }

    // Opción 4: Modificar empleado
    private static void modificarEmpleado()
    {
        Empleado empleado = new Empleado();
        empleado.modificarPersona();
    }

    // Opción 5: Baja de empleado
    private static void bajaEmpleado()
    {
        System.out.print("INGRESE LEGAJO DEL EMPLEADO A DAR DE BAJA: ");
        int legajo = leerEntero();
        Empleado empleado = (Empleado) new Empleado().buscaPersona(legajo);
        if (empleado != null)
        {
            new Empleado().bajaPersona(empleado);
        }
            else
            {
                System.out.println("NO SE ENCONTRO EMPLEADO CON ESE LEGAJO.");
            }
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero()
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(scanner.nextLine());
            }
                catch (NumberFormatException e)
                {
                    System.out.print("INGRESE UN NUMERO VALIDO: ");
                }
        }
    }
}