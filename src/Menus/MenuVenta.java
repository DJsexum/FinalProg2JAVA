package Menus;

import Clases.*;
import Archivos.*;
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
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                    VENTAS                     │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│       [1] CREAR VENTA                         │");
            System.out.println("│       [2] LISTAR TODAS LAS VENTAS             │");
            System.out.println("│       [3] BUSCAR VENTA POR CODIGO             │");
            System.out.println("│       [4] MODIFICAR VENTA                     │");
            System.out.println("│       [5] ELIMINAR VENTA                      │");
            System.out.println("│       [6] FILTRAR VENTAS POR CLIENTE          │");
            System.out.println("│       [7] FILTRAR VENTAS POR FECHA            │");
            System.out.println("│       [8] FILTRAR VENTAS POR FORMA PAGO       │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│       [0] VOLVER AL MENU PRINCIPAL            │");
            System.out.println("└───────────────────────────────────────────────┘");
            opcion = leerEntero("SELECCIONE UNA OPCION: ");

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
                    // Aca nomas sale al menu principal :)
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
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                 NUEVA VENTA                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            
            // Generar código de venta automáticamente
            int codigo = generarCodigoVenta();
            LocalDate fecha = LocalDate.now();
            
            System.out.println();
            System.out.printf("CODIGO DE VENTA GENERADO: %d%n", codigo);
            System.out.printf("FECHA: %s%n", fecha);

            // PASO 1: Mostrar productos disponibles primero
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           PRODUCTOS DISPONIBLES                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            
            if (!mostrarProductosDisponiblesConStock())
            {
                return; // No hay productos disponibles
            }

            // PASO 2: Seleccionar productos y cantidades
            Producto[] productos = new Producto[10];
            int[] cantidades = new int[10];
            int productosAgregados = 0;
            double total = 0;

            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                           SELECCIONAR PRODUCTOS                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");

            // Permite agregar hasta 10 productos, sin repetir y con validación de stock
            while (productosAgregados < 10)
            {
                if (productosAgregados > 0)
                {
                    System.out.println();
                    System.out.print("¿AGREGAR MÁS PRODUCTOS? (S/N, 0 para cancelar): ");
                    String resp = scanner.nextLine().toUpperCase();
                    if (resp.equals("0")) return;
                    if (!resp.equals("S")) break;
                }
                
                int codProd = leerEntero("INGRESE CODIGO DE PRODUCTO (0 para cancelar): ");
                if (codProd == 0) return;
                
                // Verificar si ya fue agregado
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
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                        YA AGREGASTE ESE PRODUCTO                               ║");
                    System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                    continue;
                }
                
                // Buscar el producto
                Producto prod = Producto.buscarProducto(codProd);
                if (prod == null)
                {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                          PRODUCTO NO ENCONTRADO                                ║");
                    System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                    continue;
                }

                // Mostrar información del producto y stock disponible
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                          PRODUCTO SELECCIONADO                                 ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
                System.out.printf("║ CODIGO:   %-68d ║%n", prod.getCodigo());
                System.out.printf("║ PRODUCTO: %-68s ║%n", prod.getDetalle());
                System.out.printf("║ MARCA:    %-68s ║%n", prod.getMarca());
                System.out.printf("║ PRECIO:   $%-67.2f ║%n", prod.getPrecio());
                System.out.printf("║ STOCK:    %-68d ║%n", prod.getStock());
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");

                // Verificar stock disponible
                if (prod.getStock() <= 0)
                {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                      PRODUCTO SIN STOCK DISPONIBLE                             ║");
                    System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                    continue;
                }

                // Pedir cantidad con validación de stock
                int cant = 0;
                while (cant <= 0 || cant > prod.getStock())
                {
                    cant = leerEntero("INGRESE CANTIDAD (disponible: " + prod.getStock() + "): ");
                    if (cant == 0) break; // Cancelar
                    
                    if (cant <= 0)
                    {
                        System.out.println("LA CANTIDAD DEBE SER MAYOR A CERO.");
                        continue;
                    }
                    
                    if (cant > prod.getStock())
                    {
                        System.out.println();
                        System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                          STOCK INSUFICIENTE                                    ║");
                        System.out.printf("║ CANTIDAD SOLICITADA: %-52d ║%n", cant);
                        System.out.printf("║ STOCK DISPONIBLE:    %-52d ║%n", prod.getStock());
                        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                        continue;
                    }
                }
                
                if (cant == 0) continue; // Si canceló la cantidad, volver a seleccionar producto
                
                // Agregar producto al carrito temporal
                productos[productosAgregados] = prod;
                cantidades[productosAgregados] = cant;
                double subtotal = prod.getPrecio() * cant;
                total += subtotal;
                productosAgregados++;
                
                // Mostrar confirmación de producto agregado
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                         PRODUCTO AGREGADO AL CARRITO                           ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
                System.out.printf("║ CANTIDAD:           %-58d ║%n", cant);
                System.out.printf("║ PRECIO UNITARIO:    $%-57.2f ║%n", prod.getPrecio());
                System.out.printf("║ SUBTOTAL:           $%-57.2f ║%n", subtotal);
                System.out.printf("║ STOCK RESTANTE:     %-58d ║%n", prod.getStock() - cant);
                System.out.printf("║ TOTAL ACUMULADO:    $%-57.2f ║%n", total);
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            }
            
            // Verificar que se haya agregado al menos un producto
            if (productosAgregados == 0)
            {
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                    NO SE AGREGARON PRODUCTOS A LA VENTA                        ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                return;
            }

            // PASO 2: Seleccionar cliente (después de los productos)
            Cliente cliente = seleccionarTipoCliente();
            if (cliente == null)
            {
                System.out.println("OPERACION CANCELADA.");
                return;
            }
            
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            CLIENTE SELECCIONADO                                ║");
            System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
            System.out.printf("║ %-78s ║%n", cliente.getNombres() + " " + cliente.getApellidos());
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");

            // PASO 3: Seleccionar forma de pago
            FormaPago pago = FormaPago.escogerFormaPago();

            // PASO 4: Mostrar resumen y confirmar venta
            mostrarResumenVenta(codigo, fecha, cliente, productos, cantidades, productosAgregados, total, pago);
            
            System.out.print("¿CONFIRMAR VENTA? (S/N): ");
            String confirmacion = scanner.nextLine().toUpperCase();
            
            if (!confirmacion.equals("S"))
            {
                System.out.println("VENTA CANCELADA.");
                return;
            }
            
            // PASO 5: Actualizar stock de productos y crear venta
            for (int i = 0; i < productosAgregados; i++)
            {
                Producto prod = productos[i];
                int cantVendida = cantidades[i];
                prod.setStock(prod.getStock() - cantVendida);
                
                // Actualizar el producto en el archivo
                try
                {
                    ArchivosProducto.modificarProducto(prod);
                }
                catch (Exception e)
                {
                    System.out.println("ERROR AL ACTUALIZAR STOCK DEL PRODUCTO " + prod.getCodigo() + ": " + e.getMessage());
                }
            }

            // Crear y guardar la venta
            Venta nuevaVenta = new Venta(codigo, fecha, cliente, productos, cantidades, total, pago);
            
            try
            {
                ArchivosVenta.guardarVenta(nuevaVenta);
                
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                          VENTA REALIZADA EXITOSAMENTE                          ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                
                // Mostrar stock actualizado
                mostrarStockActualizado(productos, cantidades, productosAgregados);
            }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR LA VENTA: " + e.getMessage());
                
                // Revertir el stock si falló la venta
                for (int i = 0; i < productosAgregados; i++)
                {
                    Producto prod = productos[i];
                    int cantVendida = cantidades[i];
                    prod.setStock(prod.getStock() + cantVendida); // Revertir
                    
                    try
                    {
                        ArchivosProducto.modificarProducto(prod);
                    }
                    catch (Exception ex)
                    {
                        System.out.println("ERROR AL REVERTIR STOCK: " + ex.getMessage());
                    }
                }
            }
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

            Cliente cliente = seleccionarCliente();
            if (cliente == null)
            {
                System.out.println("OPERACION CANCELADA.");
                return;
            }
            System.out.println("CLIENTE SELECCIONADO: " + cliente.getNombres() + " " + cliente.getApellidos());

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

    // Método para seleccionar un cliente con interfaz visual organizada
    private static Cliente seleccionarCliente()
    {
        try
        {
            while (true)
            {
                // Mostrar menú de selección
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                            SELECCIONAR CLIENTE                                 ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
                System.out.println("║ [1] VER TODOS LOS CLIENTES                                                     ║");
                System.out.println("║ [2] BUSCAR POR NOMBRE                                                          ║");
                System.out.println("║ [3] BUSCAR POR APELLIDO                                                        ║");
                System.out.println("║ [4] BUSCAR POR DNI                                                             ║");
                System.out.println("║ [5] BUSCAR POR TELEFONO                                                        ║");
                System.out.println("║ [0] CANCELAR                                                                   ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                
                int opcion = leerEntero("SELECCIONE UNA OPCION: ");
                
                ArrayList<Cliente> clientesEncontrados = new ArrayList<>();
                
                switch (opcion)
                {
                    case 0:
                        return null;
                    case 1:
                        clientesEncontrados = ArchivosCliente.leerClientes();
                    break;

                    case 2:
                        System.out.print("INGRESE NOMBRE A BUSCAR: ");
                        String nombre = scanner.nextLine().toUpperCase();
                        if (nombre.trim().isEmpty()) continue;
                        for (Cliente c : ArchivosCliente.leerClientes())
                        {
                            if (c.getNombres().toUpperCase().contains(nombre))
                            {
                                clientesEncontrados.add(c);
                            }
                        }
                    break;

                    case 3:
                        System.out.print("INGRESE APELLIDO A BUSCAR: ");
                        String apellido = scanner.nextLine().toUpperCase();
                        if (apellido.trim().isEmpty()) continue;
                        for (Cliente c : ArchivosCliente.leerClientes())
                        {
                            if (c.getApellidos().toUpperCase().contains(apellido))
                            {
                                clientesEncontrados.add(c);
                            }
                        }
                    break;

                    case 4:
                        int dniBuscar = leerEntero("INGRESE DNI A BUSCAR (0 para volver): ");
                        if (dniBuscar == 0) continue;
                        Cliente clienteDni = ArchivosCliente.buscarPorDni(dniBuscar);
                        if (clienteDni != null)
                        {
                            clientesEncontrados.add(clienteDni);
                        }
                    break;

                    case 5:
                        System.out.print("INGRESE TELEFONO A BUSCAR: ");
                        String telefono = scanner.nextLine();
                        if (telefono.trim().isEmpty()) continue;
                        for (Cliente c : ArchivosCliente.leerClientes())
                        {
                            if (c.getTelefono().contains(telefono))
                            {
                                clientesEncontrados.add(c);
                            }
                        }
                    break;

                    default:
                        System.out.println("OPCION INVALIDA.");
                        continue;
                }
                
                if (clientesEncontrados.isEmpty())
                {
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                          NO SE ENCONTRARON CLIENTES                            ║");
                    System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                    System.out.print("PRESIONE ENTER PARA CONTINUAR...");
                    scanner.nextLine();
                    continue;
                }
                
                // Mostrar clientes encontrados en tabla organizada
                Cliente clienteSeleccionado = mostrarYSeleccionarCliente(clientesEncontrados);
                if (clienteSeleccionado != null)
                {
                    return clienteSeleccionado;
                }
                // Si no seleccionó ninguno, vuelve al menú
            }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL SELECCIONAR CLIENTE: " + e.getMessage());
                return null;
            }
    }
    
    // Método auxiliar para mostrar la tabla de clientes y permitir selección
    private static Cliente mostrarYSeleccionarCliente(ArrayList<Cliente> clientes)
    {
        try
        {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                             CLIENTES ENCONTRADOS                               ║");
            System.out.println("╠═══╦═════════╦══════════════════════════════════════════════════════════════════╣");
            System.out.println("║ # ║   DNI   ║                    NOMBRE COMPLETO                               ║");
            System.out.println("╠═══╬═════════╬══════════════════════════════════════════════════════════════════╣");
            
            for (int i = 0; i < clientes.size(); i++)
            {
                Cliente c = clientes.get(i);
                String nombreCompleto = c.getNombres() + " " + c.getApellidos();
                // Truncar si es muy largo para mantener formato
                if (nombreCompleto.length() > 59)
                {
                    nombreCompleto = nombreCompleto.substring(0, 56) + "...";
                }
                System.out.printf("║%2d ║%8d ║ %-59s ║%n", 
                    i + 1, c.getDni(), nombreCompleto);
            }
            
            System.out.println("╚═══╩═════════╩══════════════════════════════════════════════════════════════════╝");
            
            // Mostrar información adicional en tabla separada
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            INFORMACIÓN ADICIONAL                               ║");
            System.out.println("╠═══╦══════════════╦═════════════════════════════════════════════════════════════╣");
            System.out.println("║ # ║   TELEFONO   ║                    DIRECCION                                ║");
            System.out.println("╠═══╬══════════════╬═════════════════════════════════════════════════════════════╣");
            
            for (int i = 0; i < clientes.size(); i++)
            {
                Cliente c = clientes.get(i);
                String direccionCompleta = c.getDireccion() + ", " + c.getLocalidad() + ", " + c.getProvincia();
                // Truncar si es muy largo
                if (direccionCompleta.length() > 53)
                {
                    direccionCompleta = direccionCompleta.substring(0, 50) + "...";
                }
                System.out.printf("║%2d ║%-13s ║ %-53s ║%n", 
                    i + 1, c.getTelefono(), direccionCompleta);
            }
            
            System.out.println("╚═══╩══════════════╩═════════════════════════════════════════════════════════════╝");
            
            while (true)
            {
                int seleccion = leerEntero("SELECCIONE UN CLIENTE (1-" + clientes.size() + ") o 0 para volver: ");
                
                if (seleccion == 0)
                {
                    return null;
                }
                
                if (seleccion >= 1 && seleccion <= clientes.size())
                {
                    Cliente clienteSeleccionado = clientes.get(seleccion - 1);
                    
                    // Mostrar confirmación
                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                    System.out.println("║                           CLIENTE SELECCIONADO                                 ║");
                    System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
                    System.out.printf("║ DNI:      %-68s ║%n", clienteSeleccionado.getDni());
                    System.out.printf("║ NOMBRE:   %-68s ║%n", clienteSeleccionado.getNombres());
                    System.out.printf("║ APELLIDO: %-68s ║%n", clienteSeleccionado.getApellidos());
                    System.out.printf("║ TELEFONO: %-68s ║%n", clienteSeleccionado.getTelefono());
                    System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                    
                    System.out.print("¿CONFIRMA LA SELECCION? (S/N): ");
                    String confirmacion = scanner.nextLine().toUpperCase();
                    
                    if (confirmacion.equals("S"))
                    {
                        return clienteSeleccionado;
                    }
                        else
                        {
                            return null; // Vuelve al menú anterior
                        }
                }
                    else
                    {
                        System.out.println("NUMERO INVALIDO. INTENTE NUEVAMENTE.");
                    }
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR AL MOSTRAR CLIENTES: " + e.getMessage());
            return null;
        }
    }

    // Método para seleccionar tipo de cliente (nuevo o existente)
    private static Cliente seleccionarTipoCliente()
    {
        try
        {
            while (true)
            {
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                             SELECCIONAR CLIENTE                                ║");
                System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
                System.out.println("║ [1] CLIENTE EXISTENTE                                                          ║");
                System.out.println("║ [2] NUEVO CLIENTE                                                              ║");
                System.out.println("║ [0] CANCELAR                                                                   ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                
                int opcion = leerEntero("SELECCIONE UNA OPCION: ");
                
                switch (opcion)
                {
                    case 0:
                        return null;
                    case 1:
                        return seleccionarCliente();
                    case 2:
                        return crearNuevoCliente();
                    default:
                        System.out.println("OPCION INVALIDA.");
                        continue;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR AL SELECCIONAR TIPO DE CLIENTE: " + e.getMessage());
            return null;
        }
    }

    // Método para crear un nuevo cliente
    private static Cliente crearNuevoCliente()
    {
        try
        {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                               NUEVO CLIENTE                                    ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            
            Cliente nuevoCliente = new Cliente();
            
            // Usar los métodos interactivos de la clase Cliente
            nuevoCliente.setDniInteractivo();
            nuevoCliente.setNombresInteractivo();
            nuevoCliente.setApellidosInteractivo();
            nuevoCliente.setTelefonoInteractivo();
            nuevoCliente.setDireccionInteractivo();
            nuevoCliente.setLocalidadInteractivo();
            nuevoCliente.setProvinciaInteractivo();
            nuevoCliente.setSexoInteractivo();
            nuevoCliente.setFechaNacimientoInteractivo();
            nuevoCliente.setActivo(true);
            
            // Guardar el cliente
            ArchivosCliente.guardarCliente(nuevoCliente);
            
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                          CLIENTE CREADO EXITOSAMENTE                           ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
            
            return nuevoCliente;
        }
        catch (Exception e)
        {
            System.out.println("ERROR AL CREAR NUEVO CLIENTE: " + e.getMessage());
            return null;
        }
    }

    // Método para mostrar el resumen de la venta antes de confirmar
    private static void mostrarResumenVenta(int codigo, LocalDate fecha, Cliente cliente, 
                                          Producto[] productos, int[] cantidades, int productosAgregados, 
                                          double total, FormaPago pago)
    {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              RESUMEN DE VENTA                                  ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ CODIGO VENTA: %-63d ║%n", codigo);
        System.out.printf("║ FECHA:        %-63s ║%n", fecha);
        System.out.printf("║ CLIENTE:      %-63s ║%n", cliente.getNombres() + " " + cliente.getApellidos());
        System.out.printf("║ FORMA PAGO:   %-63s ║%n", pago);
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                               PRODUCTOS                                        ║");
        System.out.println("╠════════╦═══════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  CANT  ║                        PRODUCTO                                       ║");
        System.out.println("╠════════╬═══════════════════════════════════════════════════════════════════════╣");
        
        for (int i = 0; i < productosAgregados; i++)
        {
            Producto p = productos[i];
            int cant = cantidades[i];
            String descripcion = p.getDetalle() + " - $" + p.getPrecio() + " c/u";
            if (descripcion.length() > 67)
            {
                descripcion = descripcion.substring(0, 64) + "...";
            }
            System.out.printf("║%7d ║ %-67s ║%n", cant, descripcion);
        }
        
        System.out.println("╠════════╩═══════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ TOTAL:        $%-63.2f ║%n", total);
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
    }

    // Método para mostrar el stock actualizado después de la venta
    private static void mostrarStockActualizado(Producto[] productos, int[] cantidades, int productosAgregados)
    {
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           STOCK ACTUALIZADO                                    ║");
        System.out.println("╠════════╦═══════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ CODIGO ║                    PRODUCTO - STOCK RESTANTE                          ║");
        System.out.println("╠════════╬═══════════════════════════════════════════════════════════════════════╣");
        
        for (int i = 0; i < productosAgregados; i++)
        {
            Producto p = productos[i];
            int cantVendida = cantidades[i];
            String info = p.getDetalle() + " - Stock: " + p.getStock() + " (vendidos: " + cantVendida + ")";
            if (info.length() > 67)
            {
                info = info.substring(0, 64) + "...";
            }
            System.out.printf("║%7d ║ %-67s ║%n", p.getCodigo(), info);
        }
        
        System.out.println("╚════════╩═══════════════════════════════════════════════════════════════════════╝");
    }

    // Método para generar código de venta automáticamente
    private static int generarCodigoVenta()
    {
        try
        {
            ArrayList<Venta> ventas = ArchivosVenta.leerVentas();
            int maxCodigo = 0;
            
            for (Venta v : ventas)
            {
                if (v.getCodigo() > maxCodigo)
                {
                    maxCodigo = v.getCodigo();
                }
            }
            
            return maxCodigo + 1;
        }
        catch (Exception e)
        {
            // Si hay error o no hay ventas, empezar desde 1001
            return 1001;
        }
    }

    // Método mejorado para mostrar productos disponibles con stock
    private static boolean mostrarProductosDisponiblesConStock()
    {
        try
        {
            ArrayList<Producto> productos = ArchivosProducto.leerProductos();
            
            if (productos.isEmpty())
            {
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                          NO HAY PRODUCTOS DISPONIBLES                         ║");
                System.out.println("╚════════════════════════════════════════════════════════════════════════════════╝");
                return false;
            }
            
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                            PRODUCTOS DISPONIBLES                              ║");
            System.out.println("╠════════╦═══════════════════════════════════════════╦════════╦═════════════════╣");
            System.out.println("║ CODIGO ║                 PRODUCTO                  ║ STOCK  ║     PRECIO      ║");
            System.out.println("╠════════╬═══════════════════════════════════════════╬════════╬═════════════════╣");
            
            boolean hayProductosDisponibles = false;
            
            for (Producto p : productos)
            {
                String producto = p.getDetalle() + " - " + p.getMarca();
                if (producto.length() > 39)
                {
                    producto = producto.substring(0, 36) + "...";
                }
                
                // Solo mostrar productos con stock > 0
                if (p.getStock() > 0)
                {
                    System.out.printf("║%7d ║ %-39s ║%7d ║ $%13.2f ║%n", 
                        p.getCodigo(), producto, p.getStock(), p.getPrecio());
                    hayProductosDisponibles = true;
                }
            }
            
            if (!hayProductosDisponibles)
            {
                System.out.println("║        ║                                       ║        ║                 ║");
                System.out.println("║        ║        NO HAY PRODUCTOS CON STOCK    ║        ║                 ║");
                System.out.println("║        ║                                       ║        ║                 ║");
            }
            
            System.out.println("╚════════╩═══════════════════════════════════════════╩════════╩═════════════════╝");
            
            return hayProductosDisponibles;
        }
        catch (Exception e)
        {
            System.out.println("ERROR AL MOSTRAR PRODUCTOS: " + e.getMessage());
            return false;
        }
    }
}