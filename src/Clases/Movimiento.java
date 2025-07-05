package Clases;

import java.time.LocalDate;

public class Movimiento
{
    private int Codigo;
    private String Tipo;
    private double MontoDebe;
    private double MontoHaber;
    private double SaldoAcumulado;
    private LocalDate FechaMovimiento;

    /*Esto de aca es un constructor para inicializar los valores de un movimiento*/
    public Movimiento(int codigo, String tipo, double montoDebe, double montoHaber, double saldoAcumulado, LocalDate fechaMovimiento)
    {
        this.Codigo = codigo;
        this.Tipo = tipo;
        this.MontoDebe = montoDebe;
        this.MontoHaber = montoHaber;
        this.SaldoAcumulado = saldoAcumulado;
        this.FechaMovimiento = fechaMovimiento;
    }

    /*Metodo para (no sé qué tiene que hacer este metodo)*/
    public Movimiento altaMovimiento()
    {
        return null;
    }

    /*Metodo para modificar un movimiento existente*/
    public void modificarMovimiento()
    {}

    /*Metodo para buscar un movimiento existente*/
    public Movimiento busquedaMovimiento()
    {
        return null;
    }

    /*Metodo que supongo que lo que hace es eliminar un movimiento o no sé*/
    public void bajaMovimiento()
    {}


    /*Metodos Getters y Setters si se necesitan*/
    public int getCodigo()
    {
        return Codigo;
    }

    public void setCodigo (int codigo)
    {
        this.Codigo = codigo;
    }

    public String getTipo ()
    {
        return Tipo;
    }

    public void setTipo (String tipo)
    {
        this.Tipo = tipo;
    }

    public double getMontoDebe ()
    {
        return MontoDebe;
    }

    public void setMontoDebe (double montoDebe)
    {
        this.MontoDebe = montoDebe;
    }

    public double getMontoHaber ()
    {
        return MontoHaber;
    }

    public void setMontoHaber (double MontoHaber)
    {
        this.MontoHaber = MontoHaber;
    }

    public double getSaldoAcumulado ()
    {
        return SaldoAcumulado;
    }

    public void setSaldoAcumulado (double saldoAcumulado)
    {
        this.SaldoAcumulado = saldoAcumulado;
    }

    public LocalDate getFechaMovimiento ()
    {
        return FechaMovimiento;
    }

    public void setFechaMovimiento (LocalDate fechaMovimiento)
    {
        this.FechaMovimiento = fechaMovimiento;
    }
}