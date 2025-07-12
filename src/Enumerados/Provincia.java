package Enumerados;

import java.util.Scanner;
import java.util.InputMismatchException; // Importado para manejar entradas no numéricas de forma robusta

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

    // Método para seleccionar una provincia por código, replicando el patrón de 'Sexo'
    public static Provincia seleccionarProvincia()
    {
        // Se recomienda que el Scanner se gestione externamente y se pase como parámetro
        // o que haya una única instancia global para System.in para evitar problemas.
        // Por la replicación del patrón, se crea aquí localmente.
        Scanner scanner = new Scanner(System.in);

        while (true) // Bucle infinito hasta que se devuelva una provincia válida
        {
            System.out.println("Seleccione una provincia por código:");
            for (Provincia p : Provincia.values())
            {
                System.out.println(p.getCodigo() + "-" + p.getNombre());
            }

            System.out.println("Ingrese el código de la provincia: "); // Solicitud de entrada
            try
            {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                Provincia provinciaSeleccionada = getProvinciaPorCodigo(opcion); // Usa el metodo de búsqueda por codigo

                if (provinciaSeleccionada != null)
                {
                    return provinciaSeleccionada; // Retorna la provincia valida y sale del bucle
                }
                    else
                    {
                        System.out.println("Código inválido. La provincia con ese código no existe. Intente de nuevo.");
                    }
            }
                catch (InputMismatchException e) // Captura si la entrada no es un entero
                {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    scanner.nextLine(); // Consume la entrada inválida para evitar un bucle infinito
                }
        }
    }

    // Método auxiliar para obtener una provincia por su código explícito
    public static Provincia getProvinciaPorCodigo(int codigo)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.getCodigo() == codigo) // Compara con el codigo explicito del enum
            {
                return provincia;
            }
        }
        return null; // Si no encuentra ninguna provincia con ese codigo
    }

    // Este metodo ya existe y es útil, lo mantengo.
    public static Provincia getProvinciaPorNombre(String nombre)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.getNombre().equalsIgnoreCase(nombre)) // Compara con el nombre de la provincia
            {
                return provincia;
            }
        }
        return null;
    }
}