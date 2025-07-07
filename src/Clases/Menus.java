package Clases;

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
        
        cliente.setDni();
        
        
        cliente.setNombres();
        
        cliente.setApellidos();
        
        cliente.setTelefono();
        
        cliente.setDireccion();
        
        cliente.setLocalidad();
       
        cliente.setProvincia();
       
        cliente.setSexo(); // Llama al método para seleccionar el sexo desde la consola\
        
        cliente.setFechaNacimiento();
        cliente.setActivo(true); // Por defecto, el cliente está activo

        
        System.out.println("Cliente agregado exitosamente.");
        cliente.VerResumenCliente();
    }

}
