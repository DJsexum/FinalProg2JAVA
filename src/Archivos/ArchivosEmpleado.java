package Archivos;

import Clases.Empleado;
import Enumerados.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/*
Clase utilitaria para gestionar la persistencia de empleados en un archivo CSV.
Permite guardar, leer, buscar, modificar y eliminar empleados.
*/
public class ArchivosEmpleado
{
    // Nombre del archivo donde se guardarán los empleados
    private static final String ARCHIVO = "src/Datos/Empleados.csv";

    /*
    Guarda un empleado en el archivo CSV (agrega al final del archivo).
    Cada empleado ocupa una línea, con sus datos separados por coma.
    */
    public static void guardarEmpleado(Empleado empleado)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true); // true = append
            BufferedWriter bw = new BufferedWriter(fw))
        {
            // Escribimos los datos del empleado separados por coma
            bw.write
            (
                empleado.getDni() + "," +
                empleado.getNombres() + "," +
                empleado.getApellidos() + "," +
                empleado.getTelefono() + "," +
                empleado.getDireccion() + "," +
                empleado.getLocalidad() + "," +
                empleado.getProvincia().name() + "," +
                empleado.getSexo().name() + "," +
                empleado.getFechaNacimiento() + "," +
                empleado.getFechaIngreso() + "," +
                (empleado.getFechaEgreso() != null ? empleado.getFechaEgreso() : "") + "," +
                empleado.getLegajo() + "," +
                empleado.getSalario()
            );
            bw.newLine(); // Salto de línea para el siguiente empleado
        }
            catch (IOException e)
            {
                System.out.println("ERROR AL GUARDAR EL EMPLEADO: " + e.getMessage());
            }
    }

    /*
    Lee todos los empleados del archivo CSV y los devuelve en una lista.
    Cada línea del archivo representa un empleado, cuyos datos se separan por coma.
    Si el archivo no existe, retorna una lista vacía.
    */
    public static ArrayList<Empleado> leerEmpleados()
    {
        ArrayList<Empleado> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            // Leemos línea por línea el archivo
            while ((linea = br.readLine()) != null)
            {
                // Separamos los datos por coma
                String[] datos = linea.split(",");
                // Creamos un nuevo empleado con los datos leídos
                Empleado e = new Empleado
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
                    LocalDate.parse(datos[9]), // Fecha ingreso
                    datos[10].isEmpty() ? null : LocalDate.parse(datos[10]), // Fecha egreso
                    Integer.parseInt(datos[11]), // Legajo
                    Double.parseDouble(datos[12]) // Salario
                );
                lista.add(e); // Agregamos el empleado a la lista
            }
        }
            catch (FileNotFoundException e)
            {
                // Si el archivo no existe, simplemente retorna la lista vacía
            }
                catch (IOException ex)
                {
                    System.out.println("ERROR AL LEER EMPLEADOS: " + ex.getMessage());
                }
                return lista;
    }

    /*
    Busca un empleado por legajo en el archivo.
    Lee todos los empleados y retorna el que tenga el legajo buscado, o null si no existe.
    */
    public static Empleado buscarPorLegajo(int legajo)
    {
        for (Empleado e : leerEmpleados())
        {
            if (e.getLegajo() == legajo)
            {
                return e;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    /*
    Busca un empleado por DNI en el archivo.
    Lee todos los empleados y retorna el que tenga el DNI buscado, o null si no existe.
    */
    public static Empleado buscarPorDni(int dni)
    {
        for (Empleado e : leerEmpleados())
        {
            if (e.getDni() == dni)
            {
                return e;
            }
        }
        return null; // Si no se encuentra, retorna null
    }

    /*
    Modifica los datos de un empleado existente en el archivo.
    Busca el empleado por legajo, lo reemplaza por el nuevo objeto y reescribe todo el archivo.
    */
    public static boolean modificarEmpleado(Empleado empleadoModificado)
    {
        ArrayList<Empleado> lista = leerEmpleados();
        boolean encontrado = false;
        // Recorremos la lista y reemplazamos el empleado con el mismo legajo
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getLegajo() == empleadoModificado.getLegajo())
            {
                lista.set(i, empleadoModificado);
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
    Elimina un empleado del archivo por legajo.
    Lee todos los empleados, elimina el que coincide y reescribe el archivo.
    */
    public static boolean eliminarEmpleado(int legajo)
    {
        ArrayList<Empleado> lista = leerEmpleados();
        // Usamos una expresión lambda para eliminar el empleado con el legajo especificado
        boolean eliminado = lista.removeIf(e -> e.getLegajo() == legajo);
        if (eliminado)
        {
            // Reescribimos todo el archivo con la lista actualizada
            escribirListaCompleta(lista);
        }
        return eliminado;
    }

    /*
    Método auxiliar privado para sobrescribir el archivo completo con una lista de empleados.
    Se usa en modificar y eliminar.
    */
    private static void escribirListaCompleta(ArrayList<Empleado> lista)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, false); // false = sobrescribir
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for (Empleado empleado : lista)
            {
                bw.write
                (
                    empleado.getDni() + "," +
                    empleado.getNombres() + "," +
                    empleado.getApellidos() + "," +
                    empleado.getTelefono() + "," +
                    empleado.getDireccion() + "," +
                    empleado.getLocalidad() + "," +
                    empleado.getProvincia().name() + "," +
                    empleado.getSexo().name() + "," +
                    empleado.getFechaNacimiento() + "," +
                    empleado.getFechaIngreso() + "," +
                    (empleado.getFechaEgreso() != null ? empleado.getFechaEgreso() : "") + "," +
                    empleado.getLegajo() + "," +
                    empleado.getSalario()
                );
                bw.newLine(); // Salto de línea para el siguiente empleado
            }
        }
            catch (IOException e)
            {
                System.out.println("ERROR AL ESCRIBIR EMPELADOS: " + e.getMessage());
            }
    }
}