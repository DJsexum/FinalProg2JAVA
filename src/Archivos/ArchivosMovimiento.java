package Archivos;

import Clases.Movimiento;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class ArchivosMovimiento
{
    private static final String ARCHIVO = "src/Datos/Movimientos.csv";

    /*
    Guarda un movimiento en el archivo CSV agregando una nueva línea al final.
    */
    public static void guardarMovimiento(Movimiento movimiento)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            bw.write
            (
                movimiento.getCodigo() + "," +
                movimiento.getTipo() + "," +
                movimiento.getDetalle() + "," +
                movimiento.getMontoDebe() + "," +
                movimiento.getMontoHaber() + "," +
                movimiento.getSaldo() + "," +
                (movimiento.getFechaMovimiento() != null ? movimiento.getFechaMovimiento() : "")
            );
            bw.newLine();
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR MOVIMIENTO: " + e.getMessage().toUpperCase());
            }
    }

    /*
    Elimina un movimiento del archivo por su código.
    Lee todos los movimientos, elimina el que coincide y vuelve a escribir la lista completa.
    @return true si se eliminó, false si no se encontró.
    */
    public static boolean eliminarMovimiento(int codigo)
    {
        ArrayList<Movimiento> lista = leerMovimientos();
        boolean eliminado = lista.removeIf(m -> m.getCodigo() == codigo);
        escribirLista(lista);
        return eliminado;
    }

    /*
    Modifica un movimiento existente en el archivo.
    Busca por código, reemplaza el movimiento y vuelve a escribir la lista completa.
    @return true si se modificó, false si no se encontró.
    */
    public static boolean modificarMovimiento(Movimiento movimiento)
    {
        ArrayList<Movimiento> lista = leerMovimientos();
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getCodigo() == movimiento.getCodigo())
            {
                lista.set(i, movimiento);
                escribirLista(lista);
                return true;
            }
        }
        return false;
    }

    /*
    Lee todos los movimientos del archivo CSV y los devuelve en una listita
    */
    public static ArrayList<Movimiento> leerMovimientos()
    {
        ArrayList<Movimiento> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",", -1);
                if (datos.length >= 7)
                {
                    Movimiento m = new Movimiento
                    (
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        Double.parseDouble(datos[3]),
                        Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5]),
                        datos[6].isEmpty() ? null : LocalDate.parse(datos[6])
                    );
                    lista.add(m);
                }
            }
        }
            catch (FileNotFoundException e)
            {
                // Si no existe el archivo, retorna lista vacía
            }
                catch (Exception e)
                {
                    System.out.println("ERROR AL LEER MOVIMIENTOS: " + e.getMessage().toUpperCase());
                }
                return lista;
    }

    /*
    Busca un movimiento por código en el archivo.
    Devuelve el movimiento si lo encuentra, o null si no existe.
    */
    public static Movimiento buscarMovimientoPorCodigo(int codigo)
    {
        ArrayList<Movimiento> lista = leerMovimientos();
        for (Movimiento m : lista)
        {
            if (m.getCodigo() == codigo)
            {
                return m;
            }
        }
        return null;
    }

    /*
    Escribe la lista completa de movimientos en el archivo CSV, sobrescribiendo el archivo.
    Se utiliza internamente para actualizar el archivo tras eliminar o modificar movimientos.
    */
    private static void escribirLista(ArrayList<Movimiento> lista)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, false)))
        {
            for (Movimiento movimiento : lista)
            {
                bw.write
                (
                    movimiento.getCodigo() + "," +
                    movimiento.getTipo() + "," +
                    movimiento.getDetalle() + "," +
                    movimiento.getMontoDebe() + "," +
                    movimiento.getMontoHaber() + "," +
                    movimiento.getSaldo() + "," +
                    (movimiento.getFechaMovimiento() != null ? movimiento.getFechaMovimiento() : "")
                );
                bw.newLine();
            }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR MOVIMIENTOS: " + e.getMessage().toUpperCase());
            }
    }
}