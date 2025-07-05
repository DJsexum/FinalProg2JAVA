import Clases.Cliente;
import Enumerados.*;
import java.time.LocalDate;


public class Main
{
    public static void main(String[] args)
    {
        Sexo sexo = Sexo.getSexoDesdeConsola();
        System.out.println("Sexo seleccionado: " + sexo);
        Cliente cliente = new Cliente(12345678, true, "Juan", "Pérez", "1234567890", "Calle Falsa 123", "Ciudad", Provincia.BUENOS_AIRES, sexo, LocalDate.of(1990, 1, 1), null);

        System.out.println("El programa ha finalizado correctamente.");
    }
}