package Clases;

import Enumerados.*;
import Principal.*;
import Archivos.ArchivosProducto;
import java.util.ArrayList;
import java.util.Scanner;

/*
La clase Producto implementa Comparable<Producto> para poder comparar y ordenar productos.
Esto permite, por ejemplo, ordenar listas de productos por su código usando Collections.sort().
El método compareTo(Producto otro) define el criterio de comparación (por código en este caso).
*/

public class Producto implements Comparable<Producto>
{
    // Atributos del producto
    private int codigo;
    private String detalle;
    private String talle;
    private double precio;
    private String marca;
    private String material;
    private Categoria categoria;
    private int stock;

    // Scanner único y estático para toda la clase (evita advertencias y fugas de recursos)
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor vacío: útil para carga por consola o instancias sin datos iniciales
    public Producto() {}

    // Constructor completo: para crear un producto con todos los datos
    public Producto(int codigo, String detalle, String talle, double precio, String marca, String material, Categoria categoria, int stock)
    {
        if (codigo <= 0)
            throw new Excepciones.ProductoInvalidoException("EL CÓDIGO DEL PRODUCTO DEBE SER UN NÚMERO POSITIVO.");
        if (detalle == null || detalle.trim().isEmpty())
            throw new Excepciones.ProductoInvalidoException("EL DETALLE DEL PRODUCTO NO PUEDE ESTAR VACÍO.");
        if (precio < 0)
            throw new Excepciones.ProductoInvalidoException("EL PRECIO DEL PRODUCTO DEBE SER UN NÚMERO POSITIVO.");
        if (stock < 0)
            throw new Excepciones.ProductoInvalidoException("EL STOCK DEL PRODUCTO NO PUEDE SER NEGATIVO.");

        this.codigo = codigo;
        this.detalle = detalle;
        this.talle = talle;
        this.precio = precio;
        this.marca = marca;
        this.material = material;
        this.categoria = categoria;
        this.stock = stock;
    }

    // Getters y setters para cada atributo
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Alta de producto: pide datos por consola y guarda en archivo
    public void altaProducto()
    {
        System.out.print("CÓDIGO: ");
        this.codigo = scanner.nextInt(); scanner.nextLine();
        System.out.print("DETALLE: ");
        this.detalle = scanner.nextLine();
        System.out.print("TALLE: ");
        this.talle = scanner.nextLine();
        System.out.print("PRECIO: ");
        this.precio = scanner.nextDouble(); scanner.nextLine();
        System.out.print("MARCA: ");
        this.marca = scanner.nextLine();
        System.out.print("MATERIAL: ");
        this.material = scanner.nextLine();
        System.out.print("CATEGORÍA (COMO TEXTO): ");
        this.categoria = Categoria.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("STOCK: ");
        this.stock = scanner.nextInt(); scanner.nextLine();

        try 
        {
            ArchivosProducto.guardarProducto(this); // Guarda el producto en el archivo
            System.out.println("PRODUCTO GUARDADO CORRECTAMENTE.");
        } 
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL GUARDAR EL PRODUCTO: " + e.getMessage().toUpperCase());
            }
    }

    // Baja de producto: elimina del archivo por código
    public void bajaProducto()
    {
        try 
        {
            boolean eliminado = ArchivosProducto.eliminarProducto(this.codigo);
            if (eliminado)
                System.out.println("PRODUCTO ELIMINADO CORRECTAMENTE.");
                else
                    System.out.println("NO SE ENCONTRÓ EL PRODUCTO.");
        } 
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL ELIMINAR EL PRODUCTO: " + e.getMessage().toUpperCase());
            }
    }

    // Modificar producto: pide nuevos datos y actualiza en archivo
    public void modificarProducto()
    {
        System.out.print("NUEVO DETALLE: ");
        this.detalle = scanner.nextLine();
        System.out.print("NUEVO TALLE: ");
        this.talle = scanner.nextLine();
        System.out.print("NUEVO PRECIO: ");
        this.precio = scanner.nextDouble(); scanner.nextLine();
        System.out.print("NUEVA MARCA: ");
        this.marca = scanner.nextLine();
        System.out.print("NUEVO MATERIAL: ");
        this.material = scanner.nextLine();
        System.out.print("NUEVA CATEGORÍA (COMO TEXTO): ");
        this.categoria = Categoria.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("NUEVO STOCK: ");
        this.stock = scanner.nextInt(); scanner.nextLine();

        try 
        {
            boolean modificado = ArchivosProducto.modificarProducto(this);
            if (modificado)
                System.out.println("PRODUCTO MODIFICADO CORRECTAMENTE.");
                else
                    System.out.println("NO SE PUDO MODIFICAR EL PRODUCTO.");
        } 
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL MODIFICAR EL PRODUCTO: " + e.getMessage().toUpperCase());
            }
    }

    // Listar todos los productos del archivo
    public static void listarProductos()
    {
        try 
        {
            ArrayList<Producto> lista = ArchivosProducto.leerProductos();
            if (lista.isEmpty())
                System.out.println("NO HAY PRODUCTOS REGISTRADOS.");
                else
                    for (Producto p : lista)
                        System.out.println(p);
        }
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL LISTAR PRODUCTOS: " + e.getMessage().toUpperCase());
            }
    }

    // Buscar producto por código
    public static Producto buscarProducto(int codigo)
    {
        try 
        {
            Producto encontrado = ArchivosProducto.buscarPorCodigo(codigo);
            if (encontrado != null)
                System.out.println("PRODUCTO ENCONTRADO:\n" + encontrado);
                else
                    System.out.println("NO SE ENCONTRÓ PRODUCTO CON ESE CÓDIGO.");
                    return encontrado;
        }
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL BUSCAR PRODUCTO: " + e.getMessage().toUpperCase());
                return null;
            }
    }

    // Ver detalle de un producto (muestra todos los datos)
    public void verDetalleProducto()
    {
        System.out.println("CÓDIGO: " + this.codigo);
        System.out.println("DETALLE: " + this.detalle);
        System.out.println("TALLE: " + this.talle);
        System.out.println("PRECIO: " + this.precio);
        System.out.println("MARCA: " + this.marca);
        System.out.println("MATERIAL: " + this.material);
        System.out.println("CATEGORÍA: " + this.categoria);
        System.out.println("STOCK: " + this.stock);
    }

    // Actualizar stock del producto
    public void actualizarStock(int cantidad)
    {
        if (this.stock + cantidad < 0)
            throw new Excepciones.ProductoInvalidoException("EL STOCK NO PUEDE SER NEGATIVO.");
        this.stock += cantidad;
    }

    // Representación en texto del producto
    @Override
    public String toString()
    {
        return "CLASES.PRODUCTO\n" +
                "{\n" +
                "CÓDIGO = " + codigo + ",\n" +
                "DETALLE = '" + detalle + "',\n" +
                "TALLE = '" + talle + "',\n" +
                "PRECIO = " + precio + ",\n" +
                "MARCA = '" + marca + "',\n" +
                "MATERIAL ='" + material + "',\n" +
                "CATEGORÍA =" + categoria + ",\n" +
                "STOCK=" + stock + "\n" +
                "}";
    }

    // Permite ordenar productos por código
    @Override
    public int compareTo(Producto otroProducto)
    {
        return Integer.compare(this.codigo, otroProducto.codigo);
    }
}