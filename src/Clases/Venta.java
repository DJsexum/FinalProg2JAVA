package Clases;

import java.time.LocalDate;
import Enumerados.FormaPago;

public class Venta
{
    private int codigo;
    private LocalDate fecha; // Esto lo hice por que soy tan piola que hice para que la fecha se guarde automaticamente cuando se crea la venta, y no tener que estar pidiendo la fecha al usuario, ademas de que es mas facil de manejar (esto lo hice hacer rato, asi que no se si termina haciendo lo que realemtne queria que haga)
    private Cliente cliente;
    private Producto[] producto; // Arreglo de 10 productos máximo
    private int[] cantidad; // Cantidad de cada producto
    private double total;
    private FormaPago pago;
    private int productosAgregados; // Esto basicamente lo hice para saber cuantos productos se han agregado a la venta, por que el arreglo de productos es de tamaño fijo (10), y no quiero estar contando cada vez que se agrega un producto jaja

    // Constructor vacio
    public Venta()
    {
        this.producto = new Producto[10];
        this.cantidad = new int[10];
        this.productosAgregados = 0;
    }

    // Constructor completo
    public Venta(int codigo, LocalDate fecha, Cliente cliente, Producto[] producto, int[] cantidad, double total, FormaPago pago)
    {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = total;
        this.pago = pago;
        this.productosAgregados = contarProductosNoNulos(producto);
    }

    // Metodo para agregar un producto pa la venta
    //agrega un producto y su cantidad a la venta, actualiza el total y controla que no se supere el máximo de 10 productos. Usa el contador productosAgregados para llevar la cuenta de los productos agregados para saber si se puede agregar otro.
    public boolean agregarProducto(Producto prod, int cantidadProducto)
    {
        if (productosAgregados >= 10)
        {
            System.out.println("NO SE PUEDEN AGREGAR MAS DE 10 PRODUCTOS PA.");
            return false;
        }
        this.producto[productosAgregados] = prod;
        this.cantidad[productosAgregados] = cantidadProducto;
        this.total += prod.getPrecio() * cantidadProducto;
        productosAgregados++;
        return true;
    // Resumidamente no tan resumidamente, cada vez que agregas un producto, lo guarda junto a su cantidad en la siguiente posición libre, suma su precio al total y avanza el contador para el próximo producto. Así, puedes manejar hasta 10 productos distintos por venta, cada uno con su cantidad.
    }

    // Getters y setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto[] getProducto() {
        return producto;
    }

    public void setProducto(Producto[] producto) {
        this.producto = producto;
    }

    public int[] getCantidad() {
        return cantidad;
    }

    public void setCantidad(int[] cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public FormaPago getPago() {
        return pago;
    }

    public void setPago(FormaPago pago) {
        this.pago = pago;
    }

    /*
    Método auxiliar para contar productos no nulos en el arreglo
    Recorre el arreglo de productos y suma 1 cada vez que encuentra un producto distinto de null.
    Sirve para saber cuántos productos reales tiene la venta y así evitar procesar posiciones vacías.
    */
    private int contarProductosNoNulos(Producto[] productos)
    { // O algo asi era la idea, no me acuerdo bien
        int count = 0;
        for (Producto p : productos)
        {
            if (p != null) count++;
        }
        return count;
    }

    @Override
    public String toString() // Este metodo lo hice para que al imprimir la venta, se muestre todo de una forma mas mejor
    {
        // El StringBuilder es una clase que permite construir cadenas de texto de manera eficiente cuando se agregan muchas partes.
        StringBuilder sb = new StringBuilder();

        // El sb.append agrega texto al final del StringBuilder.
        sb.append("VENTA #").append(codigo)
        .append(" - FECHA: ").append(fecha)
        .append("\nCLIENTE: ").append(cliente)
        .append("\nTOTAL: ").append(total)
        .append("\nFORMA DE PAGO: ").append(pago)
        .append("\nPRODUCTOS:\n");

        // Este for recorre solo los productos que realmente se agregaron a la venta (desde 0 hasta productosAgregados - 1).
        // Así, solo se muestran los productos cargados y no posiciones vacias del arreglo.
        for (int i = 0; i < productosAgregados; i++) // Muestra solo los productos agregados a la venta, con su cantidad y precio
        {
            sb.append("  - ").append(producto[i].getDetalle())
            .append(" | CANTIDAD: ").append(cantidad[i])
            .append(" | PRECIO UNITARIO: ").append(producto[i].getPrecio())
            .append("\n");
        }
        // A lo ultimo se devuelve el contenido del StringBuilder como una cadena de texto.
        // Esto supuestamente es mas eficiente que concatenar cadenas con el operador "+" sobretodo en los bucles
        return sb.toString();
    }
}