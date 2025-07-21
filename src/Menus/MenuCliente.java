package Menus;

import java.util.Scanner;
import java.util.ArrayList;
import Clases.Cliente;
import Clases.CuentaCorriente;
import Clases.Movimiento;
import Enumerados.Provincia;
import Archivos.ArchivosCliente;

public class MenuCliente
{
    /*
    Muestra el menú de clientes y ejecuta la opción seleccionada.
    Es un método estático para poder llamarlo desde MenuPrincipal sin crear instancias.
    */
    public static void mostrarMenu()
    {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do
        {
            System.out.println("\n┌───────────────────────────────────────────────┐");
            System.out.println("│                   CLIENTES                    │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│           [1] ALTA DE CLIENTE                 │");
            System.out.println("│           [2] LISTAR CLIENTES                 │");
            System.out.println("│           [3] BUSCAR CLIENTE POR DNI          │");
            System.out.println("│           [4] BUSCAR CLIENTES POR PROVINCIA   │");
            System.out.println("│           [5] MODIFICAR CLIENTE               │");
            System.out.println("│           [6] BAJA DE CLIENTE                 │");
            System.out.println("│           [7] VER CUENTAS CORRIENTES          │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.println("│           [0] VOLVER AL MENU PRINCIPAL        │");
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
                    new Cliente().altaPersona();
                break;

                case 2:
                    new Cliente().listarPersona();
                break;

                case 3:
                    System.out.print("INGRESE DNI: ");
                    int dni = leerEntero(scanner);
                    new Cliente().buscaPersona(dni);
                break;

                case 4:
                    buscarClientesPorProvincia(scanner);
                break;

                case 5:
                    new Cliente().modificarPersona();
                break;

                case 6:
                    new Cliente().bajaPersona();
                break;

                case 7:
                    verCuentasCorrientes(scanner);
                break;

                case 0:
                    // Aca nomas sale al menu principal :)
                break;

                default:
                    System.out.println("OPCION INVALIDA. INTENTE DE NUEVO: ");
            }
        }
        while (opcion != 0);
    }

    // Método auxiliar para leer enteros de forma segura
    private static int leerEntero(Scanner scanner)
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

    // Método para buscar clientes por provincia
    private static void buscarClientesPorProvincia(Scanner scanner)
    {
        System.out.println("\n    === SELECCIONAR PROVINCIA ===");
        System.out.println("┌─────┬──────────────────────────────┐");
        System.out.println("│ COD │           PROVINCIA          │");
        System.out.println("├─────┼──────────────────────────────┤");
        
        // Mostrar todas las provincias
        for (Provincia provincia : Provincia.values()) 
        {
            System.out.printf("│ %2d  │ %-28s │%n", provincia.getCodigo(), provincia.getNombre());
        }
        System.out.println("└─────┴──────────────────────────────┘");
        
        System.out.print("INGRESE EL CODIGO DE LA PROVINCIA: ");
        int codigoProvincia = leerEntero(scanner);
        
        // Buscar la provincia por código
        Provincia provinciaSeleccionada = null;
        for (Provincia provincia : Provincia.values()) 
        {
            if (provincia.getCodigo() == codigoProvincia) 
            {
                provinciaSeleccionada = provincia;
                break;
            }
        }
        
        if (provinciaSeleccionada != null) 
        {
            Cliente.buscarClientesPorProvincia(provinciaSeleccionada);
        } 
            else 
            {
                System.out.println("CODIGO DE PROVINCIA INVALIDO.");
            }
    }

