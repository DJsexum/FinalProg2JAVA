package Clases;

import Enumerados.FormaPago;
import java.util.ArrayList;
import Clases.Excepciones.ProductoInvalidoException;

public class Venta
{
    private int codigo;
    private Cliente cliente;
    private int cantidad;
    private Producto [] producto = new Producto[10];
    private double total;
    private Enumerados.FormaPago pago;

    public Venta(int codigo, Cliente cliente, int cantidad, Producto[] productos, double total, FormaPago formaPago)
    {
        this.codigo = codigo;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.producto = productos;
        this.total = total;
        this.pago = formaPago;
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

    public void verDetalleVenta(Venta venta)
    {
        System.out.println("Código de Venta: " + venta.codigo);
        System.out.println("Cliente: " + venta.cliente);
        System.out.println("Cantidad: " + venta.cantidad);
        System.out.println("Productos:");

        for (Producto p : venta.producto)
        {
            try
            {
               p.verDetalleProducto(p); 
            }
                catch (ProductoInvalidoException ProdInv)
                {
                    System.err.println("Error: " + ProdInv.getMessage());
                }
        }
        System.out.println("Total: " + venta.total);
        System.out.println("Forma de Pago: " + venta.pago);
    }
}