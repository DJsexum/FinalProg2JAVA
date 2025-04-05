import java.time.LocalDate;
import Enumerados.Sexo;
import Enumerados.Provincia;

public class Cliente extends Persona
{
    private CuentaCorriente CtaCte;

    // Constructor de la clase Cliente que inicializa los datos heredados y la cuenta corriente
    public Cliente(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento); // Llamada al constructor de Persona
        this.CtaCte = ctaCte;
    }

    // Metodo para ver los detalles de la cuenta corriente
    public void VerCtaCte()
    {
        if (CtaCte != null)
        {
            System.out.println("Detalles de la Cuenta Corriente:");
            System.out.println("Saldo actual: " + CtaCte.ObtenerSaldo());
            CtaCte.VerMovimientos(); // Mostramos todos los movimientos de la cuenta
        }
            else
            {
                System.out.println("Este cliente no tiene cuenta corriente asociada.");
            }
    }

    // Metodo para depositar guita en la cuenta corriente
    public void DepositarEnCtaCte(double monto, String descripcion)
    {
        if (CtaCte != null)
        {
            Movimiento movimiento = new Movimiento(1, "Haber", 0.0, monto, CtaCte.ObtenerSaldo() + monto, LocalDate.now());
            CtaCte.AgregarMovimiento(movimiento);
            System.out.println("Deposito realizado: " + monto + " (" + descripcion + ")");
        }
            else
            {
                System.out.println("Este cliente no tiene cuenta corriente asociada.");
            }
    }

    // Metodo para retirar platita de la cuenta corriente
    public void RetirarDeCtaCte(double monto, String descripcion)
    {
        if (CtaCte != null)
        {
            if (CtaCte.ObtenerSaldo() >= monto)
            {
                Movimiento movimiento = new Movimiento(2, "Debe", monto, 0.0, CtaCte.ObtenerSaldo() - monto, LocalDate.now());
                CtaCte.AgregarMovimiento(movimiento);
                System.out.println("Retiro realizado: " + monto + " (" + descripcion + ")");
            }
                else
                {
                    System.out.println("Saldo insuficiente para realizar el retiro.");
                }
        }
            else
            {
                System.out.println("Este cliente no tiene cuenta corriente asociada.");
            }
    }

    // Metodo para ver el resumen completo del cliente
    public void VerResumenCliente()
    {
        super.DatosPersona(); /*Mostramos los datos generales del cliente*/
        VerCtaCte(); /*Muestra los detalles de la cuenta corriente*/
    }
}