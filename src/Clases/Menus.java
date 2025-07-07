package Clases;

import java.time.LocalDate;
import java.util.Scanner;

public class Menus {
    private Cliente cliente;
    private Producto producto;
    private CuentaCorriente cuentaCorriente;
    private Movimiento movimiento;
    private Proveedor proveedor;
    private Venta venta;
    private Usuario usuario;
    private Producto[] productos;
    private Persona persona;
    private Scanner scanner = new Scanner(System.in);

    public Menus() {
        mostrarMenuPrincipal();
        // Constructor de la clase Menus
    }

    private void mostrarMenuPrincipal() {
        // Lógica para mostrar el menú principal
        System.out.println("Bienvenido al sistema de gestión.");
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Clientes");
            System.out.println("2. Productos");
            System.out.println("3. Cuentas Corrientes");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();

            switch (opcion)
            {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    mostrarMenuProductos();
                    break;
                case 3:
                    mostrarMenuCuentasCorrientes();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private void mostrarMenuClientes()
    {
        // Lógica para mostrar el menú de clientes
        System.out.println("Menú de Clientes:");
        System.out.println("1. Ver Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Modificar Cliente");

        int opcion = scanner.nextInt();

        switch (opcion) {
                case 1:
                    mostrarMenuClientes();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    mostrarMenuCuentasCorrientes();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
    }

    private void mostrarMenuProductos()
    {
        // Lógica para mostrar el menú de productos
        System.out.println("Menú de Productos:");
        System.out.println("1. Ver Productos");
        System.out.println("2. Agregar Producto");
        System.out.println("3. Modificar producto");
        System.out.println("4. Eliminar Producto");

        int opcion = scanner.nextInt();

    }

    private void mostrarMenuCuentasCorrientes() {
        // Lógica para mostrar el menú de cuentas corrientes
    }

    private void agregarCliente() {
        cliente = new Cliente();
        // Lógica para agregar un cliente
        System.out.println("Ingrese los datos del nuevo cliente:");
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        cliente.setDni(dni);
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        cliente.setNombres(nombre);
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        cliente.setApellidos(apellido);
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        cliente.setTelefono(telefono);
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        cliente.setDireccion(direccion);
        System.out.print("Localidad: ");
        String localidad = scanner.nextLine();
        cliente.setLocalidad(localidad);
        System.out.print("Provincia (seleccione por código): ");
        cliente.setProvincia();
        System.out.print("Sexo: ");
        cliente.setSexo(); // Llama al método para seleccionar el sexo desde la consola\
        System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
        String fechaNacimientoStr = scanner.nextLine();
        // Convertir la fecha de nacimiento a LocalDate
        cliente.setFechaNacimiento(LocalDate.parse(fechaNacimientoStr));
        cliente.setActivo(true); // Por defecto, el cliente está activo

        
        System.out.println("Cliente agregado exitosamente.");
        cliente.VerResumenCliente();
    }

}
