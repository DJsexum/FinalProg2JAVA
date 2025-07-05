package Clases;

import Enumerados.Provincia;
import Enumerados.Sexo;
import java.time.LocalDate;

public class Cliente extends Persona
{
    private CuentaCorriente CtaCte;

    // Constructor de la clase Clases.Cliente que inicializa los datos heredados y la cuenta corriente
    public Cliente(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento); // Llamada al constructor de Clases.Persona
        this.CtaCte = ctaCte;
    }

    // Metodo para ver los detalles de la cuenta corriente
    public void VerCtaCte()
    {
        if (CtaCte != null)
        {
            System.out.println("Detalles de la Cuenta Corriente:");
            System.out.println("Saldo actual: " + CtaCte.getSaldo());
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
            Movimiento movimiento = new Movimiento(1, "Haber", 0.0, monto, CtaCte.getSaldo() + monto, LocalDate.now());
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
            if (CtaCte.getSaldo() >= monto)
            {
                Movimiento movimiento = new Movimiento(2, "Debe", monto, 0.0, CtaCte.getSaldo() - monto, LocalDate.now());
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

    @Override
    public Sexo getSexo()
    {
        return super.getSexo(); // Llamada al metodo de la clase Persona
    }
}