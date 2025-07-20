package Enumerados;

import java.util.Scanner;

public enum Categoria
{
    INDUMENTARIA("INDUMENTARIA"),
    CALZADO("CALZADO"),
    ACCESORIO("ACCESORIO");

    private final String detalleCategoria;

    // Se crea el constructor para inicializar el atributo
    Categoria(String detalleCategoria)
    {
        this.detalleCategoria = detalleCategoria;
    }

    public String getCategoria()
    {
        return this.detalleCategoria;
    }

    // Se crea el metodo estático para la selección del usuario
    public static Categoria seleccionarCategoria()
    {
        // Usar Scanner sin try-with-resources para evitar cerrar System.in (osea que no se cierre el scanner)
        Scanner scanner = new Scanner(System.in);
        while (true) // Bucle para reintentar si la opción es inválida
        {
            System.out.println("SELECCIONE LA CATEGORIA:");
            System.out.println("1. INDUMENTARIA");
            System.out.println("2. CALZADO");
            System.out.println("3. ACCESORIO");
            System.out.print("OPCION: ");

            if (scanner.hasNextInt())
            {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Se quita el salto de línea que quede

                switch (opcion)
                {
                    case 1:
                        return INDUMENTARIA;
                    case 2:
                        return CALZADO;
                    case 3:
                        return ACCESORIO;
                    default:
                        System.out.println("\nOPCION INVALIDA. INTENTE DE NUEVO.\n");
                }
            }
                else
                {
                    System.out.println("\nENTRADA NO VALIDA. POR FAVOR, INGRESE UN NUMERO.\n");
                    scanner.nextLine(); // Se limpia el scanner para evitar un bucle infinito
                }
        }
    }
}