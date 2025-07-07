package Clases;

import Enumerados.Provincia;
import Enumerados.Sexo;
import java.time.LocalDate;
import java.util.Scanner;

public class Cliente extends Persona
{
    private CuentaCorriente CtaCte;
    private Scanner scanner = new Scanner(System.in);

    // Constructor de la clase Clases.Cliente que inicializa los datos heredados y la cuenta corriente
    public Cliente(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento); // Llamada al constructor de Clases.Persona
        this.CtaCte = ctaCte;
    }

    // Constructor de Cliente a rellenar
    public Cliente()
    {
        super(0, false, "", "", "", "", "", null, null, null); // Llamada al constructor de Clases.Persona
        this.CtaCte = null; // Inicializamos la cuenta corriente como nula
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

//     Metodo para ver el resumen completo del cliente
    public void VerResumenCliente()
    {
        super.toString(); /*Mostramos los datos generales del cliente*/
        VerCtaCte(); /*Muestra los detalles de la cuenta corriente*/
    }

    @Override
    public Sexo getSexo()
    {
        return super.getSexo(); // Llamada al metodo de la clase Persona
    }

    // Setters
    public void setCtaCte(CuentaCorriente ctaCte)
    {
        this.CtaCte = ctaCte;
    }

    @Override
    public void setActivo(boolean activo)
    {
        super.setActivo(activo); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setNombres(String nombres)
    {
        super.setNombres(nombres); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setApellidos(String apellidos)
    {
        super.setApellidos(apellidos); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setTelefono(String telefono)
    {
        super.setTelefono(telefono); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setDireccion(String direccion)
    {
        super.setDireccion(direccion); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setLocalidad(String localidad)
    {
        super.setLocalidad(localidad); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setProvincia()
    {
        super.setProvincia(); // Llamada al metodo de la clase Persona
    }

    @Override
    public void setSexo()
    {
        super.setSexo(); // Llamada al metodo de la clase Persona

    }

    @Override
    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        try
        {
            if (fechaNacimiento.isAfter(LocalDate.now()))
            {
                throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
            }
            super.setFechaNacimiento(fechaNacimiento); // Llamada al metodo de la clase Persona
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}