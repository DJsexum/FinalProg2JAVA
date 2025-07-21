package Menus;

import Clases.*;
import Archivos.ArchivosCuentaCorriente;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCuentaCorriente
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│               CUENTA CORRIENTE                │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│      [1] LISTAR CUENTAS CORRIENTES            │");
            System.out.println("│      [2] VER MOVIMIENTOS DE UNA CUENTA        │");
            System.out.println("│      [3] CREAR NUEVA CUENTA CORRIENTE         │");
            System.out.println("│      [4] ELIMINAR CUENTA CORRIENTE            │");
            System.out.println("│      [5] AGREGAR MOVIMIENTO A UNA CUENTA      │");
            System.out.println("│      [6] MOSTRAR RESUMEN DE UNA CUENTA        │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│      [0] VOLVER                               │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt())
            {
                System.out.print("INGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion)
            {
                case 1:
                    listarCuentas();
                break;

                case 2:
                    verMovimientosCuenta();
                break;

                case 3:
                    crearCuenta();
                break;

                case 4:
                    eliminarCuenta();
                break;

                case 5:
                    agregarMovimientoCuenta();
                break;

                case 6:
                    mostrarResumenCuenta();
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

    private static void listarCuentas()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        if (cuentas.isEmpty())
        {
            System.out.println("NO HAY CUENTAS CORRIENTES REGISTRADAS.");
        }
            else
            {
                for (int i = 0; i < cuentas.size(); i++)
                {
                    System.out.println("CUENTA #" + (i + 1));
                    cuentas.get(i).verMovimientos();
                    System.out.println("-----------------------------");
                }
            }
    }

    private static void verMovimientosCuenta()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        if (cuentas.isEmpty())
        {
            System.out.println("NO HAY CUENTAS CORRIENTES REGISTRADAS.");
            return;
        }
        System.out.print("INGRESE EL NUMERO DE CUENTA (1 a " + cuentas.size() + "): ");
        int num = leerEntero();
        
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
        }
            else
            {
                cuentas.get(num - 1).verMovimientos();
            }
    }

    private static void crearCuenta()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        CuentaCorriente nueva = new CuentaCorriente();
        cuentas.add(nueva);
        ArchivosCuentaCorriente.guardarCuentas(cuentas);
        System.out.println("NUEVA CUENTA CORRIENTE CREADA EXITOSAMENTE.");
    }

    private static void eliminarCuenta()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        if (cuentas.isEmpty())
        {
            System.out.println("NO HAY CUENTAS CORRIENTES PARA ELIMINAR.");
            return;
        }
        System.out.print("INGRESE EL NUMERO DE CUENTA A ELIMINAR (1 a " + cuentas.size() + "): ");
        int num = leerEntero();
        
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
        }
            else
            {
                cuentas.remove(num - 1);
                ArchivosCuentaCorriente.guardarCuentas(cuentas);
                System.out.println("CUENTA CORRIENTE ELIMINADA EXITOSAMENTE.");
            }
    }

    // OPCION 5: Agregar movimiento a una cuenta y persistir el cambio
    private static void agregarMovimientoCuenta()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        if (cuentas.isEmpty())
        {
            System.out.println("NO HAY CUENTAS CORRIENTES REGISTRADAS.");
            return;
        }
        
        System.out.print("INGRESE EL NUMERO DE CUENTA (1 a " + cuentas.size() + "): ");
        int num = leerEntero();
        
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
            return;
        }

        // Usar métodos de validación
        String tipo = leerTipoMovimiento();
        String detalle = leerDetalle();
        double monto = leerMontoPositivo();
        
        CuentaCorriente cuentaSeleccionada = cuentas.get(num - 1);
        
        // Validar saldo suficiente para movimientos DEBE
        if (tipo.equals("DEBE") && cuentaSeleccionada.getSaldo() < monto)
        {
            System.out.println("SALDO INSUFICIENTE. SALDO ACTUAL: " + cuentaSeleccionada.getSaldo());
            return;
        }

        // Generar código único
        int codigo = generarCodigoMovimiento(cuentaSeleccionada);
        double montoDebe = tipo.equals("DEBE") ? monto : 0.0;
        double montoHaber = tipo.equals("HABER") ? monto : 0.0;
        double saldoAnterior = cuentaSeleccionada.getSaldo();
        double saldoNuevo = tipo.equals("DEBE") ? saldoAnterior - monto : saldoAnterior + monto;

        try
        {
            Movimiento movimiento = new Movimiento
            (
                codigo,
                tipo,
                detalle,
                montoDebe,
                montoHaber,
                saldoNuevo,
                java.time.LocalDate.now()
            );

            cuentaSeleccionada.agregarMovimiento(movimiento);
            ArchivosCuentaCorriente.guardarCuentas(cuentas);
            
            System.out.println("┌─────────────────────────────────────────────┐");
            System.out.println("│        MOVIMIENTO AGREGADO EXITOSAMENTE     │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│ TIPO: " + String.format("%-35s", tipo) + " │");
            System.out.println("│ MONTO: $" + String.format("%-32.2f", monto) + "  │");
            System.out.println("│ SALDO ANTERIOR: $" + String.format("%-23.2f", saldoAnterior) + "  │");
            System.out.println("│ SALDO NUEVO: $" + String.format("%-26.2f", saldoNuevo) + "  │");
            System.out.println("└─────────────────────────────────────────────┘");
        }
            catch (Exception e)
            {
                System.out.println("ERROR AL CREAR EL MOVIMIENTO: " + e.getMessage());
            }
    }

    // OPCION 6: Mostrar resumen de una cuenta
    private static void mostrarResumenCuenta()
    {
        ArrayList<CuentaCorriente> cuentas = ArchivosCuentaCorriente.leerCuentas();
        if (cuentas.isEmpty())
        {
            System.out.println("NO HAY CUENTAS CORRIENTES REGISTRADAS.");
            return;
        }
        System.out.print("INGRESE EL NUMERO DE CUENTA (1 a " + cuentas.size() + "): ");
        int num = leerEntero();
        
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
            return;
        }
        
        CuentaCorriente cuenta = cuentas.get(num - 1);
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│              RESUMEN DE CUENTA              │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.printf("│ SALDO ACTUAL: $%-28.2f │%n", cuenta.getSaldo());
        System.out.printf("│ MOVIMIENTOS: %-30d │%n", cuenta.getMovimientos().size());
        System.out.println("└─────────────────────────────────────────────┘");
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero()
    {
        while (!scanner.hasNextInt())
        {
            System.out.print("INGRESE UN NUMERO VALIDO: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    // Método auxiliar para leer números decimales de forma segura
    private static double leerDouble()
    {
        while (!scanner.hasNextDouble())
        {
            System.out.print("INGRESE UN NUMERO VALIDO: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    // Método auxiliar para validar tipo de movimiento
    private static String leerTipoMovimiento()
    {
        String tipo;
        do
        {
            System.out.print("TIPO DE MOVIMIENTO (DEBE/HABER): ");
            tipo = scanner.nextLine().trim().toUpperCase();
            
            if (!tipo.equals("DEBE") && !tipo.equals("HABER"))
            {
                System.out.println("TIPO INVALIDO. INGRESE 'DEBE' O 'HABER'.");
            }
        }
        while (!tipo.equals("DEBE") && !tipo.equals("HABER"));
        
        return tipo;
    }

    // Método auxiliar para leer detalle no vacío
    private static String leerDetalle()
    {
        String detalle;
        do
        {
            System.out.print("DETALLE: ");
            detalle = scanner.nextLine().trim();
            
            if (detalle.isEmpty())
            {
                System.out.println("EL DETALLE NO PUEDE ESTAR VACIO.");
            }
        }
        while (detalle.isEmpty());
        
        return detalle;
    }

    // Método auxiliar para leer monto positivo
    private static double leerMontoPositivo()
    {
        double monto;
        do
        {
            System.out.print("MONTO: ");
            monto = leerDouble();
            
            if (monto <= 0)
            {
                System.out.println("EL MONTO DEBE SER MAYOR A CERO.");
            }
        } while (monto <= 0);
        
        return monto;
    }

    // Método auxiliar para generar código único de movimiento
    private static int generarCodigoMovimiento(CuentaCorriente cuenta)
    {
        int maxCodigo = 0;
        for (Movimiento mov : cuenta.getMovimientos())
        {
            if (mov.getCodigo() > maxCodigo)
            {
                maxCodigo = mov.getCodigo();
            }
        }
        return maxCodigo + 1;
    }
}