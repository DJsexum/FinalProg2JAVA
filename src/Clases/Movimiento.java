package Clases;

import java.time.LocalDate;

public class Movimiento
{
    private int codigo;
    private String tipo;
    private String detalle;
    private double montoDebe;
    private double montoHaber;
    private double saldo;
    private LocalDate fechaMovimiento;

    // Constructor vacío
    public Movimiento()
    {
        
    }

    // Constructor completo
    public Movimiento(int codigo, String tipo, String detalle, double montoDebe, double montoHaber, double saldo, LocalDate fechaMovimiento)
    {
        this.codigo = codigo;
        this.tipo = tipo;
        this.detalle = detalle;
        this.montoDebe = montoDebe;
        this.montoHaber = montoHaber;
        this.saldo = saldo;
        this.fechaMovimiento = fechaMovimiento;
    }

    // Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getMontoDebe() {
        return montoDebe;
    }

    public void setMontoDebe(double montoDebe) {
        this.montoDebe = montoDebe;
    }

    public double getMontoHaber() {
        return montoHaber;
    }

    public void setMontoHaber(double montoHaber) {
        this.montoHaber = montoHaber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    @Override
    public String toString()
    {
        return "CODIGO: " + codigo +
               ", TIPO: " + tipo +
               ", DETALLE: " + detalle +
               ", MONTO DEBE: " + montoDebe +
               ", MONTO HABER: " + montoHaber +
               ", SALDO: " + saldo +
               ", FECHA: " + fechaMovimiento;
    }
}