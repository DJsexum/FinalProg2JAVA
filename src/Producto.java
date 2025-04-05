import Enumerados.Categoria;
import java.util.ArrayList;


public class Producto
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
        if (codigo <= 0)
        {
            throw new Excepciones.ProductoInvalidoException("El código del producto debe ser un número positivo.");
        }
        if (detalle == null || detalle.trim().isEmpty())
        {
            throw new Excepciones.ProductoInvalidoException("El detalle del producto no puede estar vacío.");
        }
        if (precio <= 0)
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
        if (productos.removeIf(producto -> producto.getCodigo() == codigo))
        {
            System.out.println("Producto con código " + codigo + " eliminado.");
        }
            else
            {
                System.out.println("No se encontró el producto con código " + codigo + ".");
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

    public static Producto busquedaProducto(ArrayList<Producto> productos, int codigo)
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
            throw new Excepciones.ProductoNoEncontradoException("No se encontró el producto con código " + codigo + ".");
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
}