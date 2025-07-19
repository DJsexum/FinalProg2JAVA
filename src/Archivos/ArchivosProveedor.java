package Archivos;

import Clases.Proveedor;
import Enumerados.*;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class ArchivosProveedor
{

    private static final String ARCHIVO = "src/Datos/proveedores.csv";

    /*
    Guarda un proveedor en el archivo CSV agregando una nueva línea al final.
    Cada campo del proveedor se separa por coma.
    */
    public static void guardarProveedor(Proveedor proveedor)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write
            (
                proveedor.getDni() + "," +
                proveedor.getNombres() + "," +
                proveedor.getApellidos() + "," +
                proveedor.getTelefono() + "," +
                proveedor.getDireccion() + "," +
                proveedor.getLocalidad() + "," +
                (proveedor.getProvincia() != null ? proveedor.getProvincia().name() : "") + "," +
                (proveedor.getSexo() != null ? proveedor.getSexo().name() : "") + "," +
                (proveedor.getFechaNacimiento() != null ? proveedor.getFechaNacimiento() : "")
            );
            bw.newLine();
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR PROVEEDOR: " + e.getMessage().toUpperCase());
            }
    }

    /*
    Elimina un proveedor del archivo por su DNI.
    Lee todos los proveedores, elimina el que coincide y vuelve a escribir la lista completa.
    return true si se eliminó, false si no se encontró.
    */
    public static boolean eliminarProveedor(int dni)
    {
        ArrayList<Proveedor> lista = leerProveedores();
        boolean eliminado = lista.removeIf(p -> p.getDni() == dni);
        escribirLista(lista);
        return eliminado;
    }

    /*
    Modifica un proveedor existente en el archivo.
    Busca por DNI, reemplaza el proveedor y vuelve a escribir la lista completa.
    return true si se modificó, false si no se encontró.
    */
    public static boolean modificarProveedor(Proveedor proveedor)
    {
        ArrayList<Proveedor> lista = leerProveedores();
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getDni() == proveedor.getDni())
            {
                lista.set(i, proveedor);
                escribirLista(lista);
                return true;
            }
        }
        return false;
    }

    /*
    Lee todos los proveedores del archivo CSV y los devuelve en una lista.
    Cada línea se convierte en un objeto Proveedor.
    */
    public static ArrayList<Proveedor> leerProveedores()
    {
        ArrayList<Proveedor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",", -1);
                if (datos.length >= 9)
                {
                    Proveedor p = new Proveedor
                    (
                        Integer.parseInt(datos[0]),
                        true,
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4],
                        datos[5],
                        datos[6].isEmpty() ? null : Provincia.valueOf(datos[6]),
                        datos[7].isEmpty() ? null : Sexo.valueOf(datos[7]),
                        datos[8].isEmpty() ? null : LocalDate.parse(datos[8]),
                        null
                    );
                    lista.add(p);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            // Si no existe el archivo, retorna lista vacía
        }
        catch (Exception e)
        {
            System.out.println("ERROR AL LEER PROVEEDORES: " + e.getMessage().toUpperCase());
        }
        return lista;
    }

    /*
    Busca un proveedor por DNI en el archivo.
    Devuelve el proveedor si lo encuentra, o null si no existe.
    */
    public static Proveedor buscarProveedorPorDni(int dni)
    {
        ArrayList<Proveedor> lista = leerProveedores();
        for (Proveedor p : lista)
        {
            if (p.getDni() == dni)
            {
                return p;
            }
        }
        return null;
    }

    /*
    Escribe la lista completa de proveedores en el archivo CSV, sobrescribiendo el archivo.
    Se utiliza internamente para actualizar el archivo tras eliminar o modificar proveedores.
    */
    private static void escribirLista(ArrayList<Proveedor> lista)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, false)))
        {
            for (Proveedor proveedor : lista)
            {
                bw.write
                (
                    proveedor.getDni() + "," +
                    proveedor.getNombres() + "," +
                    proveedor.getApellidos() + "," +
                    proveedor.getTelefono() + "," +
                    proveedor.getDireccion() + "," +
                    proveedor.getLocalidad() + "," +
                    (proveedor.getProvincia() != null ? proveedor.getProvincia().name() : "") + "," +
                    (proveedor.getSexo() != null ? proveedor.getSexo().name() : "") + "," +
                    (proveedor.getFechaNacimiento() != null ? proveedor.getFechaNacimiento() : "")
                );
                bw.newLine();
            }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR PROVEEDORES: " + e.getMessage().toUpperCase());
            }
    }
}