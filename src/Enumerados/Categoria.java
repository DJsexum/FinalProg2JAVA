package Enumerados;

import Clases.*;
import java.util.Scanner;

public enum Categoria
{
    INDUMENTARIA("Indumentaria"),
    CALZADO("Calzado"),
    ACCESORIO("Accesorio");

    private final String detalleCategoria;

    // 3. Se crea el constructor para inicializar el atributo
    Categoria(String detalleCategoria)
    {
        this.detalleCategoria = detalleCategoria;
    }

    // 4. El método 'get' ahora devuelve el nuevo atributo
    public String getCategoria()
    {
        return this.detalleCategoria;
    }

    // 5. Se crea el método estático para la selección del usuario
    public static Categoria seleccionarCategoria()
    {
        // Se usa try-with-resources para que el Scanner se cierre solo y de forma segura.
        // Se crea un Scanner para leer la entrada del usuario
        try (Scanner scanner = new Scanner(System.in))
        {
            while (true) // Bucle para reintentar si la opción es inválida
            {
                System.out.println("Seleccione la categoria:");
                System.out.println("1. Indumentaria");
                System.out.println("2. Calzado");
                System.out.println("3. Accesorio");
                System.out.print("Opción: ");

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
                            System.out.println("\nOpcion invalida. Intente de nuevo.\n");
                    }
                }
                    else
                    {
                        System.out.println("\nEntrada no valida. Por favor, ingrese un numero.\n");
                        scanner.nextLine(); // Se limpia el scanner para evitar un bucle infinito
                    }
            }
        }
    }
}