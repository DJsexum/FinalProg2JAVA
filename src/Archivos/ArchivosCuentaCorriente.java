package Archivos;

import Clases.CuentaCorriente;
import Clases.Movimiento;
import java.util.ArrayList;
import java.io.*;

public class ArchivosCuentaCorriente
{
    // Nombre del archivo CSV donde se guardan las cuentas corrientes
    private static final String ARCHIVO = "cuentas_corrientes.csv";

    /*
    Guarda todas las cuentas corrientes y sus movimientos en el archivo CSV.
    Cada movimiento de cada cuenta se guarda como una línea en el archivo.
    */
    public static void guardarCuentas(ArrayList<CuentaCorriente> cuentas)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, false)))
        {
            // Recorre todas las cuentas corrientes
            for (CuentaCorriente cuenta : cuentas)
            {
                // Recorre todos los movimientos de la cuenta
                for (Movimiento movimiento : cuenta.getMovimientos())
                {
                    // Escribe los datos del movimiento separados por coma
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
                    bw.newLine(); // Salto de linea
                }
            }
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL GUARDAR CUENTAS CORRIENTES: " + e.getMessage().toUpperCase());
            }
    }

    /*
    Lee todas las cuentas corrientes y sus movimientos desde el archivo CSV.
    Cada línea del archivo representa un movimiento, que se agrega a una cuenta corriente
    */
    public static ArrayList<CuentaCorriente> leerCuentas()
    {
        ArrayList<CuentaCorriente> cuentas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            CuentaCorriente cuenta = new CuentaCorriente();
            // Lee cada línea del archivo (cada movimiento)
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",", -1);
                if (datos.length >= 7)
                {
                    // Crea un nuevo movimiento a partir de los datos leídos
                    Movimiento m = new Movimiento
                    (
                        Integer.parseInt(datos[0]),
                        datos[1],
                        datos[2],
                        Double.parseDouble(datos[3]),
                        Double.parseDouble(datos[4]),
                        Double.parseDouble(datos[5]),
                        datos[6].isEmpty() ? null : java.time.LocalDate.parse(datos[6])
                    );
                    // Agrega el movimiento a la cuenta corriente
                    cuenta.agregarMovimiento(m);
                }
            }
            // Si la cuenta tiene movimientos, la agrega a la lista de cuentas
            if (!cuenta.getMovimientos().isEmpty())
                cuentas.add(cuenta);
        }
            catch (FileNotFoundException e)
            {
                // Si no existe el archivo, retorna lista vacía
            }
                catch (Exception e)
                {
                    System.out.println("ERROR AL LEER CUENTAS CORRIENTES: " + e.getMessage().toUpperCase());
                }
                return cuentas;
    }
}