    // Método para ver todas las cuentas corrientes
    private static void verCuentasCorrientes(Scanner scanner)
    {
        ArrayList<Cliente> clientes = ArchivosCliente.leerClientes();
        ArrayList<Cliente> clientesConCuenta = new ArrayList<>();
        
        // Filtrar clientes que tienen cuenta corriente
        for (Cliente cliente : clientes)
        {
            if (cliente.getCtaCte() != null)
            {
                clientesConCuenta.add(cliente);
            }
        }
        
        if (clientesConCuenta.isEmpty())
        {
            System.out.println("\nNO HAY CLIENTES CON CUENTA CORRIENTE REGISTRADOS.");
            return;
        }
        
        // Mostrar vista general
        mostrarVistaGeneralCuentas(clientesConCuenta);
        
        // Menú de opciones
        int opcion;
        do
        {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│           OPCIONES DISPONIBLES              │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│        [1] VER DETALLE DE UNA CUENTA        │");
            System.out.println("│        [2] FILTRAR POR SALDO POSITIVO       │");
            System.out.println("│        [3] FILTRAR POR SALDO NEGATIVO       │");
            System.out.println("│        [4] ORDENAR POR SALDO                │");
            System.out.println("├─────────────────────────────────────────────┤");
            System.out.println("│        [0] VOLVER AL MENU ANTERIOR          │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.print("SELECCIONE UNA OPCION: ");
            
            opcion = leerEntero(scanner);
            
            switch (opcion)
            {
                case 1:
                    verDetalleCuenta(clientesConCuenta, scanner);
                    break;
                case 2:
                    filtrarPorSaldoPositivo(clientesConCuenta);
                    break;
                case 3:
                    filtrarPorSaldoNegativo(clientesConCuenta);
                    break;
                case 4:
                    ordenarPorSaldo(clientesConCuenta);
                    break;
                case 0:
                    System.out.println("REGRESANDO AL MENU ANTERIOR...");
                    break;
                default:
                    System.out.println("OPCION INVALIDA. INTENTE DE NUEVO.");
            }
        } while (opcion != 0);
    }

    // Mostrar vista general de todas las cuentas corrientes
    private static void mostrarVistaGeneralCuentas(ArrayList<Cliente> clientesConCuenta)
    {
        System.out.println("\n┌───────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                        CUENTAS CORRIENTES                                             │");
        System.out.println("├────────────────────────────┬──────────┬─────────────┬────────┬────────────────────────────────────────┤");
        System.out.println("│ CLIENTE                    │   DNI    │    SALDO    │ MOVIMS │ ÚLTIMO MOVIMIENTO                      │");
        System.out.println("├────────────────────────────┼──────────┼─────────────┼────────┼────────────────────────────────────────┤");
        
        double totalPositivo = 0.0;
        double totalNegativo = 0.0;
        
        for (Cliente cliente : clientesConCuenta)
        {
            CuentaCorriente cuenta = cliente.getCtaCte();
            String nombreCompleto = cliente.getNombres() + " " + cliente.getApellidos();
            if (nombreCompleto.length() > 26) 
            {
                nombreCompleto = nombreCompleto.substring(0, 23) + "...";
            }
            
            String saldoStr = String.format("$%.2f", cuenta.getSaldo());
            String saldoColor = cuenta.getSaldo() >= 0 ? saldoStr : saldoStr; // Para futuras mejoras de color
            
            // Obtener último movimiento
            String ultimoMovimiento = "Sin movimientos";
            if (!cuenta.getMovimientos().isEmpty()) 
            {
                Movimiento ultimo = cuenta.getMovimientos().get(cuenta.getMovimientos().size() - 1);
                String tipo = ultimo.getTipo();
                double monto = tipo.equals("DEBE") ? ultimo.getMontoDebe() : ultimo.getMontoHaber();
                String signo = tipo.equals("DEBE") ? "-" : "+";
                ultimoMovimiento = ultimo.getFechaMovimiento().getDayOfMonth() + "/" + 
                                 ultimo.getFechaMovimiento().getMonthValue() + " - " + 
                                 tipo + ": " + signo + "$" + String.format("%.0f", monto);
                if (ultimoMovimiento.length() > 38) 
                {
                    ultimoMovimiento = ultimoMovimiento.substring(0, 35) + "...";
                }
            }
            
            // Acumular totales
            if (cuenta.getSaldo() >= 0) 
            {
                totalPositivo += cuenta.getSaldo();
            } 
                else 
                {
                    totalNegativo += cuenta.getSaldo();
                }
            
            System.out.printf("│ %-26s │ %8d │ %11s │ %6d │ %-38s │%n", 
                nombreCompleto, cliente.getDni(), saldoColor, 
                cuenta.getMovimientos().size(), ultimoMovimiento);
        }
        
        System.out.println("└────────────────────────────┴──────────┴─────────────┴────────┴────────────────────────────────────────┘");
        
        // Mostrar resumen
        System.out.println("\nRESUMEN GENERAL:");
        System.out.println("• Total clientes con cuenta corriente: " + clientesConCuenta.size());
        System.out.printf("• Saldo total positivo: $%.2f%n", totalPositivo);
        System.out.printf("• Saldo total negativo: $%.2f%n", totalNegativo);
        System.out.printf("• Balance general: $%.2f%n", totalPositivo + totalNegativo);
    }

    // Ver detalle de una cuenta específica
    private static void verDetalleCuenta(ArrayList<Cliente> clientesConCuenta, Scanner scanner)
    {
        System.out.println("\n=== SELECCIONAR CLIENTE ===");
        for (int i = 0; i < clientesConCuenta.size(); i++)
        {
            Cliente cliente = clientesConCuenta.get(i);
            System.out.printf("[%d] %s %s (DNI: %d) - Saldo: $%.2f%n", 
                (i + 1), cliente.getNombres(), cliente.getApellidos(), 
                cliente.getDni(), cliente.getCtaCte().getSaldo());
        }
        
        System.out.print("SELECCIONE UN CLIENTE (0 para cancelar): ");
        int seleccion = leerEntero(scanner);
        
        if (seleccion == 0) {
            return;
        }
        
        if (seleccion < 1 || seleccion > clientesConCuenta.size()) {
            System.out.println("OPCION INVALIDA.");
            return;
        }
        
        Cliente clienteSeleccionado = clientesConCuenta.get(seleccion - 1);
        mostrarDetalleCuentaCorriente(clienteSeleccionado);
    }

    // Mostrar detalle completo de cuenta corriente con SALDO FINAL
    private static void mostrarDetalleCuentaCorriente(Cliente cliente)
    {
        CuentaCorriente cuenta = cliente.getCtaCte();
        
        System.out.println("\n┌─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│                                           DETALLE CUENTA CORRIENTE                                                         │");
        System.out.printf("│                                              %-30s                                                │%n", 
            (cliente.getNombres() + " " + cliente.getApellidos()).toUpperCase());
        System.out.println("├─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.printf("│ DNI: %-50s SALDO ACTUAL: $%-30.2f │%n", cliente.getDni(), cuenta.getSaldo());
        System.out.println("├─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤");
        System.out.println("│ FECHA  │ TIPO  │        DETALLE         │   DEBE    │   HABER   │   SALDO   │  CÓDIGO  │    SALDO FINAL    │");
        System.out.println("├────────┼───────┼────────────────────────┼───────────┼───────────┼───────────┼──────────┼───────────────────┤");
        
        double totalDebe = 0.0;
        double totalHaber = 0.0;
        
        for (Movimiento mov : cuenta.getMovimientos())
        {
            String detalle = mov.getDetalle();
            if (detalle.length() > 22) 
            {
                detalle = detalle.substring(0, 19) + "...";
            }
            
            totalDebe += mov.getMontoDebe();
            totalHaber += mov.getMontoHaber();
            
            // Calcular saldo final (HABER - DEBE acumulado hasta este movimiento)
            double saldoFinal = totalHaber - totalDebe;
            
            System.out.printf("│ %02d/%02d  │ %-5s │ %-22s │ %9.2f │ %9.2f │ %9.2f │ %8d │ %17.2f │%n",
                mov.getFechaMovimiento().getDayOfMonth(),
                mov.getFechaMovimiento().getMonthValue(),
                mov.getTipo(),
                detalle,
                mov.getMontoDebe(),
                mov.getMontoHaber(),
                mov.getSaldo(),
                mov.getCodigo(),
                saldoFinal
            );
        }
        
        // Calcular saldo final total
        double saldoFinalTotal = totalHaber - totalDebe;
        
        System.out.println("├────────┴───────┴────────────────────────┼───────────┼───────────┼───────────┴──────────┼───────────────────┤");
        System.out.printf("│                               TOTALES:  │ %9.2f │ %9.2f │                      │ %17.2f │%n", 
            totalDebe, totalHaber, saldoFinalTotal);
        System.out.println("└──────────────────────────────────────────┴───────────┴───────────┴──────────────────────┴───────────────────┘");
        
        // Mensaje de estado
        if (saldoFinalTotal > 0) 
        {
            System.out.printf("%nESTADO: SALDO A FAVOR - $%.2f%n", saldoFinalTotal);
        } 
            else if (saldoFinalTotal < 0) 
            {
                System.out.printf("%nESTADO: DEBE DINERO - $%.2f%n", Math.abs(saldoFinalTotal));
            } 
                else 
                {
                    System.out.println("\nESTADO: CUENTA BALANCEADA - $0.00");
                }
    }

    // Métodos auxiliares para filtros y ordenamiento
    private static void filtrarPorSaldoPositivo(ArrayList<Cliente> clientesConCuenta)
    {
        ArrayList<Cliente> positivos = new ArrayList<>();
        for (Cliente cliente : clientesConCuenta) 
        {
            if (cliente.getCtaCte().getSaldo() >= 0) 
            {
                positivos.add(cliente);
            }
        }
        
        if (positivos.isEmpty()) 
        {
            System.out.println("\nNO HAY CLIENTES CON SALDO POSITIVO.");
        } 
            else 
            {
                System.out.println("\n=== CLIENTES CON SALDO POSITIVO ===");
                mostrarVistaGeneralCuentas(positivos);
            }
    }

    private static void filtrarPorSaldoNegativo(ArrayList<Cliente> clientesConCuenta)
    {
        ArrayList<Cliente> negativos = new ArrayList<>();
        for (Cliente cliente : clientesConCuenta) 
        {
            if (cliente.getCtaCte().getSaldo() < 0) 
            {
                negativos.add(cliente);
            }
        }
        
        if (negativos.isEmpty()) 
        {
            System.out.println("\nNO HAY CLIENTES CON SALDO NEGATIVO.");
        } 
            else 
            {
                System.out.println("\n=== CLIENTES CON SALDO NEGATIVO (DEBEN DINERO) ===");
                mostrarVistaGeneralCuentas(negativos);
            }
    }

    private static void ordenarPorSaldo(ArrayList<Cliente> clientesConCuenta)
    {
        // Ordenar por saldo de mayor a menor
        clientesConCuenta.sort((c1, c2) -> Double.compare(c2.getCtaCte().getSaldo(), c1.getCtaCte().getSaldo()));
        
        System.out.println("\n=== CLIENTES ORDENADOS POR SALDO (MAYOR A MENOR) ===");
        mostrarVistaGeneralCuentas(clientesConCuenta);
    }
}