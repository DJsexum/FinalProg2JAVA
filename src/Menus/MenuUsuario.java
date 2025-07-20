package Menus;

import Clases.*;
import java.util.Scanner;

public class MenuUsuario
{
    private static final Scanner scanner = new Scanner(System.in);

    /*
    Muestra el menu de usuarios y ejecuta la opcion seleccionada.
    */
    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                MENU DE USUARIOS               │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│      [1] ALTA DE USUARIO                      │");
            System.out.println("│      [2] LISTAR USUARIOS                      │");
            System.out.println("│      [3] BUSCAR USUARIO POR NOMBRE USUARIO    │");
            System.out.println("│      [4] MODIFICAR USUARIO                    │");
            System.out.println("│      [5] BAJA DE USUARIO                      │");
            System.out.println("│      [0] VOLVER AL MENU PRINCIPAL             │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");

            // Validacion para que la opcion sea un numero
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
                    // Alta de usuario
                    new Usuario().altaPersona();
                break;

                case 2:
                    // Listar todos los usuarios
                    new Usuario().listarPersona();
                break;

                case 3:
                    // Buscar usuario por nombre de usuario
                    System.out.print("INGRESE NOMBRE DE USUARIO: ");
                    String nombreUsuario = scanner.nextLine();
                    Usuario usuarioBuscado = Usuario.buscarUsuario(nombreUsuario);
                    if (usuarioBuscado != null)
                    {
                        usuarioBuscado.mostrarDatos();
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO USUARIO CON ESE NOMBRE.");
                        }
                break;

                case 4:
                    // Modificar usuario
                    System.out.print("INGRESE NOMBRE DE USUARIO A MODIFICAR: ");
                    String nombreMod = scanner.nextLine();
                    Usuario usuarioMod = Usuario.buscarUsuario(nombreMod);
                    if (usuarioMod != null)
                    {
                        usuarioMod.modificarPersona();
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO USUARIO CON ESE NOMBRE.");
                        }
                break;

                case 5:
                    // Baja de usuario
                    System.out.print("INGRESE NOMBRE DE USUARIO A DAR DE BAJA: ");
                    String nombreBaja = scanner.nextLine();
                    Usuario usuarioBaja = Usuario.buscarUsuario(nombreBaja);
                    if (usuarioBaja != null)
                    {
                        usuarioBaja.bajaPersona(usuarioBaja);
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO USUARIO CON ESE NOMBRE.");
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