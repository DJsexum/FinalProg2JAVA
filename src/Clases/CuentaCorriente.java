package Clases;

import java.util.ArrayList;

public class CuentaCorriente
{
    private ArrayList<Movimiento> movimientos;
    private double saldo;

    // Constructor que inicializa la lista de movimientos y el saldo
    public CuentaCorriente()
    {
        this.movimientos = new ArrayList<>();
        this.saldo = 0.0;
    }

    public CuentaCorriente(ArrayList<Movimiento> movimientos)
    {
        this.movimientos = movimientos;
        actualizarSaldo();
    }

    public ArrayList<Movimiento> getMovimientos()
    {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos)
    {
        this.movimientos = movimientos;
        actualizarSaldo();
    }

    // Metodo para agregar un movimiento a la cuenta corriente
    public void agregarMovimiento(Movimiento movimiento)
    {
        this.movimientos.add(movimiento);
        actualizarSaldo();
    }

    // Metodo para actualizar el saldo de la cuenta corriente
    public void actualizarSaldo()
    {
        saldo = 0.0;
        for (Movimiento movimiento : movimientos)
        {
            if (movimiento.getTipo().equals("DEBE"))
            {
                saldo -= movimiento.getMontoDebe();
            }
                else
                {
                    saldo += movimiento.getMontoHaber();
                }
        }
    }

    // Metodo para obtener el saldo actual de la cuenta corriente
    public double getSaldo()
    {
        return saldo;
    }

    // Metodo para mostrar todos los movimientos de la cuenta corriente
    public void verMovimientos()
    {
        for (Movimiento movimiento : movimientos)
        {
            System.out.println("CODIGO: " + movimiento.getCodigo() +
                               ", TIPO: " + movimiento.getTipo() +
                               ", FECHA: " + movimiento.getFechaMovimiento() +
                               ", MONTO: " + (movimiento.getMontoDebe() - movimiento.getMontoHaber())
                               );
        }
        System.out.println("SALDO ACTUAL: " + saldo);
    }
}