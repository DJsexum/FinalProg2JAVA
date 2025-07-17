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
            System.out.println(quitarAcentos("\n=== MENU DE EMPLEADOS ===").toUpperCase());
            System.out.println(quitarAcentos("1. ALTA DE EMPLEADO").toUpperCase());
            System.out.println(quitarAcentos("2. LISTAR EMPLEADOS").toUpperCase());
            System.out.println(quitarAcentos("3. BUSCAR EMPLEADO POR LEGAJO").toUpperCase());
            System.out.println(quitarAcentos("4. MODIFICAR EMPLEADO").toUpperCase());
            System.out.println(quitarAcentos("5. BAJA DE EMPLEADO").toUpperCase());
            System.out.println(quitarAcentos("0. SALIR").toUpperCase());
            System.out.print(quitarAcentos("SELECCIONE UNA OPCION: ").toUpperCase());
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
                    System.out.println(quitarAcentos("SALIENDO DEL MENU DE EMPLEADOS...").toUpperCase());
                break;

                default:
                    System.out.println(quitarAcentos("OPCION NO VALIDA.").toUpperCase());
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
        System.out.print(quitarAcentos("INGRESE LEGAJO DEL EMPLEADO A BUSCAR: ").toUpperCase());
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
        System.out.print(quitarAcentos("INGRESE LEGAJO DEL EMPLEADO A DAR DE BAJA: ").toUpperCase());
        int legajo = leerEntero();
        Empleado empleado = (Empleado) new Empleado().buscaPersona(legajo);
        if (empleado != null)
        {
            new Empleado().bajaPersona(empleado);
        }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO EMPLEADO CON ESE LEGAJO.").toUpperCase());
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
                    System.out.print(quitarAcentos("INGRESE UN NUMERO VALIDO: ").toUpperCase());
                }
        }
    }

    // Método utilitario para quitar solo acentos (no modifica ñ/Ñ)
    private static String quitarAcentos(String texto)
    {
        if (texto == null) return null;
        return texto
            .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
    }
}