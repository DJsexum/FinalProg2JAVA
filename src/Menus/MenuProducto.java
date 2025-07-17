package Menus;

import Clases.*;
import Enumerados.*;
import Archivos.ArchivosProducto;
import java.util.ArrayList;
import java.util.Scanner;
// Tengo que admitir me costo una banda, y hubo chatgpt en lo del menu ese que hice raro (copie mas o menos como lo hice en el fixture (el verdadero que fixture era ese))
public class MenuProducto
{
    // Scanner unico y estatico para toda la clase
    private static final Scanner scanner = new Scanner(System.in);

    // Muestra el menu de productos y ejecuta la opcion seleccionada.
    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("===== MENU DE PRODUCTOS =====");
            System.out.println("1. ALTA DE PRODUCTO");
            System.out.println("2. LISTAR PRODUCTOS");
            System.out.println("3. BUSCAR PRODUCTO POR CODIGO");
            System.out.println("4. MODIFICAR PRODUCTO");
            System.out.println("5. BAJA DE PRODUCTO");
            System.out.println("6. BUSCAR PRODUCTO POR NOMBRE/MARCA/CATEGORIA");
            System.out.println("0. VOLVER AL MENU PRINCIPAL");
            System.out.print("SELECCIONE UNA OPCION: ");

            // Validacion para que la opcion sea un numero
            while (!scanner.hasNextInt())
            {
                System.out.print("INGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion)
            {
                case 1:
                {
                    // Alta de producto con opcione para poder cancelar en cualquier momento (es cualquier cosa)
                    System.out.print("INGRESE CODIGO DE PRODUCTO (0 PARA CANCELAR): ");
                    int codigoAlta = leerEntero();
                    if (codigoAlta == 0)
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                        if (Producto.buscarProducto(codigoAlta) != null)
                        {
                            System.out.println("YA EXISTE UN PRODUCTO CON ESE CODIGO.");
                            break;
                        }
                    // Validar campos obligatorios y permitir cancelar en cualquier momento (la misma cagada que arriba)
                    String detalle = leerCampoObligatorio("DETALLE (0 PARA CANCELAR): ");
                    if (detalle == null) break;
                    String talle = leerCampoObligatorio("TALLE (0 PARA CANCELAR): ");
                    if (talle == null) break;
                    Double precio = leerDoubleObligatorio("PRECIO (0 PARA CANCELAR): ");
                    if (precio == null) break;
                    String marca = leerCampoObligatorio("MARCA (0 PARA CANCELAR): ");
                    if (marca == null) break;
                    String material = leerCampoObligatorio("MATERIAL (0 PARA CANCELAR): ");
                    if (material == null) break;
                    String categoriaStr = leerCampoObligatorio("CATEGORIA (0 PARA CANCELAR): ");
                    if (categoriaStr == null) break;
                    Integer stock = leerEnteroObligatorio("STOCK (0 PARA CANCELAR): ");
                    if (stock == null) break;

                    // Mostrar resumen y pedir confirmacion antes de guardar (me zarpe con esto)
                    System.out.println("\nRESUMEN DEL PRODUCTO A GUARDAR:");
                    System.out.println("CODIGO: " + codigoAlta);
                    System.out.println("DETALLE: " + detalle);
                    System.out.println("TALLE: " + talle);
                    System.out.println("PRECIO: " + precio);
                    System.out.println("MARCA: " + marca);
                    System.out.println("MATERIAL: " + material);
                    System.out.println("CATEGORIA: " + categoriaStr);
                    System.out.println("STOCK: " + stock);
                    System.out.print("¿CONFIRMA ALTA? (S/N): ");
                    String confAlta = scanner.nextLine().toUpperCase();
                    if (!confAlta.equals("S"))
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                        try
                        {
                            Producto nuevoProducto = new Producto
                            (
                                codigoAlta,
                                detalle,
                                talle,
                                precio,
                                marca,
                                material,
                                Categoria.valueOf(categoriaStr.toUpperCase()),
                                stock
                            );
                            // Guardar producto usando el metodo de la clase Producto
                            ArchivosProducto.guardarProducto(nuevoProducto);
                            System.out.println("PRODUCTO GUARDADO CORRECTAMENTE.");
                        }
                            catch (Exception e)
                            {
                                System.out.println("ERROR AL GUARDAR EL PRODUCTO: " + e.getMessage().toUpperCase());
                            }
                break;
                }

                case 2:
                {
                    // Lista de los productos en forma de tablita (no se si anda pero eligo creer)
                    try
                    {
                        ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                        if (lista.isEmpty())
                        {
                            System.out.println("NO HAY PRODUCTOS REGISTRADOS.");
                        }
                            else
                            {
                                System.out.println("---------------------------------------------------------------------------------------------");
                                System.out.printf("%-8s %-20s %-8s %-10s %-15s %-12s %-12s %-8s\n",
                                "CODIGO", "DETALLE", "TALLE", "PRECIO", "MARCA", "MATERIAL", "CATEGORIA", "STOCK");
                                System.out.println("---------------------------------------------------------------------------------------------");
                                // Filas de productos
                                for (Producto p : lista)
                                {
                                    System.out.printf
                                    ("%-8d %-20s %-8s %-10.2f %-15s %-12s %-12s %-8d\n",
                                        p.getCodigo(), p.getDetalle(), p.getTalle(), p.getPrecio(),
                                        p.getMarca(), p.getMaterial(), p.getCategoria(), p.getStock()
                                    );
                                }
                                System.out.println("---------------------------------------------------------------------------------------------");
                            }
                    }
                        catch (Exception e)
                        {
                            System.out.println("ERROR AL LISTAR PRODUCTOS: " + e.getMessage().toUpperCase());
                        }
                break;
                }

                case 3:
                {
                    // BUSCAR PRODUCTO POR CODIGO
                    System.out.print("INGRESE CODIGO DE PRODUCTO (0 PARA CANCELAR): ");
                    int codigoBuscar = leerEntero();
                    if (codigoBuscar == 0)
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                    Producto.buscarProducto(codigoBuscar);
                break;
                }

                case 4:
                {
                    // MODIFICAR PRODUCTO CON CONTROL Y OPCION DE CANCELAR
                    System.out.print("INGRESE CODIGO DE PRODUCTO A MODIFICAR (0 PARA CANCELAR): ");
                    int codigoMod = leerEntero();
                    if (codigoMod == 0)
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                    Producto productoMod = Producto.buscarProducto(codigoMod);
                    if (productoMod != null)
                    {
                        productoMod.verDetalleProducto();
                        System.out.print("¿CONFIRMA MODIFICACION? (S/N): ");
                        String confMod = scanner.nextLine().toUpperCase();
                        if (confMod.equals("S"))
                        {
                            // Validar campos obligatorios y permitir cancelar en cualquier paso
                            String detalle = leerCampoObligatorio("NUEVO DETALLE (0 PARA CANCELAR): ");
                            if (detalle == null) break;
                            String talle = leerCampoObligatorio("NUEVO TALLE (0 PARA CANCELAR): ");
                            if (talle == null) break;
                            Double precio = leerDoubleObligatorio("NUEVO PRECIO (0 PARA CANCELAR): ");
                            if (precio == null) break;
                            String marca = leerCampoObligatorio("NUEVA MARCA (0 PARA CANCELAR): ");
                            if (marca == null) break;
                            String material = leerCampoObligatorio("NUEVO MATERIAL (0 PARA CANCELAR): ");
                            if (material == null) break;
                            String categoriaStr = leerCampoObligatorio("NUEVA CATEGORIA (0 PARA CANCELAR): ");
                            if (categoriaStr == null) break;
                            Integer stock = leerEnteroObligatorio("NUEVO STOCK (0 PARA CANCELAR): ");
                            if (stock == null) break;

                            // Mostrar resumen y pedir confirmacion antes de modificar
                            System.out.println("\nRESUMEN DE LA MODIFICACION:");
                            System.out.println("CODIGO: " + codigoMod);
                            System.out.println("DETALLE: " + detalle);
                            System.out.println("TALLE: " + talle);
                            System.out.println("PRECIO: " + precio);
                            System.out.println("MARCA: " + marca);
                            System.out.println("MATERIAL: " + material);
                            System.out.println("CATEGORIA: " + categoriaStr);
                            System.out.println("STOCK: " + stock);
                            System.out.print("¿CONFIRMA GUARDAR CAMBIOS? (S/N): ");
                            String confGuardar = scanner.nextLine().toUpperCase();
                            if (!confGuardar.equals("S"))
                            {
                                System.out.println("OPERACION CANCELADA.");
                                break;
                            }
                                try
                                {
                                    productoMod.setDetalle(detalle);
                                    productoMod.setTalle(talle);
                                    productoMod.setPrecio(precio);
                                    productoMod.setMarca(marca);
                                    productoMod.setMaterial(material);
                                    productoMod.setCategoria(Categoria.valueOf(categoriaStr.toUpperCase()));
                                    productoMod.setStock(stock);
                                    boolean modificado = ArchivosProducto.modificarProducto(productoMod);
                                    if (modificado)
                                    {
                                        System.out.println("PRODUCTO MODIFICADO CORRECTAMENTE.");
                                    }
                                        else
                                        {
                                            System.out.println("NO SE PUDO MODIFICAR EL PRODUCTO.");
                                        }
                                }
                                    catch (Exception e)
                                    {
                                        System.out.println("ERROR AL MODIFICAR EL PRODUCTO: " + e.getMessage().toUpperCase());
                                    }
                        }
                            else
                            {
                                System.out.println("OPERACION CANCELADA.");
                            }
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO PRODUCTO CON ESE CODIGO.");
                        }
                break;
                }

                case 5:
                {
                    // BAJA DE PRODUCTO CON CONFIRMACION Y OPCION DE CANCELAR
                    System.out.print("INGRESE CODIGO DE PRODUCTO A ELIMINAR (0 PARA CANCELAR): ");
                    int codigoBaja = leerEntero();
                    if (codigoBaja == 0)
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                    Producto productoBaja = Producto.buscarProducto(codigoBaja);
                    if (productoBaja != null)
                    {
                        productoBaja.verDetalleProducto();
                        System.out.print("¿CONFIRMA ELIMINACION? (S/N): ");
                        String confBaja = scanner.nextLine().toUpperCase();
                        if (confBaja.equals("S"))
                        {
                            try
                            {
                                productoBaja.bajaProducto();
                            }
                                catch (Exception e)
                                {
                                    System.out.println("ERROR AL ELIMINAR EL PRODUCTO: " + e.getMessage().toUpperCase());
                                }
                        }
                            else
                            {
                                System.out.println("OPERACION CANCELADA.");
                            }
                    }
                        else
                        {
                            System.out.println("NO SE ENCONTRO PRODUCTO CON ESE CODIGO.");
                        }
                break;
                }

                case 6:
                {
                    // BUSQUEDA POR NOMBRE, MARCA O CATEGORIA
                    System.out.println("BUSQUEDA AVANZADA DE PRODUCTOS");
                    System.out.print("INGRESE TEXTO A BUSCAR (DETALLE, MARCA O CATEGORIA, 0 PARA CANCELAR): ");
                    String texto = scanner.nextLine().trim();
                    if (texto.equals("0"))
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                        try
                        {
                            ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                            boolean encontrado = false;
                            // Buscar coincidencias en detalle, marca o categoria (ignorando mayusculas/minusculas)
                            for (Producto p : lista)
                            {
                                if (p.getDetalle().toUpperCase().contains(texto.toUpperCase()) ||
                                p.getMarca().toUpperCase().contains(texto.toUpperCase()) ||
                                p.getCategoria().toString().toUpperCase().contains(texto.toUpperCase()))
                                {
                                    if (!encontrado)
                                    {
                                        System.out.println("PRODUCTOS ENCONTRADOS:");
                                        System.out.println("---------------------------------------------------------------------------------------------");
                                        System.out.printf("%-8s %-20s %-8s %-10s %-15s %-12s %-12s %-8s\n",
                                            "CODIGO", "DETALLE", "TALLE", "PRECIO", "MARCA", "MATERIAL", "CATEGORIA", "STOCK");
                                        System.out.println("---------------------------------------------------------------------------------------------");
                                        encontrado = true;
                                    }
                                    System.out.printf("%-8d %-20s %-8s %-10.2f %-15s %-12s %-12s %-8d\n",
                                    p.getCodigo(), p.getDetalle(), p.getTalle(), p.getPrecio(),
                                    p.getMarca(), p.getMaterial(), p.getCategoria(), p.getStock());
                                }
                            }
                                if (!encontrado)
                                {
                                    System.out.println("NO SE ENCONTRARON PRODUCTOS CON ESE CRITERIO.");
                                }
                                    else
                                    {
                                        System.out.println("---------------------------------------------------------------------------------------------");
                                    }
                        }
                            catch (Exception e)
                            {
                                System.out.println("ERROR EN LA BUSQUEDA: " + e.getMessage().toUpperCase());
                            }
                break;
                }

                case 0:
                {
                    System.out.println("VOLVIENDO AL MENU PRINCIPAL...");
                    break;
                }

                default:
                {
                    System.out.println("OPCION NO VALIDA.");
                }
            }
        }
        while (opcion != 0);
    }

