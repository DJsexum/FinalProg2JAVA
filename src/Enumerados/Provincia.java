package Enumerados;

import java.util.Scanner;
import java.util.InputMismatchException;
// Lo de arriba importa la clase que dice, sirve para manejar errores cuando el usuario ingresa un dato de un tipo incorrecto, por ejemplo un String en vez de un int 
public enum Provincia
{
    BUENOS_AIRES (1, "BUENOS AIRES"),
    CATAMARCA (2, "CATAMARCA"),
    CHACO (3, "CHACO"),
    CHUBUT (4, "CHUBUT"),
    CORDOBA (5, "CORDOBA"),
    CORRIENTES (6, "CORRIENTES"),
    ENTRE_RIOS (7, "ENTRE RIOS"),
    FORMOSA (8, "FORMOSA"),
    JUJUY (9, "JUJUY"),
    LA_PAMPA (10, "LA PAMPA"),
    LA_RIOJA (11, "LA RIOJA"),
    MENDOZA (12, "MENDOZA"),
    MISIONES (13, "MISIONES"),
    NEUQUEN (14, "NEUQUEN"),
    RIO_NEGRO (15, "RIO NEGRO"),
    SALTA (16, "SALTA"),
    SAN_JUAN (17, "SAN JUAN"),
    SAN_LUIS (18, "SAN LUIS"),
    SANTA_CRUZ (19, "SANTA CRUZ"),
    SANTA_FE (20, "SANTA FE"),
    SANTIAGO_DEL_ESTERO (21, "SANTIAGO DEL ESTERO"),
    TIERRA_DEL_FUEGO (22, "TIERRA DEL FUEGO"),
    TUCUMAN (23, "TUCUMAN");

    private final int codigo;
    private final String nombre;

    Provincia(int codigo, String nombre)
    {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    private static final Scanner scanner = new Scanner(System.in);

    // Metodo para seleccionar una provincia por codigo
    public static Provincia seleccionarProvincia()
    {
        while (true)
        {
            System.out.println("SELECCIONE UNA PROVINCIA POR CODIGO:");
            for (Provincia p : Provincia.values())
            {
                System.out.println(p.getCodigo() + " - " + p.getNombre());
            }

            System.out.print("INGRESE EL CODIGO DE LA PROVINCIA: ");
            try
            {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                Provincia provinciaSeleccionada = getProvinciaPorCodigo(opcion);

                if (provinciaSeleccionada != null)
                {
                    return provinciaSeleccionada;
                }
                    else
                    {
                        System.out.println("CODIGO INVALIDO. LA PROVINCIA CON ESE CODIGO NO EXISTE. INTENTE DE NUEVO.");
                    }
            }
                catch (InputMismatchException e)
                {
                    System.out.println("ENTRADA INVALIDA. POR FAVOR, INGRESE UN NUMERO.");
                    scanner.nextLine();
                }
        }
    }

    public static Provincia getProvinciaPorCodigo(int codigo)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.getCodigo() == codigo)
            {
                return provincia;
            }
        }
        return null;
    }

    // Metodo para obtener una provincia por su nombre (esta si que no se si funcionaba antes, pero la agregue por si las moscas)
    public static Provincia getProvinciaPorNombre(String nombre)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.getNombre().equalsIgnoreCase(nombre))
            {
                return provincia;
            }
        }
        return null;
    }
}