import Enumerados.FormaPago;

import java.util.ArrayList;

public class Venta
{
    private int Codigo;
    private Cliente Cliente;
    private int Cantidad;
    private Producto[] Productos;
    private double Total;
    private Enumerados.FormaPago FormaPago;

    public Venta(int codigo, Cliente cliente, int cantidad, Producto[] productos, double total, FormaPago formaPago)
    {
        this.Codigo = codigo;
        this.Cliente = cliente;
        this.Cantidad = cantidad;
        this.Productos = productos;
        this.Total = total;
        this.FormaPago = formaPago;
    }

    public Venta altaVenta() { /* Implementación */
        return null;
    }

    public void bajaVenta() { /* Implementación */ }

    public void modificarVenta() { /* Implementación */ }

    public ArrayList<Venta> listarVentas()
    {
        return null;
    }

    public Venta busquedaVenta()
    {
        return null;
    }

    public void verDetalleVenta() { /* Implementación */ }
}