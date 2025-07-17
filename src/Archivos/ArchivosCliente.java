package Archivos;

import Clases.Cliente;
import Enumerados.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/*
Clase utilitaria para gestionar la persistencia de clientes en un archivo CSV.
Permite guardar, leer, buscar, modificar y eliminar clientes.
*/
public class ArchivosCliente
{
    // Nombre del archivo donde se guardarán los clientes
    private static final String ARCHIVO = "clientes.csv";

    /*
    Guarda un cliente en el archivo CSV.
    Abre el archivo en modo append (agregar al final) y escribe los datos del cliente separados por coma.
    Cada cliente ocupa una línea en el archivo.
    */
    public static void guardarCliente(Cliente cliente)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true); // true = append
            BufferedWriter bw = new BufferedWriter(fw))
        {
            // Escribimos los datos del cliente en mayúsculas y sin acentos
            bw.write
            (
                cliente.getDni() + "," +
                quitarAcentos(cliente.getNombres().toUpperCase()) + "," +
                quitarAcentos(cliente.getApellidos().toUpperCase()) + "," +
                quitarAcentos(cliente.getTelefono().toUpperCase()) + "," +
                quitarAcentos(cliente.getDireccion().toUpperCase()) + "," +
                quitarAcentos(cliente.getLocalidad().toUpperCase()) + "," +
                cliente.getProvincia().name().toUpperCase() + "," +
                cliente.getSexo().name().toUpperCase() + "," +
                cliente.getFechaNacimiento()
            );
            bw.newLine(); // Salto de línea para el siguiente cliente
        }
            catch (IOException e)
            {
                System.out.println("ERROR AL GUARDAR CLIENTE: " + e.getMessage());
            }
    }

    /*
    Lee todos los clientes del archivo CSV y los devuelve en una lista
    Si el archivo no existe, retorna una lista vacía
    */
    public static ArrayList<Cliente> leerClientes()
    {
        ArrayList<Cliente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            // Leemos línea por línea el archivo
            while ((linea = br.readLine()) != null)
            {
                // Separamos los datos por coma
                String[] datos = linea.split(",");
                // Creamos un nuevo cliente con los datos leídos
                Cliente c = new Cliente
                (
                    Integer.parseInt(datos[0]), // DNI
                    true, // Activo (por defecto true)
                    datos[1], // Nombres
                    datos[2], // Apellidos
                    datos[3], // Teléfono
                    datos[4], // Dirección
                    datos[5], // Localidad
                    Provincia.valueOf(datos[6]), // Provincia
                    Sexo.valueOf(datos[7]), // Sexo
                    LocalDate.parse(datos[8]), // Fecha de nacimiento
                    null // Cuenta corriente (no se guarda aca)
                );
                lista.add(c); // Agregamos el cliente a la lista
            }
        }
            catch (FileNotFoundException e)
            {
                // Si el archivo no existe, simplemente retorna la lista vacía
            }
                catch (IOException e)
                {
                    System.out.println("ERROR AL LEER CLIENTES: " + e.getMessage());
                }
                return lista;
    }

    /*
    Busca un cliente por DNI en el archivo.
    Lee todos los clientes y retorna el que tenga el DNI buscado, o null si no existe.
    */
    public static Cliente buscarPorDni(int dni)
    {
        for (Cliente c : leerClientes())
        {
            if (c.getDni() == dni)
            {
                return c;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    /*
    Modifica los datos de un cliente existente en el archivo.
    Busca el cliente por DNI, lo reemplaza por el nuevo objeto y reescribe todo el archivo.
    */
    public static boolean modificarCliente(Cliente clienteModificado)
    {
        ArrayList<Cliente> lista = leerClientes();
        boolean encontrado = false;

        // Recorremos la lista y reemplazamos el cliente con el mismo DNI
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getDni() == clienteModificado.getDni())
            {
                lista.set(i, clienteModificado);
                encontrado = true;
                break;
            }
        }

        if (encontrado)
        {
            // Reescribimos todo el archivo con la lista actualizada
            escribirListaCompleta(lista);
        }
        return encontrado;
    }

    /*
    Elimina un cliente del archivo por DNI.
    Lee todos los clientes, elimina el que coincide y reescribe el archivo.
    */
    public static boolean eliminarCliente(int dni)
    {
        ArrayList<Cliente> lista = leerClientes();
        boolean eliminado = lista.removeIf(c -> c.getDni() == dni);

        if (eliminado)
        {
            // Reescribimos todo el archivo con la lista actualizada
            escribirListaCompleta(lista);
        }
        return eliminado;
    }

    /*
    Método auxiliar privado para sobrescribir el archivo completo con una lista de clientes.
    Se usa en modificar y eliminar.
    */
    private static void escribirListaCompleta(ArrayList<Cliente> lista)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, false); // false = sobrescribir
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for (Cliente cliente : lista)
            {
                bw.write
                (
                    cliente.getDni() + "," +
                    quitarAcentos(cliente.getNombres().toUpperCase()) + "," +
                    quitarAcentos(cliente.getApellidos().toUpperCase()) + "," +
                    quitarAcentos(cliente.getTelefono().toUpperCase()) + "," +
                    quitarAcentos(cliente.getDireccion().toUpperCase()) + "," +
                    quitarAcentos(cliente.getLocalidad().toUpperCase()) + "," +
                    cliente.getProvincia().name().toUpperCase() + "," +
                    cliente.getSexo().name().toUpperCase() + "," +
                    cliente.getFechaNacimiento()
                );
                bw.newLine();
            }
        }
            catch (IOException e)
            {
                System.out.println("ERROR AL ESCRIBIR CLIENTES: " + e.getMessage());
            }
    }

    
    // Método utilitario para quitar solo acentos (no modifica ñ/Ñ)
    private static String quitarAcentos(String texto)
{
    if (texto == null) return null;
    return texto
        .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
        .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
}
}