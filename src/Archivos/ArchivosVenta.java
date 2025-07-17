package Archivos;

import Clases.*;
import Enumerados.FormaPago;
import Principal.Excepciones.ProductoArchivoException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArchivosVenta
{
    private static final String ARCHIVO = "ventas.csv";

    // Guarda una venta en el archivo CSV (agrega al final)
    // Recibe una venta y la escribe como una línea en el archivo ventas.csv
    public static void guardarVenta(Venta venta) throws ProductoArchivoException
    {
        if (venta == null)
        {
            throw new ProductoArchivoException("NO SE PUEDE GUARDAR UNA VENTA NULA.");
        }
        if (venta.getCliente() == null)
        {
            throw new ProductoArchivoException("NO SE PUEDE GUARDAR UNA VENTA SIN CLIENTE.");
        }
        // Validación de unicidad: no permite guardar ventas con código repetido
        if (existeCodigoVenta(venta.getCodigo()))
        {
            throw new ProductoArchivoException("YA EXISTE UNA VENTA CON ESE CODIGO.");
        }
            try (FileWriter fw = new FileWriter(ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw))
            {
                StringBuilder productosStr = new StringBuilder();
                for (int i = 0; i < venta.getProducto().length; i++)
                {
                    Producto p = venta.getProducto()[i];
                    int c = venta.getCantidad()[i];
                    if (p != null)
                    {
                        productosStr.append(p.getCodigo()).append(":").append(c).append(";");
                    }
                }
                if (productosStr.length() > 0)
                {
                    productosStr.setLength(productosStr.length() - 1);
                }

                bw.write
                (
                    venta.getCodigo() + "," +
                    venta.getFecha() + "," +
                    venta.getCliente().getCodigo() + "," +
                    productosStr + "," +
                    venta.getTotal() + "," +
                    venta.getPago()
                );
                bw.newLine();
            }
                catch (IOException e)
                {
                    throw new ProductoArchivoException("ERROR AL GUARDAR VENTA: " + e.getMessage());
                }
    }

    // Lee todas las ventas del archivo y las devuelve en una lista
    // Abre el archivo, lee cada línea y reconstruye los objetos Venta
    public static ArrayList<Venta> leerVentas() throws ProductoArchivoException
    { // Las cosas que hay aca sabra Dios que son, pero aca si tengo que admitir que me dieron una mano (tampoco entendi por que funciona, pero bueno, funciona)
        ArrayList<Venta> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            int nroLinea = 0;
            while ((linea = br.readLine()) != null)
            {
                nroLinea++;
                try
                {
                    String[] datos = linea.split(",");
                    if (datos.length != 6)
                    {
                        System.out.println("LINEA " + nroLinea + " MAL FORMADA, SE OMITE.");
                        continue;
                    }
                    int codigo = Integer.parseInt(datos[0]);
                    LocalDate fecha = LocalDate.parse(datos[1]);
                    Cliente cliente = Cliente.buscarClientePorCodigo(Integer.parseInt(datos[2]));
                    if (cliente == null)
                    {
                        System.out.println("CLIENTE NO ENCONTRADO EN LINEA " + nroLinea + ", SE OMITE LA VENTA.");
                        continue;
                    }
                    String productosYcantidades = datos[3];
                    double total = Double.parseDouble(datos[4]);
                    FormaPago pago = FormaPago.valueOf(datos[5]);

                    Producto[] productos = new Producto[10];
                    int[] cantidades = new int[10];
                    int idx = 0;
                    if (!productosYcantidades.isEmpty())
                    {
                        for (String prodCant : productosYcantidades.split(";"))
                        {
                            String[] pc = prodCant.split(":");
                            if (pc.length == 2)
                            {
                                Producto prod = Producto.buscarProducto(Integer.parseInt(pc[0]));
                                if (prod == null)
                                {
                                    System.out.println("PRODUCTO NO ENCONTRADO EN LINEA " + nroLinea + ", SE OMITE ESE PRODUCTO.");
                                    continue;
                                }
                                int cant = Integer.parseInt(pc[1]);
                                productos[idx] = prod;
                                cantidades[idx] = cant;
                                idx++;
                                if (idx >= 10)
                                {
                                    System.out.println("SE ENCONTRARON MAS DE 10 PRODUCTOS EN LINEA " + nroLinea + ", SOLO SE TOMAN LOS PRIMEROS 10.");
                                    break;
                                }
                            }
                        }
                    }
                    Venta venta = new Venta(codigo, fecha, cliente, productos, cantidades, total, pago);
                    lista.add(venta);
                }
                    catch (Exception ex)
                    {
                        System.out.println("ERROR AL PROCESAR LINEA " + nroLinea + ": " + ex.getMessage());
                    }
            }
        }
            catch (FileNotFoundException e)
            {
                // Si el archivo no existe, retorna lista vacía
            }
                catch (IOException ex)
                {
                    throw new ProductoArchivoException("ERROR AL LEER VENTAS: " + ex.getMessage());
                }
                return lista;
    }

    // Busca una venta por código
    // Devuelve la venta cuyo código coincide, o null si no existe
    public static Venta buscarPorCodigo(int codigo) throws ProductoArchivoException
    {
        for (Venta v : leerVentas())
        {
            if (v.getCodigo() == codigo)
            {
                return v;
            }
        }
        return null;
    }

    // Elimina una venta por código
    // Borra la venta del archivo si existe y devuelve true, sino false
    public static boolean eliminarVenta(int codigo) throws ProductoArchivoException
    {
        ArrayList<Venta> lista = leerVentas();
        boolean eliminado = lista.removeIf(v -> v.getCodigo() == codigo);
        if (eliminado)
        {
            escribirListaCompleta(lista);
        }
        return eliminado;
    }

    // Modifica una venta existente por su código
    // Busca una venta por código y la reemplaza por la nueva venta recibida
    public static boolean modificarVenta(Venta ventaModificada) throws ProductoArchivoException
    {
        ArrayList<Venta> lista = leerVentas();
        boolean modificado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getCodigo() == ventaModificada.getCodigo())
            {
                lista.set(i, ventaModificada);
                modificado = true;
                break;
            }
        }
        if (modificado)
        {
            escribirListaCompleta(lista);
        }
        return modificado;
    }

    // Filtra ventas por código de cliente
    // Devuelve una lista de ventas hechas por un cliente específico
    public static ArrayList<Venta> filtrarPorCliente(int codigoCliente) throws ProductoArchivoException
    {
        ArrayList<Venta> resultado = new ArrayList<>();
        for (Venta v : leerVentas())
        {
            if (v.getCliente() != null && v.getCliente().getCodigo() == codigoCliente)
            {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Filtra ventas por fecha exacta
    // Devuelve una lista de ventas hechas en una fecha específica
    public static ArrayList<Venta> filtrarPorFecha(LocalDate fecha) throws ProductoArchivoException
    {
        ArrayList<Venta> resultado = new ArrayList<>();
        for (Venta v : leerVentas())
        {
            if (v.getFecha() != null && v.getFecha().equals(fecha))
            {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Filtra ventas por forma de pago
    // Devuelve una lista de ventas hechas con una forma de pago específica
    public static ArrayList<Venta> filtrarPorFormaPago(FormaPago formaPago) throws ProductoArchivoException
    {
        ArrayList<Venta> resultado = new ArrayList<>();
        for (Venta v : leerVentas())
        {
            if (v.getPago() == formaPago)
            {
                resultado.add(v);
            }
        }
        return resultado;
    }

    // Verifica si ya existe una venta con el mismo codigo
    // Devuelve true si existe, false si no existe (para evitar que aiga duplicados)
    public static boolean existeCodigoVenta(int codigo) throws ProductoArchivoException
    {
        for (Venta v : leerVentas())
        {
            if (v.getCodigo() == codigo)
            {
                return true;
            }
        }
        return false;
    }

    // Sobrescribe el archivo con la lista de ventas recibida
    // Se usa al eliminar o modificar una venta para actualizar el archivo completo
    private static void escribirListaCompleta(ArrayList<Venta> lista) throws ProductoArchivoException
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, false);
        BufferedWriter bw = new BufferedWriter(fw))
        {
            for (Venta venta : lista)
            {
                StringBuilder productosStr = new StringBuilder();
                for (int i = 0; i < venta.getProducto().length; i++)
                {
                    Producto p = venta.getProducto()[i];
                    int c = venta.getCantidad()[i];
                    if (p != null)
                    {
                        productosStr.append(p.getCodigo()).append(":").append(c).append(";");
                    }
                }
                if (productosStr.length() > 0)
                {
                    productosStr.setLength(productosStr.length() - 1);
                }

                bw.write
                (
                    venta.getCodigo() + "," +
                    venta.getFecha() + "," +
                    venta.getCliente().getCodigo() + "," +
                    productosStr + "," +
                    venta.getTotal() + "," +
                    venta.getPago()
                );
                bw.newLine();
            }
        }
            catch (IOException e)
            {
                throw new ProductoArchivoException("ERROR AL ESCRIBIR VENTAS: " + e.getMessage());
            }
    }
}