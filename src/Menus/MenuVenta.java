package Menus;

import Clases.*;
import Archivos.ArchivosVenta;
import Enumerados.FormaPago;
import Principal.Excepciones.ProductoArchivoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuVenta
{
    private static final Scanner scanner = new Scanner(System.in);

    // Método auxiliar para leer un entero con validación y opción de cancelar
    private static int leerEntero(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            if (input.equals("0")) return 0;
            try
            {
                return Integer.parseInt(input);
            }
                catch (NumberFormatException e)
                {
                    System.out.println("INGRESE UN NUMERO VALIDO.");
                }
        }
    }

    // Método auxiliar para leer una fecha con validación y opción de cancelar
    private static LocalDate leerFecha(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            if (input.equals("0")) return null;
            try
            {
                return LocalDate.parse(input);
            }
                catch (Exception e)
                {
                    System.out.println("FORMATO DE FECHA INVALIDO. USE YYYY-MM-DD.");
                }
        }
    }

    // Muestra el menú principal de ventas y gestiona la selección del usuario
    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("===== MENU DE VENTAS =====");
            System.out.println("1. ALTA DE VENTA");
            System.out.println("2. LISTAR TODAS LAS VENTAS");
            System.out.println("3. BUSCAR VENTA POR CODIGO");
            System.out.println("4. MODIFICAR VENTA");
            System.out.println("5. ELIMINAR VENTA");
            System.out.println("6. FILTRAR VENTAS POR CLIENTE");
            System.out.println("7. FILTRAR VENTAS POR FECHA");
            System.out.println("8. FILTRAR VENTAS POR FORMA DE PAGO");
            System.out.println("0. VOLVER AL MENU PRINCIPAL");
            opcion = leerEntero("INGRESE UNA OPCION: ");

            switch (opcion)
            {
                case 1:
                    altaVenta();
                break;

                case 2:
                    listarVentas();
                break;

                case 3:
                    buscarVentaPorCodigo();
                break;

                case 4:
                    modificarVenta();
                break;

                case 5:
                    eliminarVenta();
                break;

                case 6:
                    filtrarPorCliente();
                break;

                case 7:
                    filtrarPorFecha();
                break;

                case 8:
                    filtrarPorFormaPago();
                break;

                case 0:
                    System.out.println("VOLVIENDO AL MENU PRINCIPAL...");
                break;

                default:
                    System.out.println("OPCION INVALIDA.");
            }
        }
        while (opcion != 0);
    }

    // Permite dar de alta una nueva venta pidiendo los datos por consola y validando todo
    private static void altaVenta()
    {
        try
        {
            System.out.println("=== ALTA DE VENTA ===");
            int codigo = leerEntero("INGRESE CODIGO DE VENTA (0 para cancelar): ");
            if (codigo == 0) return;

            if (ArchivosVenta.existeCodigoVenta(codigo))
            {
                System.out.println("YA EXISTE UNA VENTA CON ESE CODIGO.");
                return;
            }

            LocalDate fecha = LocalDate.now();

            int codCliente = leerEntero("INGRESE CODIGO DE CLIENTE (0 para cancelar): ");
            if (codCliente == 0) return;
            Cliente cliente = Cliente.buscarClientePorCodigo(codCliente);
            if (cliente == null)
            {
                System.out.println("CLIENTE NO ENCONTRADO.");
                return;
            }
            System.out.println("CLIENTE: " + cliente);

            Producto[] productos = new Producto[10];
            int[] cantidades = new int[10];
            int productosAgregados = 0;
            double total = 0;

            // Permite agregar hasta 10 productos, sin repetir y con cantidad válida
            while (productosAgregados < 10)
            {
                System.out.print("AGREGAR PRODUCTO? (S/N, 0 para cancelar): ");
                String resp = scanner.nextLine().toUpperCase();
                if (resp.equals("0")) return;
                if (!resp.equals("S")) break;

                int codProd = leerEntero("INGRESE CODIGO DE PRODUCTO: ");
                if (codProd == 0) return;
                boolean repetido = false;
                for (int i = 0; i < productosAgregados; i++)
                {
                    if (productos[i] != null && productos[i].getCodigo() == codProd)
                    {
                        repetido = true;
                        break;
                    }
                }
                if (repetido)
                {
                    System.out.println("YA AGREGASTE ESE PRODUCTO.");
                    continue;
                }
                Producto prod = Producto.buscarProducto(codProd);
                if (prod == null)
                {
                    System.out.println("PRODUCTO NO ENCONTRADO.");
                    continue;
                }
                System.out.println("PRODUCTO: " + prod);

                int cant = leerEntero("INGRESE CANTIDAD: ");
                if (cant <= 0)
                {
                    System.out.println("LA CANTIDAD DEBE SER MAYOR A CERO.");
                    continue;
                }
                productos[productosAgregados] = prod;
                cantidades[productosAgregados] = cant;
                total += prod.getPrecio() * cant;
                productosAgregados++;
            }

            FormaPago pago = FormaPago.escogerFormaPago();

            Venta venta = new Venta(codigo, fecha, cliente, productos, cantidades, total, pago);
            ArchivosVenta.guardarVenta(venta);
            System.out.println("VENTA GUARDADA CORRECTAMENTE.");
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL DAR DE ALTA LA VENTA: " + e.getMessage());
            }
    }

    // Lista todas las ventas guardadas en el archivo
    private static void listarVentas()
    {
        try
        {
            ArrayList<Venta> lista = ArchivosVenta.leerVentas();
            if (lista.isEmpty())
            {
                System.out.println("NO HAY VENTAS REGISTRADAS.");
            }
                else
                {
                    for (Venta v : lista)
                    {
                        System.out.println(v);
                    }
                }
        }
            catch (ProductoArchivoException e)
            {
                System.out.println("ERROR AL LISTAR VENTAS: " + e.getMessage());
            }
    }

    // Busca y muestra una venta por su código
    private static void buscarVentaPorCodigo()
    {
        try
        {
            int codigo = leerEntero("INGRESE CODIGO DE VENTA (0 para cancelar): ");
            if (codigo == 0) return;
            Venta v = ArchivosVenta.buscarPorCodigo(codigo);
            if (v != null)
            {
                System.out.println(v);
            }
                else
                {
                    System.out.println("VENTA NO ENCONTRADA.");
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL BUSCAR VENTA: " + e.getMessage());
            }
    }

    // Permite modificar una venta existente (pide todos los datos de nuevo y actualiza el archivo)
    private static void modificarVenta()
    {
        try
        {
            int codigo = leerEntero("INGRESE CODIGO DE VENTA A MODIFICAR (0 para cancelar): ");
            if (codigo == 0) return;
            Venta ventaExistente = ArchivosVenta.buscarPorCodigo(codigo);
            if (ventaExistente == null)
            {
                System.out.println("NO SE ENCONTRO LA VENTA.");
                return;
            }
            System.out.println("VENTA ACTUAL: " + ventaExistente);
            System.out.print("CONFIRMA MODIFICACION? (S/N): ");
            String conf = scanner.nextLine().toUpperCase();
            if (!conf.equals("S"))
            {
                System.out.println("OPERACION CANCELADA.");
                return;
            }

            int codCliente = leerEntero("INGRESE NUEVO CODIGO DE CLIENTE (0 para cancelar): ");
            if (codCliente == 0) return;
            Cliente cliente = Cliente.buscarClientePorCodigo(codCliente);
            if (cliente == null)
            {
                System.out.println("CLIENTE NO ENCONTRADO.");
                return;
            }
            System.out.println("CLIENTE: " + cliente);

            Producto[] productos = new Producto[10];
            int[] cantidades = new int[10];
            int productosAgregados = 0;
            double total = 0;

            while (productosAgregados < 10)
            {
                System.out.print("AGREGAR PRODUCTO? (S/N, 0 para cancelar): ");
                String resp = scanner.nextLine().toUpperCase();
                if (resp.equals("0")) return;
                if (!resp.equals("S")) break;

                int codProd = leerEntero("INGRESE CODIGO DE PRODUCTO: ");
                if (codProd == 0) return;
                boolean repetido = false;
                for (int i = 0; i < productosAgregados; i++)
                {
                    if (productos[i] != null && productos[i].getCodigo() == codProd)
                    {
                        repetido = true;
                        break;
                    }
                }
                if (repetido)
                {
                    System.out.println("YA AGREGASTE ESE PRODUCTO.");
                    continue;
                }
                Producto prod = Producto.buscarProducto(codProd);
                if (prod == null)
                {
                    System.out.println("PRODUCTO NO ENCONTRADO.");
                    continue;
                }
                System.out.println("PRODUCTO: " + prod);

                int cant = leerEntero("INGRESE CANTIDAD: ");
                if (cant <= 0)
                {
                    System.out.println("LA CANTIDAD DEBE SER MAYOR A CERO.");
                    continue;
                }
                productos[productosAgregados] = prod;
                cantidades[productosAgregados] = cant;
                total += prod.getPrecio() * cant;
                productosAgregados++;
            }

            FormaPago pago = FormaPago.escogerFormaPago();

            Venta ventaModificada = new Venta(codigo, LocalDate.now(), cliente, productos, cantidades, total, pago);
            boolean modificado = ArchivosVenta.modificarVenta(ventaModificada);
            if (modificado)
            {
                System.out.println("VENTA MODIFICADA CORRECTAMENTE.");
            }
                else
                {
                    System.out.println("NO SE PUDO MODIFICAR LA VENTA.");
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL MODIFICAR LA VENTA: " + e.getMessage());
            }
    }

    // Elimina una venta por su código
    private static void eliminarVenta()
    {
        try
        {
            int codigo = leerEntero("INGRESE CODIGO DE VENTA A ELIMINAR (0 para cancelar): ");
            if (codigo == 0) return;
            Venta v = ArchivosVenta.buscarPorCodigo(codigo);
            if (v == null)
            {
                System.out.println("NO SE ENCONTRO LA VENTA.");
                return;
            }
            System.out.println("VENTA A ELIMINAR: " + v);
            System.out.print("CONFIRMA ELIMINACION? (S/N): ");
            String conf = scanner.nextLine().toUpperCase();
            if (!conf.equals("S"))
            {
                System.out.println("OPERACION CANCELADA.");
                return;
            }
            boolean eliminado = ArchivosVenta.eliminarVenta(codigo);
            if (eliminado)
            {
                System.out.println("VENTA ELIMINADA CORRECTAMENTE.");
            }
                else
                {
                    System.out.println("NO SE ENCONTRO LA VENTA.");
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL ELIMINAR LA VENTA: " + e.getMessage());
            }
    }

    // Filtra y muestra ventas por código de cliente
    private static void filtrarPorCliente()
    {
        try
        {
            int codigoCliente = leerEntero("INGRESE CODIGO DE CLIENTE (0 para cancelar): ");
            if (codigoCliente == 0) return;
            ArrayList<Venta> lista = ArchivosVenta.filtrarPorCliente(codigoCliente);
            if (lista.isEmpty())
            {
                System.out.println("NO HAY VENTAS PARA ESE CLIENTE.");
            }
                else
                {
                    for (Venta v : lista)
                    {
                        System.out.println(v);
                    }
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL FILTRAR VENTAS POR CLIENTE: " + e.getMessage());
            }
    }

    // Filtra y muestra ventas por fecha
    private static void filtrarPorFecha()
    {
        try
        {
            LocalDate fecha = leerFecha("INGRESE FECHA (YYYY-MM-DD, 0 para cancelar): ");
            if (fecha == null) return;
            ArrayList<Venta> lista = ArchivosVenta.filtrarPorFecha(fecha);
            if (lista.isEmpty())
            {
                System.out.println("NO HAY VENTAS EN ESA FECHA.");
            }
                else
                {
                    for (Venta v : lista)
                    {
                        System.out.println(v);
                    }
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL FILTRAR VENTAS POR FECHA: " + e.getMessage());
            }
    }

    // Filtra y muestra ventas por forma de pago
    private static void filtrarPorFormaPago()
    {
        try
        {
            FormaPago formaPago = FormaPago.escogerFormaPago();
            ArrayList<Venta> lista = ArchivosVenta.filtrarPorFormaPago(formaPago);
            if (lista.isEmpty())
            {
                System.out.println("NO HAY VENTAS CON ESA FORMA DE PAGO.");
            }
                else
                {
                    for (Venta v : lista)
                    {
                        System.out.println(v);
                    }
                }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL FILTRAR VENTAS POR FORMA DE PAGO: " + e.getMessage());
            }
    }
}