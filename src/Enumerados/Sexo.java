package Enumerados;
import java.util.Scanner;

public enum Sexo
{
    MASCULINO("MASCULINO."),
    FEMENINO("FEMENINO."),
    OTRO("OTRO.");

    private final String detalleSexo;

    Sexo(String detalleSexo) // Constructor que asigna el detalle del sexo (osea que sos man)
    {
        this.detalleSexo = detalleSexo;
    }

    public String obtenerSexo()
    {
        return this.detalleSexo;
    }

    // Scanner statico para la clase (lo hago asi por que no me gusta que me muestre un mensajito que dice "Scanner is never closed" y bueno, eso)
    private static final Scanner scanner = new Scanner(System.in);

    public static Sexo seleccionarSexo()
    {
        while (true)
        {
            System.out.println("SELECCIONE EL SEXO:");
            System.out.println("1. MASCULINO");
            System.out.println("2. FEMENINO");
            System.out.println("3. OTRO");

            int opcion;
            while (!scanner.hasNextInt())
            {
                System.out.print("INGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                    return MASCULINO;
                case 2:
                    return FEMENINO;
                case 3:
                    return OTRO;
                default:
                    System.out.println("OPCION INVALIDA. INTENTE DE NUEVO.");
            }
        }
    }
}