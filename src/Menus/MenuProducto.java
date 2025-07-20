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
                System.out.print("INGRESE UNA OPCION VALIDA: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion)
            {
                case 1:
                {
                    // Alta de producto con opcione para poder cancelar en cualquier momento (es cualquier cosa pero lo dejo por las dudas)
                    System.out.print("INGRESE CODIGO DE PRODUCTO (0 PARA CANCELAR): ");
                    int codigoAlta = leerEntero();
                    if (codigoAlta == 0)
                    {
                        System.out.println("OPERACION CANCELADA.");
                        break;
                    }
                        if (Producto.buscarProducto(codigoAlta) != null)
                        {
                            System.out.println("YA EXISTE UN PRODUCTO CON ESE CODIGO.\n");
                            break;
                        }
                    // Validar campos obligatorios y permite cancelar en cualquier momento
                    String detalle = leerCampoObligatorio("DETALLE: ");
                    String talle = leerCampoObligatorio("TALLE: ");
                    Double precio = leerDoubleObligatorio("PRECIO: ");
                    String marca = leerCampoObligatorio("MARCA: ");
                    String material = leerCampoObligatorio("MATERIAL: ");
                    Categoria categoria = Categoria.seleccionarCategoria();
                    Integer stock = leerEnteroObligatorio("STOCK: ");

                    System.out.println("\nRESUMEN DEL PRODUCTO CREADO:");
                    System.out.println("CODIGO: " + codigoAlta);
                    System.out.println("DETALLE: " + detalle);
                    System.out.println("TALLE: " + talle);
                    System.out.println("PRECIO: " + precio);
                    System.out.println("MARCA: " + marca);
                    System.out.println("MATERIAL: " + material);
                    System.out.println("CATEGORIA: " + categoria);
                    System.out.println("STOCK: " + stock);
                    System.out.print("\n¿CONFIRMA CREACION DEL PRODUCTO? (S/N): ");
                    String confAlta = scanner.nextLine().toUpperCase();
                    if (!confAlta.equals("S"))
                    {
                        System.out.println("\nOPERACION CANCELADA.\n");
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
                                categoria,
                                stock
                            );
                            // Guardar producto usando el metodo de la clase Producto
                            ArchivosProducto.guardarProducto(nuevoProducto);
                            System.out.println("PRODUCTO GUARDADO CORRECTAMENTE.\n");
                        }
                            catch (Exception e)
                            {
                                System.out.println("\nERROR AL GUARDAR EL PRODUCTO: " + e.getMessage().toUpperCase());
                            }
                break;
                }

                case 2:
                {
                    // LISTAR PRODUCTOS EN FORMATO DE TABLA
                    try
                    {
                        ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                        Producto.mostrarListaProductosEnTabla(lista);
                    }
                        catch (Exception e)
                        {
                            System.out.println("\nERROR AL LISTAR PRODUCTOS: " + e.getMessage().toUpperCase());
                        }
                break;
                }

                case 3:
                {
                    // BUSCAR PRODUCTO POR CODIGO
                    try
                    {
                        // Primero mostrar todos los productos disponibles
                        ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                        if (lista.isEmpty())
                        {
                            System.out.println("NO HAY PRODUCTOS REGISTRADOS.\n");
                            break;
                        }
                        
                        System.out.println("PRODUCTOS DISPONIBLES:");
                        Producto.mostrarListaProductosEnTabla(lista);
                        
                        System.out.print("\nINGRESE CODIGO DE PRODUCTO A BUSCAR (0 PARA CANCELAR): ");
                        int codigoBuscar = leerEntero();
                        if (codigoBuscar == 0)
                        {
                            System.out.println("OPERACION CANCELADA.\n");
                            break;
                        }
                        
                        Producto encontrado = Producto.buscarProducto(codigoBuscar);
                        System.out.println("\nDETALLE DEL PRODUCTO SELECCIONADO:");
                        Producto.mostrarProductoEnTabla(encontrado);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ERROR AL MOSTRAR PRODUCTOS: " + e.getMessage().toUpperCase());
                    }
                break;
                }

                case 4:
                {
                    // MODIFICAR PRODUCTO CON ELECCION DE COSA A MODIFICAR
                    try
                    {
                        // Primero mostrar todos los productos disponibles
                        ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                        if (lista.isEmpty())
                        {
                            System.out.println("NO HAY PRODUCTOS REGISTRADOS.\n");
                            break;
                        }
                        
                        System.out.println("PRODUCTOS DISPONIBLES:");
                        Producto.mostrarListaProductosEnTabla(lista);
                        
                        System.out.print("\nINGRESE CODIGO DE PRODUCTO A MODIFICAR (0 PARA CANCELAR): ");
                        int codigoMod = leerEntero();
                        if (codigoMod == 0)
                        {
                            System.out.println("OPERACION CANCELADA.\n");
                            break;
                        }
                        
                        Producto productoMod = Producto.buscarProducto(codigoMod);
                        if (productoMod != null)
                        {
                            System.out.println("\nPRODUCTO SELECCIONADO PARA MODIFICAR:");
                            Producto.mostrarProductoEnTabla(productoMod);
                        System.out.print("¿CONFIRMA MODIFICACION? (S/N): ");
                        String confMod = scanner.nextLine().toUpperCase();
                        if (confMod.equals("S"))
                        {
                            // MENU DE SELECCION DE CAMPO A MODIFICAR
                            boolean continuar = true;
                            while (continuar)
                            {
                                System.out.println("\n" + "=".repeat(50));
                                System.out.println("SELECCIONE QUE CAMPO DESEA MODIFICAR:");
                                System.out.println("=".repeat(50));
                                System.out.println("1. DETALLE");
                                System.out.println("2. TALLE");
                                System.out.println("3. PRECIO");
                                System.out.println("4. MARCA");
                                System.out.println("5. MATERIAL");
                                System.out.println("6. CATEGORIA");
                                System.out.println("7. STOCK");
                                System.out.println("8. MODIFICAR TODO EL PRODUCTO");
                                System.out.println("0. SALIR SIN MODIFICAR");
                                System.out.println("=".repeat(50));
                                System.out.print("OPCION: ");
                                
                                int opcionCampo = leerEntero();
                                boolean modificacionExitosa = false;
                                
                                switch (opcionCampo)
                                {
                                    case 1: // MODIFICAR DETALLE
                                        String nuevoDetalle = leerCampoObligatorio("NUEVO DETALLE: ");
                                        try 
                                        {
                                            productoMod.setDetalle(nuevoDetalle);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("DETALLE MODIFICADO CORRECTAMENTE.");
                                            }
                                                else 
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR EL DETALLE.");
                                                }
                                        } 
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR EL DETALLE: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 2: // MODIFICAR TALLE
                                        String nuevoTalle = leerCampoObligatorio("NUEVO TALLE: ");
                                        try 
                                        {
                                            productoMod.setTalle(nuevoTalle);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("TALLE MODIFICADO CORRECTAMENTE.");
                                            } 
                                                else
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR EL TALLE.");
                                                }
                                        } 
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR EL TALLE: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 3: // MODIFICAR PRECIO
                                        Double nuevoPrecio = leerDoubleObligatorio("NUEVO PRECIO: ");
                                        try 
                                        {
                                            productoMod.setPrecio(nuevoPrecio);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("PRECIO MODIFICADO CORRECTAMENTE.");
                                            } 
                                                else 
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR EL PRECIO.");
                                                }
                                        } 
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR EL PRECIO: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 4: // MODIFICAR MARCA
                                        String nuevaMarca = leerCampoObligatorio("NUEVA MARCA: ");
                                        try 
                                        {
                                            productoMod.setMarca(nuevaMarca);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("MARCA MODIFICADA CORRECTAMENTE.");
                                            } 
                                                else 
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR LA MARCA.");
                                                }
                                        }
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR LA MARCA: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 5: // MODIFICAR MATERIAL
                                        String nuevoMaterial = leerCampoObligatorio("NUEVO MATERIAL: ");
                                        try 
                                        {
                                            productoMod.setMaterial(nuevoMaterial);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("MATERIAL MODIFICADO CORRECTAMENTE.");
                                            }
                                                else 
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR EL MATERIAL.");
                                                }
                                        }
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR EL MATERIAL: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 6: // MODIFICAR CATEGORIA
                                        System.out.println("\nSELECCIONE LA NUEVA CATEGORIA:");
                                        Categoria nuevaCategoria = Categoria.seleccionarCategoria();
                                        try
                                        {
                                            productoMod.setCategoria(nuevaCategoria);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa) 
                                            {
                                                System.out.println("CATEGORIA MODIFICADA CORRECTAMENTE.");
                                            } 
                                                else 
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR LA CATEGORIA.");
                                                }
                                        }
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR LA CATEGORIA: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 7: // MODIFICAR STOCK
                                        Integer nuevoStock = leerEnteroObligatorio("NUEVO STOCK: ");
                                        try 
                                        {
                                            productoMod.setStock(nuevoStock);
                                            modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                            if (modificacionExitosa)
                                            {
                                                System.out.println("STOCK MODIFICADO CORRECTAMENTE.");
                                            } 
                                                else
                                                {
                                                    System.out.println("NO SE PUDO MODIFICAR EL STOCK.");
                                                }
                                        }
                                            catch (Exception e) 
                                            {
                                                System.out.println("ERROR AL MODIFICAR EL STOCK: " + e.getMessage().toUpperCase());
                                            }
                                    break;
                                        
                                    case 8: // MODIFICAR EL PRODUCTO COMPLETO 
                                        String detalle = leerCampoObligatorio("NUEVO DETALLE: ");
                                        String talle = leerCampoObligatorio("NUEVO TALLE: ");
                                        Double precio = leerDoubleObligatorio("NUEVO PRECIO: ");
                                        String marca = leerCampoObligatorio("NUEVA MARCA: ");
                                        String material = leerCampoObligatorio("NUEVO MATERIAL: ");
                                        System.out.println("\nSELECCIONE LA NUEVA CATEGORIA:");
                                        Categoria categoria = Categoria.seleccionarCategoria();
                                        Integer stock = leerEnteroObligatorio("NUEVO STOCK: ");

                                        // Mostrar resumen y pedir confirmacion antes de modificar
                                        System.out.println("\nRESUMEN DE LA MODIFICACION COMPLETA:");
                                        System.out.println("CODIGO: " + codigoMod);
                                        System.out.println("DETALLE: " + detalle);
                                        System.out.println("TALLE: " + talle);
                                        System.out.println("PRECIO: " + precio);
                                        System.out.println("MARCA: " + marca);
                                        System.out.println("MATERIAL: " + material);
                                        System.out.println("CATEGORIA: " + categoria);
                                        System.out.println("STOCK: " + stock);
                                        System.out.print("¿CONFIRMA GUARDAR TODOS LOS CAMBIOS? (S/N): ");
                                        String confGuardar = scanner.nextLine().toUpperCase();
                                        if (confGuardar.equals("S"))
                                        {
                                            try 
                                            {
                                                productoMod.setDetalle(detalle);
                                                productoMod.setTalle(talle);
                                                productoMod.setPrecio(precio);
                                                productoMod.setMarca(marca);
                                                productoMod.setMaterial(material);
                                                productoMod.setCategoria(categoria);
                                                productoMod.setStock(stock);
                                                modificacionExitosa = ArchivosProducto.modificarProducto(productoMod);
                                                if (modificacionExitosa) 
                                                {
                                                    System.out.println("PRODUCTO MODIFICADO COMPLETAMENTE.");
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
                                                System.out.println("MODIFICACION COMPLETA CANCELADA.");
                                            }
                                    break;
                                        
                                    case 0: // SALIR
                                        System.out.println("SALIENDO SIN MODIFICAR...");
                                        continuar = false;
                                    break;
                                        
                                    default:
                                        System.out.println("OPCION INVALIDA. INTENTE NUEVAMENTE.");
                                    break;
                                }
                                
                                // Preguntar si quiere modificar otro campo (excepto si eligio salir)
                                if (opcionCampo != 0 && modificacionExitosa)
                                {
                                    System.out.print("\n¿DESEA MODIFICAR OTRO CAMPO DEL MISMO PRODUCTO? (S/N): ");
                                    String seguir = scanner.nextLine().toUpperCase();
                                    if (!seguir.equals("S"))
                                    {
                                        continuar = false;
                                    }
                                }
                                else if (opcionCampo != 0 && !modificacionExitosa)
                                {
                                    System.out.print("\n¿DESEA INTENTAR OTRA MODIFICACION? (S/N): ");
                                    String reintentar = scanner.nextLine().toUpperCase();
                                    if (!reintentar.equals("S"))
                                    {
                                        continuar = false;
                                    }
                                }
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
                    }
                        catch (Exception e)
                        {
                            System.out.println("ERROR AL MOSTRAR PRODUCTOS: " + e.getMessage().toUpperCase());
                        }
                break;
                }

                case 5:
                {
                    // BAJA DE PRODUCTO CON CONFIRMACION Y OPCION DE CANCELAR
                    try
                    {
                        // Primero mostrar todos los productos disponibles
                        ArrayList<Producto> lista = ArchivosProducto.leerProductos();
                        if (lista.isEmpty())
                        {
                            System.out.println("NO HAY PRODUCTOS REGISTRADOS.\n");
                            break;
                        }
                        
                        System.out.println("PRODUCTOS DISPONIBLES:");
                        Producto.mostrarListaProductosEnTabla(lista);
                        
                        System.out.print("\nINGRESE CODIGO DE PRODUCTO A ELIMINAR (0 PARA CANCELAR): ");
                        int codigoBaja = leerEntero();
                        if (codigoBaja == 0)
                        {
                            System.out.println("OPERACION CANCELADA.");
                            break;
                        }
                        
                        Producto productoBaja = Producto.buscarProducto(codigoBaja);
                        if (productoBaja != null)
                        {
                            System.out.println("\nPRODUCTO SELECCIONADO PARA ELIMINAR:");
                            Producto.mostrarProductoEnTabla(productoBaja);
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
                    }
                        catch (Exception e)
                        {
                            System.out.println("ERROR AL MOSTRAR PRODUCTOS: " + e.getMessage().toUpperCase());
                        }
                break;
                }

                case 6:
                {
                    // BUSQUEDA AVANZADA POR DETALLE, MARCA O CATEGORIA
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
                            ArrayList<Producto> encontrados = new ArrayList<>();
                            
                            // Buscar coincidencias en detalle, marca o categoria (ignorando mayusculas/minusculas)
                            for (Producto p : lista)
                            {
                                if (p.getDetalle().toUpperCase().contains(texto.toUpperCase()) ||
                                p.getMarca().toUpperCase().contains(texto.toUpperCase()) ||
                                p.getCategoria().toString().toUpperCase().contains(texto.toUpperCase()))
                                {
                                    encontrados.add(p);
                                }
                            }
                            
                            if (encontrados.isEmpty())
                            {
                                System.out.println("NO SE ENCONTRARON PRODUCTOS CON ESE CRITERIO.");
                            }
                                else
                                {
                                    System.out.println("PRODUCTOS ENCONTRADOS:");
                                    Producto.mostrarListaProductosEnTabla(encontrados);
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
                    System.out.print("INGRESE UN VALOR VALIDO: ");
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
                        System.out.println("INGRESE UN VALOR VALIDO.");
                    }
        }
    }

    // Metodo auxiliar para leer un entero obligatorio, permite cancelar con 0
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
                        System.out.println("INGRESE UN VALOR VALIDO.");
                    }
        }
    }
}