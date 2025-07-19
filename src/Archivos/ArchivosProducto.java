package Archivos;

import Clases.*;
import Enumerados.*;
import Principal.Excepciones.*;
import java.io.*;
import java.util.ArrayList;

/*
Clase utilitaria para gestionar la persistencia de productos en un archivo CSV.
Ahora usa excepciones personalizadas para manejo de errores.
*/
public class ArchivosProducto
{
    // Nombre del archivo donde se guardan los productos
    private static final String ARCHIVO = "src/Datos/productos.csv";

    /*
    Guarda un producto en el archivo CSV (agrega al final).
    */
    public static void guardarProducto(Producto producto) throws ProductoArchivoException
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write
            (
                producto.getCodigo() + "," +
                producto.getDetalle() + "," +
                producto.getTalle() + "," +
                producto.getPrecio() + "," +
                producto.getMarca() + "," +
                producto.getMaterial() + "," +
                producto.getCategoria() + "," +
                producto.getStock()
            );
            bw.newLine();
        }
            catch (IOException e)
            {
                throw new ProductoArchivoException("ERROR AL GUARDAR PRODUCTO: " + e.getMessage());
            }
    }

    /*
    Lee todos los productos del archivo y los devuelve en una lista.
    */
    public static ArrayList<Producto> leerProductos() throws ProductoArchivoException
    {
        ArrayList<Producto> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",");
                Producto p = new Producto
                (
                    Integer.parseInt(datos[0]), // codigo
                    datos[1], // detalle
                    datos[2], // talle
                    Double.parseDouble(datos[3]), // precio
                    datos[4], // marca
                    datos[5], // material
                    Categoria.valueOf(datos[6]), // categoria
                    Integer.parseInt(datos[7]) // stock
                );
                lista.add(p);
            }
        }
            catch (FileNotFoundException e)
            {
                // Si el archivo no existe, retorna lista vacía (no es error grave)
            }
                catch (IOException ex)
                {
                    throw new ProductoArchivoException("ERROR AL LEER PRODUCTOS: " + ex.getMessage());
                }
                return lista;
    }

    /*
    Busca un producto por código.
    */
    public static Producto buscarPorCodigo(int codigo) throws ProductoArchivoException
    {
        for (Producto p : leerProductos())
        {
            if (p.getCodigo() == codigo)
            {
                return p;
            }
        }
        return null;
    }

    /*
    Modifica un producto existente (por código).
    */
    public static boolean modificarProducto(Producto productoModificado) throws ProductoArchivoException
    {
        ArrayList<Producto> lista = leerProductos();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getCodigo() == productoModificado.getCodigo())
            {
                lista.set(i, productoModificado);
                encontrado = true;
                break;
            }
        }
            if (encontrado)
            {
                escribirListaCompleta(lista);
            }
            return encontrado;
    }

    /*
    Elimina un producto por código.
    */
    public static boolean eliminarProducto(int codigo) throws ProductoArchivoException
    {
        ArrayList<Producto> lista = leerProductos();
        boolean eliminado = lista.removeIf(p -> p.getCodigo() == codigo);
        if (eliminado)
        {
            escribirListaCompleta(lista);
        }
        return eliminado;
    }

    /*
    Método auxiliar para sobrescribir el archivo completo con la lista de productos.
    */
    private static void escribirListaCompleta(ArrayList<Producto> lista) throws ProductoArchivoException
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, false);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for (Producto producto : lista)
            {
                bw.write
                (
                    producto.getCodigo() + "," +
                    producto.getDetalle() + "," +
                    producto.getTalle() + "," +
                    producto.getPrecio() + "," +
                    producto.getMarca() + "," +
                    producto.getMaterial() + "," +
                    producto.getCategoria() + "," +
                    producto.getStock()
                );
                bw.newLine();
            }
        }
            catch (IOException e)
            {
                throw new ProductoArchivoException("Error al escribir productos: " + e.getMessage());
            }
    }
}