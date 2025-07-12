package Enumerados;
import java.util.Scanner;

public enum Sexo
{
	
	MASCULINO("Masculino."),
	FEMENINO("Femenino."),
	OTRO("Otro.");
	
	private final String detalleSexo;
	
	Sexo (String detalleSexo)
    {
		this.detalleSexo = detalleSexo;
	}
	
	public String getSexo ()
    {
		return this.detalleSexo;
	}
	
    public static Sexo seleccionarSexo()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Seleccione el sexo:");
            System.out.println("1. Masculino");
            System.out.println("2. Femenino");
            System.out.println("3. Otro");

            int opcion = scanner.nextInt();

            switch (opcion)
            {
                case 1:
                    return MASCULINO;
                case 2:
                    return FEMENINO;
                case 3:
                    return OTRO;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}