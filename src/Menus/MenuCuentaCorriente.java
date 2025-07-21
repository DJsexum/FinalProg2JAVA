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
        int num = scanner.nextInt();
        scanner.nextLine();
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
        int num = scanner.nextInt();
        scanner.nextLine();
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
        int num = scanner.nextInt();
        scanner.nextLine();
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
            return;
        }

        System.out.print("TIPO DE MOVIMIENTO (DEBE/HABER): ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.print("DETALLE: ");
        String detalle = scanner.nextLine();

        System.out.print("MONTO: ");
        double monto = scanner.nextDouble();
        scanner.nextLine();

        int codigo = cuentas.get(num - 1).getMovimientos().size() + 1; // Código simple incremental
        double montoDebe = tipo.equals("DEBE") ? monto : 0.0;
        double montoHaber = tipo.equals("HABER") ? monto : 0.0;
        double saldoAnterior = cuentas.get(num - 1).getSaldo();
        double saldoNuevo = tipo.equals("DEBE") ? saldoAnterior - monto : saldoAnterior + monto;

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

        cuentas.get(num - 1).agregarMovimiento(movimiento);
        ArchivosCuentaCorriente.guardarCuentas(cuentas);
        System.out.println("MOVIMIENTO AGREGADO Y GUARDADO EXITOSAMENTE.");
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
        int num = scanner.nextInt();
        scanner.nextLine();
        if (num < 1 || num > cuentas.size())
        {
            System.out.println("NUMERO DE CUENTA INVALIDO.");
            return;
        }
        CuentaCorriente cuenta = cuentas.get(num - 1);
        System.out.println("===== RESUMEN DE CUENTA =====");
        System.out.println("SALDO ACTUAL: " + cuenta.getSaldo());
        System.out.println("CANTIDAD DE MOVIMIENTOS: " + cuenta.getMovimientos().size());
        System.out.println("-----------------------------");
    }
}