    // Metodo auxiliar para leer un entero con validacion y permitir cancelar con 0
    // Devuelve el entero ingresado o 0 si el usuario cancela
    private static int leerEntero()
    {
        while (true)
        {
            String input = scanner.nextLine();
            try
            {
                return Integer.parseInt(input);
            }
                catch (NumberFormatException e)
                {
                    System.out.print("INGRESE UN NUMERO VALIDO: ");
                }
        }
    }

    // Metodo auxiliar para leer un campo obligatorio de texto, permite cancelar con 0
    // Devuelve el texto ingresado o null si el usuario cancela
    private static String leerCampoObligatorio(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            if (input.equals("0"))
            {
                System.out.println("OPERACION CANCELADA.");
                return null;
            }
            if (input.trim().isEmpty())
            {
                System.out.println("EL CAMPO NO PUEDE ESTAR VACIO.");
            }
                else
                {
                    return input;
                }
        }
    }

    // Metodo auxiliar para leer un double obligatorio, permite cancelar con 0
    // Devuelve el double ingresado o null si el usuario cancela
    private static Double leerDoubleObligatorio(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            if (input.equals("0"))
            {
                System.out.println("OPERACION CANCELADA.");
                return null;
            }
                try
                {
                    double valor = Double.parseDouble(input);
                    return valor;
                }
                    catch (NumberFormatException e)
                    {
                        System.out.println("INGRESE UN NUMERO VALIDO.");
                    }
        }
    }

    // Metodo auxiliar para leer un entero obligatorio, permite cancelar con 0
    // Devuelve el entero ingresado o null si el usuario cancela
    private static Integer leerEnteroObligatorio(String mensaje)
    {
        while (true)
        {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            if (input.equals("0"))
            {
                System.out.println("OPERACION CANCELADA.");
                return null;
            }
                try
                {
                    int valor = Integer.parseInt(input);
                    return valor;
                }
                    catch (NumberFormatException e)
                    {
                        System.out.println("INGRESE UN NUMERO VALIDO.");
                    }
        }
    }
}