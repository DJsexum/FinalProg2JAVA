package Menus;

import Clases.Movimiento;
import Archivos.ArchivosMovimiento;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuMovimiento
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenu()
    {
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                  MOVIMIENTOS                  │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│        [1] ALTA DE MOVIMIENTO                 │");
            System.out.println("│        [2] LISTAR MOVIMIENTOS                 │");
            System.out.println("│        [3] BUSCAR MOVIMIENTO POR CODIGO       │");
            System.out.println("│        [4] MODIFICAR MOVIMIENTO               │");
            System.out.println("│        [5] BAJA DE MOVIMIENTO                 │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│        [0] VOLVER AL MENU PRINCIPAL           │");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");

            while (!scanner.hasNextInt())
            {
                System.out.print("\nINGRESE UN NUMERO VALIDO: ");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion)
            {
                case 1:
                    altaMovimiento();
                break;

                case 2:
                    listarMovimientos();
                break;

                case 3:
                    buscarMovimiento();
                break;

                case 4:
                    modificarMovimiento();
                break;

                case 5:
                    bajaMovimiento();
                break;

                case 0:
                    // Aca nomas sale al menu principal :)
                break;

                default:
                    System.out.println("OPCION INVALIDA. INTENTE DE NUEVO.\n");
            }
        }
        while (opcion != 0);
    }

    private static void altaMovimiento()
    {
        System.out.print("INGRESE CODIGO: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("INGRESE TIPO (DEBE/HABER): ");
        String tipo = scanner.nextLine().toUpperCase();
        System.out.print("INGRESE DETALLE: ");
        String detalle = scanner.nextLine();
        System.out.print("INGRESE MONTO DEBE: ");
        double montoDebe = Double.parseDouble(scanner.nextLine());
        System.out.print("INGRESE MONTO HABER: ");
        double montoHaber = Double.parseDouble(scanner.nextLine());
        System.out.print("INGRESE SALDO: ");
        double saldo = Double.parseDouble(scanner.nextLine());
        System.out.print("INGRESE FECHA (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        Movimiento movimiento = new Movimiento(codigo, tipo, detalle, montoDebe, montoHaber, saldo, fecha);
        ArchivosMovimiento.guardarMovimiento(movimiento);
        System.out.println("MOVIMIENTO GUARDADO CORRECTAMENTE.\n");
    }

    private static void listarMovimientos()
    {
        System.out.println("===== LISTA DE MOVIMIENTOS =====");
        for (Movimiento m : ArchivosMovimiento.leerMovimientos())
        {
            System.out.println(m);
        }
    }

    private static void buscarMovimiento()
    {
        System.out.print("INGRESE CODIGO DE MOVIMIENTO: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Movimiento m = ArchivosMovimiento.buscarMovimientoPorCodigo(codigo);
        if (m != null)
        {
            System.out.println(m);
        }
            else
            {
                System.out.println("NO SE ENCONTRO MOVIMIENTO CON ESE CODIGO.");
            }
    }

    private static void modificarMovimiento()
    {
        System.out.print("INGRESE CODIGO DE MOVIMIENTO A MODIFICAR: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        Movimiento m = ArchivosMovimiento.buscarMovimientoPorCodigo(codigo);
        if (m == null)
        {
            System.out.println("NO SE ENCONTRO MOVIMIENTO CON ESE CODIGO.");
            return;
        }

        int opcion;

        do
        {
            System.out.println("QUE DESEA MODIFICAR?");
            System.out.println("1. TIPO");
            System.out.println("2. DETALLE");
            System.out.println("3. MONTO DEBE");
            System.out.println("4. MONTO HABER");
            System.out.println("5. SALDO");
            System.out.println("6. FECHA");
            System.out.println("0. FINALIZAR MODIFICACION");
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
                    System.out.print("NUEVO TIPO (" + m.getTipo() + "): ");
                    String tipo = scanner.nextLine();
                    if (!tipo.isEmpty()) m.setTipo(tipo.toUpperCase());
                break;
                    
                case 2:
                    System.out.print("NUEVO DETALLE (" + m.getDetalle() + "): ");
                    String detalle = scanner.nextLine();
                    if (!detalle.isEmpty()) m.setDetalle(detalle);
                break;

                case 3:
                    System.out.print("NUEVO MONTO DEBE (" + m.getMontoDebe() + "): ");
                    String debe = scanner.nextLine();
                    if (!debe.isEmpty()) m.setMontoDebe(Double.parseDouble(debe));
                break;

                case 4:
                    System.out.print("NUEVO MONTO HABER (" + m.getMontoHaber() + "): ");
                    String haber = scanner.nextLine();
                    if (!haber.isEmpty()) m.setMontoHaber(Double.parseDouble(haber));
                break;

                case 5:
                    System.out.print("NUEVO SALDO (" + m.getSaldo() + "): ");
                    String saldo = scanner.nextLine();
                    if (!saldo.isEmpty()) m.setSaldo(Double.parseDouble(saldo));
                break;

                case 6:
                    System.out.print("NUEVA FECHA (" + m.getFechaMovimiento() + "): ");
                    String fecha = scanner.nextLine();
                    if (!fecha.isEmpty()) m.setFechaMovimiento(LocalDate.parse(fecha));
                break;

                case 0:
                    System.out.println("MODIFICACION FINALIZADA.");
                break;

                default:
                    System.out.println("OPCION INVALIDA.");
            }
        }
        while (opcion != 0);

        ArchivosMovimiento.modificarMovimiento(m);
        System.out.println("MOVIMIENTO MODIFICADO CORRECTAMENTE.");
    }

    private static void bajaMovimiento()
    {
        System.out.print("INGRESE CODIGO DE MOVIMIENTO A ELIMINAR: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        boolean eliminado = ArchivosMovimiento.eliminarMovimiento(codigo);
        if (eliminado)
        {
            System.out.println("MOVIMIENTO ELIMINADO CORRECTAMENTE.");
        }
            else
            {
                System.out.println("NO SE ENCONTRO MOVIMIENTO CON ESE CODIGO.");
            }
    }
}