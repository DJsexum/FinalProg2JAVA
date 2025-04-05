import java.util.ArrayList;

public class CuentaCorriente
{
    private ArrayList<Movimiento> Movimientos;
    private double Saldo;
    private ArrayList<Movimiento> movimientos = new ArrayList<>();

    // Constructor que inicializa la lista de movimientos y el saldo
    public CuentaCorriente()
    {
        this.Movimientos = new ArrayList<>();
        this.Saldo = 0.0;
    }

    public CuentaCorriente(ArrayList<Movimiento> movimientos)
    {
        this.movimientos = movimientos;
    }

    public ArrayList<Movimiento> getMovimientos()
    {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos)
    {
        this.movimientos = movimientos;
    }

    // Metodo para agregar un movimiento a la cuenta corriente
    public void AgregarMovimiento(Movimiento movimiento)
    {
        this.Movimientos.add(movimiento); // Agregamos el movimiento a la lista
        ActualizarSaldo(); // Actualiza el saldo despues de agregar el movimiento
    }

    // Metodo para actualizar el saldo de la cuenta corriente
    public void ActualizarSaldo()
    {
        Saldo = 0.0; // Reseteamos el saldo antes de actualizarlo
        for (Movimiento movimiento : Movimientos)
        {
            // Sumamos o restamos el monto del movimiento dependiendo de si es un debito o credito
            if (movimiento.ObtenerTipo().equals("Debe"))
            {
                Saldo = Saldo - movimiento.ObtenerMontoDebe();
            }
                else
                {
                    Saldo = Saldo + movimiento.ObtenerMontoHaber();
                }
        }
    }

    // Metodo para obtener el saldo actual de la cuenta corriente
    public double ObtenerSaldo()
    {
        return Saldo;
    }

    //Metodo para mostrar todos los movimientos de la cuenta corriente
    public void VerMovimientos()
    {
        for (Movimiento movimiento : Movimientos)
        {
            System.out.println("Código: " + movimiento.ObtenerCodigo() + ", Tipo: " + movimiento.ObtenerTipo() + ", Fecha: " + movimiento.ObtenerFechaMovimiento() + ", Monto: " + (movimiento.ObtenerMontoDebe() - movimiento.ObtenerMontoHaber()));
        }
    }
}