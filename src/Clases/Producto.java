package Clases;

import Enumerados.Categoria;
import java.util.ArrayList;

public class Producto implements Comparable<Producto>
{
    private int codigo;
    private String detalle;
    private String talle;
    private double precio;
    private String marca;
    private String material;
    private Categoria categoria;
    private int stock;

    public Producto(int codigo, String detalle, String talle, double precio, String marca, String material, Categoria categoria, int stock)
    {
        try
        {
            if (codigo <= 0)
            {
                throw new Excepciones.ProductoInvalidoException("El código del producto debe ser un número positivo.");
            }
            if (detalle == null || detalle.trim().isEmpty())
            {
                throw new Excepciones.ProductoInvalidoException("El detalle del producto no puede estar vacío.");
            }
            if (precio < 0) //A ver, podés poner que algo vale cero pesos?, si, si podes. Es coherente? No, pero después el que se funde sos vos por regalar las cosas. Igual esto está bien porque dejo que seas libre y pongas el precio que vos quieras
            {
                throw new Excepciones.ProductoInvalidoException("El precio del producto debe ser un número positivo.");
            }
            if (stock < 0)
            {
                throw new Excepciones.ProductoInvalidoException("El stock del producto no puede ser negativo.");
            }

            this.codigo = codigo;
            this.detalle = detalle;
            this.talle = talle;
            this.precio = precio;
            this.marca = marca;
            this.material = material;
            this.categoria = categoria;
            this.stock = stock;
        } catch (Excepciones.ProductoInvalidoException errorDeProductoInvalido)
        {
            System.out.println(errorDeProductoInvalido);
        }
    }

    public int getCodigo()
    {
        return codigo;
    }

    public String getDetalle()
    {
        return detalle;
    }

    public String getTalle()
    {
        return talle;
    }

    public double getPrecio()
    {
        return precio;
    }

    public String getMarca()
    {
        return marca;
    }

    public String getMaterial()
    {
        return material;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public int getStock()
    {
        return stock;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public void setDetalle(String detalle)
    {
        this.detalle = detalle;
    }

    public void setTalle(String talle)
    {
        this.talle = talle;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public void setMaterial(String material)
    {
        this.material = material;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public static Producto altaProducto(ArrayList<Producto> productos, int codigo, String detalle, String talle, double precio, String marca, String material, Categoria categoria, int stock)
    {
        if (codigo <= 0 || detalle == null || detalle.trim().isEmpty() || precio <= 0 || stock < 0)
        {
            throw new Excepciones.ProductoInvalidoException("Datos del producto inválidos.");
        }
        Producto producto = new Producto(codigo, detalle, talle, precio, marca, material, categoria, stock);
        productos.add(producto);
        return producto;
    }

    public static void bajaProducto(ArrayList<Producto> productos, int codigo)
    {
        if (productos == null)
        {
            throw new Excepciones.ListaProductosNulaException("La lista de productos no puede ser nula.");
        }
            if (!productos.removeIf(producto -> producto.getCodigo() == codigo))
            {
                System.out.println("No se encontro el producto con codigo " + codigo + ".");
            }
                else
                {
                    System.out.println("Clases.Producto con codigo " + codigo + " eliminado.");
                }
    }

    public void modificarProducto(String detalle, String talle, double precio, String marca, String material, Categoria categoria, int stock)
    {
        if (detalle == null || detalle.trim().isEmpty() || precio <= 0 || stock < 0)
        {
            throw new Excepciones.ProductoInvalidoException("Datos del producto inválidos.");
        }
        this.detalle = detalle;
        this.talle = talle;
        this.precio = precio;
        this.marca = marca;
        this.material = material;
        this.categoria = categoria;
        this.stock = stock;
    }

    public static ArrayList<Producto> listarProductos(ArrayList<Producto> productos)
    {
        if (productos == null)
        {
            throw new Excepciones.ListaProductosNulaException("La lista de productos no puede ser nula.");
        }
        return new ArrayList<>(productos);
    }

    public static Producto buscarProducto(ArrayList<Producto> productos, int codigo)
    {
        if (productos == null)
        {
            throw new Excepciones.ListaProductosNulaException("La lista de productos no puede ser nula.");
        }
        for (Producto producto : productos)
        {
            if (producto.getCodigo() == codigo)
            {
                return producto;
            }
        }
        throw new Excepciones.ProductoNoEncontradoExcepcion("No se encontro el producto con codigo " + codigo + ".");
    }

    public static void verDetalleProducto(Producto producto)
    {
        if (producto == null)
        {
            throw new Excepciones.ProductoInvalidoException("El producto no puede ser nulo.");
        }
        System.out.println("Código: " + producto.getCodigo());
        System.out.println("Detalle: " + producto.getDetalle());
        System.out.println("Talle: " + producto.getTalle());
        System.out.println("Precio: " + producto.getPrecio());
        System.out.println("Marca: " + producto.getMarca());
        System.out.println("Material: " + producto.getMaterial());
        System.out.println("Categoría: " + producto.getCategoria());
        System.out.println("Stock: " + producto.getStock());
    }

    public void actualizarStock(int cantidad)
    {
        if (this.stock + cantidad < 0)
        {
            throw new Excepciones.ProductoInvalidoException("El stock no puede ser negativo.");
        }
        this.stock += cantidad;
    }

    /*
    El System.out.println() es para la salida de información en la consola en momentos especificos.
    El toString() es para la salida de informacion en la consola cuando se imprime el objeto.
    Por ejemplo, si se imprime un objeto de la clase Clases.Producto, se llamara automaticamente al metodo toString() para mostrar la informacion del objeto.
    */
    @Override
    public String toString() //God no?
    {
        return "Clases.Producto\n" +
                "{\n" +
                "Codigo = " + codigo + ",\n" +
                "Detalle = '" + detalle + "',\n" +
                "Talle = '" + talle + "',\n" +
                "Precio = " + precio + ",\n" +
                "Marca = '" + marca + "',\n" +
                "Material ='" + material + "',\n" +
                "Categoria =" + categoria + ",\n" +
                "Stock=" + stock + "\n" +
                "}";
    }

    @Override
    public int compareTo(Producto otroProducto)
    {
        return Integer.compare(this.codigo, otroProducto.codigo); //Es para ordenar por codigo
    }
}