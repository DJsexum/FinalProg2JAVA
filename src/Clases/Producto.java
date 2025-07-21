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
            throw new Excepciones.ProductoInvalidoException("EL CODIGO DEL PRODUCTO DEBE SER UN NUMERO POSITIVO.");
        if (detalle == null || detalle.trim().isEmpty())
            throw new Excepciones.ProductoInvalidoException("EL DETALLE DEL PRODUCTO NO PUEDE ESTAR VACIO.");
        if (precio < 0)
            throw new Excepciones.ProductoInvalidoException("EL PRECIO DEL PRODUCTO NO PUEDE SER NEGATIVO.");
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
        System.out.print("CODIGO: ");
        this.codigo = scanner.nextInt(); scanner.nextLine();
        System.out.print("DETALLE: ");
        this.detalle = scanner.nextLine();
        System.out.print("TALLE: ");
        this.talle = scanner.nextLine();
        System.out.print("PRECIO: ");
        this.precio = scanner.nextDouble(); scanner.nextLine();
        
        // Confirmación para precio 0
        if (this.precio == 0) 
        {
            System.out.println("\n┌─────────────────────────────────────────────────────────┐");
            System.out.println("│                      ATENCION                          │");
            System.out.println("├─────────────────────────────────────────────────────────┤");
            System.out.println("│  HAS INGRESADO PRECIO $0.00                            │");
            System.out.println("│  ESTE PRODUCTO APARECERA COMO 'GRATIS' EN LAS LISTAS   │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            System.out.print("¿CONFIRMAS QUE EL PRODUCTO ES GRATIS? (S/N): ");
            String confirmacion = scanner.nextLine().toUpperCase();
            if (!confirmacion.equals("S")) 
            {
                System.out.print("INGRESA EL PRECIO CORRECTO: ");
                this.precio = scanner.nextDouble(); scanner.nextLine();
            }
        }
        
        System.out.print("MARCA: ");
        this.marca = scanner.nextLine();
        System.out.print("MATERIAL: ");
        this.material = scanner.nextLine();
        System.out.print("CATEGORIA: ");
        this.categoria = Categoria.seleccionarCategoria();
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
                    System.out.println("NO SE ENCONTRO EL PRODUCTO.");
        } 
            catch (Excepciones.ProductoArchivoException e) 
            {
                System.out.println("ERROR AL ELIMINAR EL PRODUCTO: " + e.getMessage().toUpperCase());
            }
    }

    // Modificar producto: pide nuevos datos y actualiza en archivo (ya se que podria haber modularizado más, pero lo hice así por que las neuronas a las horas que lo hice, me daban para hacerlo asi)
    public void modificarProducto()
    {
        System.out.print("NUEVO DETALLE: ");
        this.detalle = scanner.nextLine();
        System.out.print("NUEVO TALLE: ");
        this.talle = scanner.nextLine();
        System.out.print("NUEVO PRECIO: ");
        this.precio = scanner.nextDouble(); scanner.nextLine();
        
        // Confirmación para precio 0
        if (this.precio == 0) 
        {
            System.out.println("\n┌─────────────────────────────────────────────────────────┐");
            System.out.println("│                      ATENCION                           │");
            System.out.println("├─────────────────────────────────────────────────────────┤");
            System.out.println("│  HAS INGRESADO PRECIO $0.00                             │");
            System.out.println("│  ESTE PRODUCTO APARECERA COMO 'GRATIS' EN LAS LISTAS    │");
            System.out.println("└─────────────────────────────────────────────────────────┘");
            System.out.print("¿CONFIRMAS QUE EL PRODUCTO ES GRATIS? (S/N): ");
            String confirmacion = scanner.nextLine().toUpperCase();
            if (!confirmacion.equals("S"))
            {
                System.out.print("INGRESA EL PRECIO CORRECTO: ");
                this.precio = scanner.nextDouble(); scanner.nextLine();
            }
        }
        
        System.out.print("NUEVA MARCA: ");
        this.marca = scanner.nextLine();
        System.out.print("NUEVO MATERIAL: ");
        this.material = scanner.nextLine();
        System.out.print("NUEVA CATEGORIA: ");
        this.categoria = Categoria.seleccionarCategoria();
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
                return encontrado;
                else
                {
                    System.out.println("NO SE ENCONTRO PRODUCTO CON ESE CODIGO.");
                    return null;
                }
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
        System.out.println("CODIGO: " + this.codigo);
        System.out.println("DETALLE: " + this.detalle);
        System.out.println("TALLE: " + this.talle);
        System.out.println("PRECIO: " + this.precio);
        System.out.println("MARCA: " + this.marca);
        System.out.println("MATERIAL: " + this.material);
        System.out.println("CATEGORIA: " + this.categoria);
        System.out.println("STOCK: " + this.stock);
    }

    // Mostrar un producto individual con formato de tabla profesional
    public static void mostrarProductoEnTabla(Producto producto)
    {
        if (producto == null)
        {
            System.out.println("NO SE ENCONTRO PRODUCTO CON ESE CODIGO.");
            return;
        }

        System.out.println("┌────────┬──────────────┬─────────────────┬──────────────────┬──────────────────────┬────────┬──────────────┬────────┐");
        System.out.printf("│%8s│%14s│%17s│%18s│%22s│%8s│%14s│%8s│\n",
            centrarTexto("CODIGO", 8), centrarTexto("CATEGORIA", 14), centrarTexto("MARCA", 17), 
            centrarTexto("MATERIAL", 18), centrarTexto("DETALLE", 22), centrarTexto("TALLE", 8), 
            centrarTexto("PRECIO", 14), centrarTexto("STOCK", 8));
        System.out.println("├────────┼──────────────┼─────────────────┼──────────────────┼──────────────────────┼────────┼──────────────┼────────┤");
        
        // Aplicar mayúsculas y quitar acentos
        String detalle = quitarAcentos(producto.getDetalle().toUpperCase());
        String talle = quitarAcentos(producto.getTalle().toUpperCase());
        String marca = quitarAcentos(producto.getMarca().toUpperCase());
        String material = quitarAcentos(producto.getMaterial().toUpperCase());
        
        // Truncar texto si es muy largo
        detalle = detalle.length() > 22 ? detalle.substring(0, 19) + "..." : detalle;
        marca = marca.length() > 17 ? marca.substring(0, 14) + "..." : marca;
        material = material.length() > 18 ? material.substring(0, 15) + "..." : material;
        String categoria = producto.getCategoria().toString().length() > 14 ? producto.getCategoria().toString().substring(0, 11) + "..." : producto.getCategoria().toString();
        
        // Formatear precio: mostrar "GRATIS" si es 0, sino el precio con 2 decimales
        String precioTexto = (producto.getPrecio() == 0) ? "GRATIS" : String.format("%.2f", producto.getPrecio());
        
        // Mostrar la fila del producto
        System.out.printf("│%8s│%14s│%17s│%18s│%22s│%8s│%14s│%8s│\n",
            centrarTexto(String.valueOf(producto.getCodigo()), 8), 
            centrarTexto(categoria, 14), 
            centrarTexto(marca, 17), 
            centrarTexto(material, 18),
            centrarTexto(detalle, 22),
            centrarTexto(talle, 8), 
            centrarTexto(precioTexto, 14),
            centrarTexto(String.valueOf(producto.getStock()), 8));
            
        System.out.println("└────────┴──────────────┴─────────────────┴──────────────────┴──────────────────────┴────────┴──────────────┴────────┘");
    }

    // Mostrar lista de productos con formato de tabla profesional
    public static void mostrarListaProductosEnTabla(ArrayList<Producto> lista)
    {
        if (lista == null || lista.isEmpty())
        {
            System.out.println("NO HAY PRODUCTOS REGISTRADOS.\n");
            return;
        }

        System.out.println("┌────────┬──────────────┬─────────────────┬──────────────────┬──────────────────────┬────────┬──────────────┬────────┐");
        System.out.printf("│%8s│%14s│%17s│%18s│%22s│%8s│%14s│%8s│\n",
            centrarTexto("CODIGO", 8), centrarTexto("CATEGORIA", 14), centrarTexto("MARCA", 17), 
            centrarTexto("MATERIAL", 18), centrarTexto("DETALLE", 22), centrarTexto("TALLE", 8), 
            centrarTexto("PRECIO", 14), centrarTexto("STOCK", 8));
        System.out.println("├────────┼──────────────┼─────────────────┼──────────────────┼──────────────────────┼────────┼──────────────┼────────┤");
        
        // Filas de productos con separadores entre cada uno
        for (int i = 0; i < lista.size(); i++)
        {
            Producto p = lista.get(i);
            // Aplicar mayúsculas y quitar acentos
            String detalle = quitarAcentos(p.getDetalle().toUpperCase());
            String talle = quitarAcentos(p.getTalle().toUpperCase());
            String marca = quitarAcentos(p.getMarca().toUpperCase());
            String material = quitarAcentos(p.getMaterial().toUpperCase());
            
            // Truncar texto si es muy largo
            detalle = detalle.length() > 22 ? detalle.substring(0, 19) + "..." : detalle;
            marca = marca.length() > 17 ? marca.substring(0, 14) + "..." : marca;
            material = material.length() > 18 ? material.substring(0, 15) + "..." : material;
            String categoria = p.getCategoria().toString().length() > 14 ? p.getCategoria().toString().substring(0, 11) + "..." : p.getCategoria().toString();
            
            // Formatear precio: mostrar "GRATIS" si es 0, sino el precio con 2 decimales
            String precioTexto = (p.getPrecio() == 0) ? "GRATIS" : String.format("%.2f", p.getPrecio());
            
            // Mostrar la fila del producto
            System.out.printf("│%8s│%14s│%17s│%18s│%22s│%8s│%14s│%8s│\n",
                centrarTexto(String.valueOf(p.getCodigo()), 8), 
                centrarTexto(categoria, 14), 
                centrarTexto(marca, 17), 
                centrarTexto(material, 18),
                centrarTexto(detalle, 22),
                centrarTexto(talle, 8), 
                centrarTexto(precioTexto, 14),
                centrarTexto(String.valueOf(p.getStock()), 8));
                
            // Agregar línea separadora entre productos (excepto después del último)
            if (i < lista.size() - 1)
            {
                System.out.println("├────────┼──────────────┼─────────────────┼──────────────────┼──────────────────────┼────────┼──────────────┼────────┤");
            }
        }
        System.out.println("└────────┴──────────────┴─────────────────┴──────────────────┴──────────────────────┴────────┴──────────────┴────────┘");
    }

    // Método auxiliar para centrar texto en un ancho específico
    private static String centrarTexto(String texto, int ancho)
    {
        if (texto.length() >= ancho)
        {
            return texto.substring(0, ancho);
        }
        int espaciosTotal = ancho - texto.length();
        int espaciosIzquierda = espaciosTotal / 2;
        int espaciosDerecha = espaciosTotal - espaciosIzquierda;
        
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < espaciosIzquierda; i++) {
            resultado.append(" ");
        }
        resultado.append(texto);
        for (int i = 0; i < espaciosDerecha; i++) {
            resultado.append(" ");
        }
        return resultado.toString();
    }
    
    // Método auxiliar para quitar acentos del texto
    private static String quitarAcentos(String texto)
    {
        if (texto == null) return null;
        return texto
            .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
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
        String precioTexto = (precio == 0) ? "GRATIS" : String.valueOf(precio);
        
        return "PRODUCTO:\n" +
                "\n" +
                "CODIGO = " + codigo + "\n" +
                "DETALLE = " + detalle + "\n" +
                "TALLE = " + talle + "\n" +
                "PRECIO = " + precioTexto + "\n" +
                "MARCA = " + marca + "\n" +
                "MATERIAL = " + material + "\n" +
                "CATEGORIA = " + categoria + "\n" +
                "STOCK = " + stock + "\n";
    }

    // Permite ordenar productos por código
    @Override
    public int compareTo(Producto otroProducto)
    {
        return Integer.compare(this.codigo, otroProducto.codigo);
    }
}