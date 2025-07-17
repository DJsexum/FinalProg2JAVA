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
            System.out.println(quitarAcentos("===== MENU DE CLIENTES =====").toUpperCase());
            System.out.println(quitarAcentos("1. ALTA DE CLIENTE").toUpperCase());
            System.out.println(quitarAcentos("2. LISTAR CLIENTES").toUpperCase());
            System.out.println(quitarAcentos("3. BUSCAR CLIENTE POR DNI").toUpperCase());
            System.out.println(quitarAcentos("4. MODIFICAR CLIENTE").toUpperCase());
            System.out.println(quitarAcentos("5. BAJA DE CLIENTE").toUpperCase());
            System.out.println(quitarAcentos("0. VOLVER AL MENU PRINCIPAL").toUpperCase());
            System.out.print(quitarAcentos("SELECCIONE UNA OPCION: ").toUpperCase());

            // Manejo seguro de entrada de opción
            while (!scanner.hasNextInt()) 
            {
                System.out.print(quitarAcentos("INGRESE UN NUMERO VALIDO: ").toUpperCase());
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
                    System.out.print(quitarAcentos("INGRESE DNI: ").toUpperCase());
                    int dni = leerEntero(scanner);
                    new Cliente().buscaPersona(dni);
                break;

                case 4:
                    System.out.print(quitarAcentos("INGRESE DNI DEL CLIENTE A MODIFICAR: ").toUpperCase());
                    int dniMod = leerEntero(scanner);
                    Cliente clienteMod = (Cliente) new Cliente().buscaPersona(dniMod);
                    if (clienteMod != null)
                    {
                        clienteMod.modificarPersona();
                    }
                        else
                        {
                            System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI.").toUpperCase());
                        }
                break;

                case 5:
                    System.out.print(quitarAcentos("INGRESE DNI DEL CLIENTE A DAR DE BAJA: ").toUpperCase());
                    int dniBaja = leerEntero(scanner);
                    Cliente clienteBaja = (Cliente) new Cliente().buscaPersona(dniBaja);
                    if (clienteBaja != null)
                    {
                        clienteBaja.bajaPersona(clienteBaja);
                    }
                        else
                        {
                            System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI.").toUpperCase());
                        }
                break;

                case 0:
                    System.out.println(quitarAcentos("VOLVIENDO AL MENU PRINCIPAL...").toUpperCase());
                break;

                default:
                    System.out.println(quitarAcentos("OPCION INVALIDA. INTENTE DE NUEVO.").toUpperCase());
            }
        }
        while (opcion != 0);
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero(Scanner scanner)
    {
        while (!scanner.hasNextInt()) 
        {
            System.out.print(quitarAcentos("INGRESE UN NUMERO VALIDO: ").toUpperCase());
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
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