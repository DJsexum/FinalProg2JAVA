package Clases;

import Enumerados.*;
import Archivos.ArchivosCliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Persona
{
    private CuentaCorriente ctaCte;
    private Scanner scanner = new Scanner(System.in);
    private int codigo;

    // Constructor completo
    public Cliente(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.ctaCte = ctaCte;
        this.codigo = dni;
    }

    // Constructor vacío
    public Cliente()
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.ctaCte = null;
        this.codigo = 0;
    }

    public int getCodigo()
    {
        return this.codigo;
    }

    // Métodos de cuenta corriente
    public void verCtaCte()
    {
        if (ctaCte != null)
        {
            System.out.println(quitarAcentos("DETALLES DE LA CUENTA CORRIENTE:").toUpperCase());
            System.out.println(quitarAcentos("SALDO ACTUAL: ").toUpperCase() + ctaCte.getSaldo());
            ctaCte.verMovimientos();
        }
            else
            {
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void depositarEnCtaCte(double monto, String descripcion)
    {
        // Primero se verifica si el cliente tiene una cuenta corriente asociada
        if (ctaCte != null)
        {
            // Crea un nuevo movimiento de tipo HABER (deposito)
            Movimiento movimiento = new Movimiento
            (
                1, // Codigo del movimiento
                "HABER", // Tipo de movimiento: HABER indica ingreso (aca no lo entendi, pero me djieron que lo deje asi)
                descripcion, // Detalle o descripción del deposito
                0.0, // Monto debe
                monto, // Monto haber (el valor depositado)
                ctaCte.getSaldo() + monto, // Saldo resultante despues del deposito (resultante?? quien so)
                LocalDate.now() // Fecha actual del movimiento
            );
            // Agrega el movimiento a la cuenta corriente del cliente
            ctaCte.agregarMovimiento(movimiento);
            // Muestra mensaje de confirmación del deposito
            System.out.println(quitarAcentos("DEPOSITO REALIZADO: ").toUpperCase() + monto + " (" + quitarAcentos(descripcion).toUpperCase() + ")");
        }
            else
            {
                // Si no tiene cuenta corriente, muestra el mensajito
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void retirarDeCtaCte(double monto, String descripcion)
    {
        // Verifica si el cliente tiene una cuenta corriente asociada
        if (ctaCte != null)
        {
            // Verifica si el saldo es suficiente para realizar el retiro
            if (ctaCte.getSaldo() >= monto)
            {
                // Crea un nuevo movimiento de tipo DEBE (retiro) (lo mismo de lo de arriba, no me explicaron como funciona, asi que dios sabra)
                Movimiento movimiento = new Movimiento
                (
                    2, // Código del movimiento
                    "DEBE", // Tipo de movimiento: DEBE indica egreso
                    descripcion, // Detalle o descripción del retiro
                    monto, // Monto debe (el valor retirado)
                    0.0, // Monto haber (no se usa en un retiro)
                    ctaCte.getSaldo() - monto, // Saldo resultante después del retiro
                    LocalDate.now() // Fecha actual del movimiento
                );
                // Agrega el movimiento a la cuenta corriente del cliente
                ctaCte.agregarMovimiento(movimiento);
                // Muestra mensaje de confirmación del retiro
                System.out.println(quitarAcentos("RETIRO REALIZADO: ").toUpperCase() + monto + " (" + quitarAcentos(descripcion).toUpperCase() + ")");
            }
                else
                {
                    // Si el saldo es insuficiente, muestra mensajito de advertencia
                    System.out.println(quitarAcentos("SALDO INSUFICIENTE PARA REALIZAR EL RETIRO.").toUpperCase());
                }
        }
            else
            {
                // Si no tiene cuenta corriente, muestra mensaje de advertencia
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void verResumenCliente()
    {
        System.out.println(this);
        verCtaCte();
    }

    public void setCtaCte(CuentaCorriente ctaCte)
    {
        this.ctaCte = ctaCte;
    }

    public CuentaCorriente getCtaCte()
    {
        return this.ctaCte;
    }

    // Método estático para buscar cliente por código (dni)
    public static Cliente buscarClientePorCodigo(int codigo)
    {
        ArrayList<Cliente> lista = ArchivosCliente.leerClientes();
        for (Cliente c : lista)
        {
            if (c.getCodigo() == codigo)
            {
                return c;
            }
        }
        return null;
    }

    // Alta de cliente
    @Override
    public Persona altaPersona()
    {
        System.out.println(quitarAcentos("=== NUEVO CLIENTE ===\n").toUpperCase());
        
        int dni = -1;
        while (dni <= 0)
        {
            try 
            {
                System.out.print("INGRESE DNI: ");
                String inputDni = scanner.nextLine();
                dni = Integer.parseInt(inputDni);
                if (dni <= 0)
                {
                    throw new Principal.Excepciones.DniInvalidoException("EL DNI DEBE SER UN NUMERO VALIDO");
                }
                
                // Verificar si ya existe un cliente con ese DNI en archivo
                if (ArchivosCliente.buscarPorDni(dni) != null)
                {
                    System.out.println(quitarAcentos("YA EXISTE UN CLIENTE CON ESE DNI\n").toUpperCase());
                    return null;
                }
                break;
            }
                catch (NumberFormatException e)
                {
                    System.out.println("ERROR: DEBE INGRESAR SOLO NUMEROS PARA EL DNI.");
                    dni = -1; // Resetear para continuar el bucle
                }
                    catch (Principal.Excepciones.DniInvalidoException e)
                    {
                        System.out.println("ERROR: " + e.getMessage().toUpperCase());
                        dni = -1; // Resetear para continuar el bucle
                    }
        }

        // Validación de nombres con control de excepciones
        String nombres = null;
        while (nombres == null)
        {
            try
            {
                System.out.print(quitarAcentos("\nINGRESE NOMBRES: ").toUpperCase());
                String inputNombres = scanner.nextLine();
                Principal.Excepciones.validarNombre(inputNombres, "NOMBRE");
                nombres = quitarAcentos(inputNombres).toUpperCase();
            }
                catch (Principal.Excepciones.NombreInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }

        // Validación de apellidos con control de excepciones
        String apellidos = null;
        while (apellidos == null)
        {
            try
            {
                System.out.print(quitarAcentos("INGRESE APELLIDOS: ").toUpperCase());
                String inputApellidos = scanner.nextLine();
                Principal.Excepciones.validarNombre(inputApellidos, "APELLIDO");
                apellidos = quitarAcentos(inputApellidos).toUpperCase();
            }
                catch (Principal.Excepciones.NombreInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }

        // Validación de teléfono con control de excepciones
        String telefono = null;
        while (telefono == null)
        {
            try
            {
                System.out.print(quitarAcentos("INGRESE TELEFONO: ").toUpperCase());
                String inputTelefono = scanner.nextLine();
                Principal.Excepciones.validarTelefono(inputTelefono);
                telefono = inputTelefono;
            }
                catch (Principal.Excepciones.TelefonoInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }

        // Validación de dirección con control de excepciones
        String direccion = null;
        while (direccion == null)
        {
            try
            {
                System.out.print(quitarAcentos("INGRESE DIRECCION: ").toUpperCase());
                String inputDireccion = scanner.nextLine();
                Principal.Excepciones.validarDireccion(inputDireccion);
                direccion = quitarAcentos(inputDireccion).toUpperCase();
            }
                catch (Principal.Excepciones.DireccionInvalidaException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }

        // Validación de localidad con control de excepciones
        String localidad = null;
        while (localidad == null)
        {
            try
            {
                System.out.print(quitarAcentos("INGRESE LOCALIDAD: ").toUpperCase());
                String inputLocalidad = scanner.nextLine();
                Principal.Excepciones.validarLocalidad(inputLocalidad);
                localidad = quitarAcentos(inputLocalidad).toUpperCase();
            }
                catch (Principal.Excepciones.LocalidadInvalidaException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }

        Provincia provincia = Provincia.seleccionarProvincia();
        Sexo sexo = Sexo.seleccionarSexo();

        // Usar método interactivo para fecha de nacimiento con validación individual
        System.out.println(quitarAcentos("INGRESE FECHA DE NACIMIENTO:").toUpperCase());
        
        Cliente temp = new Cliente();
        temp.setFechaNacimientoInteractivo();
        LocalDate fechaNacimiento = temp.getFechaNacimiento();

        Cliente nuevo = new Cliente(dni, true, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento, null);
        ArchivosCliente.guardarCliente(nuevo);

        System.out.println(quitarAcentos("CLIENTE CREADO CORRECTAMENTE\n").toUpperCase());
        return nuevo;
    }

    // Listar clientes
    @Override
    public void listarPersona()
    {
        System.out.println(quitarAcentos("=== LISTADO DE CLIENTES ===\n").toUpperCase());
        ArrayList<Cliente> clientes = ArchivosCliente.leerClientes();
        if (clientes.isEmpty())
        {
            System.out.println(quitarAcentos("NO HAY CLIENTES REGISTRADOS\n").toUpperCase());
        }
            else
            {
                mostrarEncabezadoClientes();
                for (int i = 0; i < clientes.size(); i++)
                {
                    System.out.println(clientes.get(i));
                    // Agregar línea separadora entre clientes, excepto después del último
                    if (i < clientes.size() - 1)
                    {
                        mostrarLineaSeparadora();
                    }
                }
                mostrarPieClientes();
                System.out.println();
            }
    }

    // Buscar cliente por DNI
    @Override
    public Persona buscaPersona(int dni)
    {
        Cliente encontrado = ArchivosCliente.buscarPorDni(dni);
        if (encontrado != null)
        {
            encontrado.mostrarClienteIndividual();
        }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI\n").toUpperCase());
            }
            return encontrado;
    }

    // Modificar cliente
    @Override
    public void modificarPersona()
    {
        try
        {
            // Primero mostrar todos los clientes disponibles
            ArrayList<Cliente> lista = ArchivosCliente.leerClientes();
            if (lista.isEmpty())
            {
                System.out.println(quitarAcentos("NO HAY CLIENTES REGISTRADOS.\n").toUpperCase());
                return;
            }
            
            System.out.println(quitarAcentos("CLIENTES DISPONIBLES:").toUpperCase());
            mostrarEncabezadoClientes();
            for (int i = 0; i < lista.size(); i++)
            {
                if (i > 0) {
                    mostrarLineaSeparadora();
                }
                System.out.println(lista.get(i));
            }
            mostrarPieClientes();
            
            System.out.print(quitarAcentos("\nINGRESE DNI DEL CLIENTE A MODIFICAR (0 PARA CANCELAR): ").toUpperCase());
            int dni = leerEntero();
            if (dni == 0)
            {
                System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                return;
            }
            
            Cliente clienteMod = ArchivosCliente.buscarPorDni(dni);
            if (clienteMod != null)
            {
                System.out.println(quitarAcentos("\nCLIENTE SELECCIONADO PARA MODIFICAR:").toUpperCase());
                mostrarEncabezadoClientes();
                System.out.println(clienteMod);
                mostrarPieClientes();
                
                System.out.print(quitarAcentos("¿CONFIRMA MODIFICACION? (S/N): ").toUpperCase());
                String confirmacion = scanner.nextLine().toUpperCase();
                if (confirmacion.equals("S"))
                {
                    // MENU DE SELECCION DE CAMPO A MODIFICAR
                    boolean continuar = true;
                    while (continuar)
                    {
                        System.out.println("\n┌───────────────────────────────────────────────┐");
                        System.out.println("│          SELECCIONE CAMPO A MODIFICAR:        │");
                        System.out.println("├───────────────────────────────────────────────┤");
                        System.out.println("│          [1] TELEFONO                         │");
                        System.out.println("│          [2] DIRECCION                        │");
                        System.out.println("│          [3] LOCALIDAD                        │");
                        System.out.println("│          [4] PROVINCIA                        │");
                        System.out.println("│          [5] MODIFICAR TODO                   │");
                        System.out.println("│          [0] SALIR SIN MODIFICAR              │");
                        System.out.println("└───────────────────────────────────────────────┘");
                        System.out.print(quitarAcentos("OPCION: ").toUpperCase());
                        
                        int opcionCampo = leerEntero();
                        boolean modificacionExitosa = false;
                        
                        switch (opcionCampo)
                        {
                            case 1: // MODIFICAR TELEFONO
                                modificacionExitosa = modificarTelefono(clienteMod);
                                break;
                                
                            case 2: // MODIFICAR DIRECCION
                                modificacionExitosa = modificarDireccion(clienteMod);
                                break;
                                
                            case 3: // MODIFICAR LOCALIDAD
                                modificacionExitosa = modificarLocalidad(clienteMod);
                                break;
                                
                            case 4: // MODIFICAR PROVINCIA
                                modificacionExitosa = modificarProvincia(clienteMod);
                                break;
                                
                            case 5: // MODIFICA TODITO
                                modificacionExitosa = modificarTodosLosCampos(clienteMod);
                                break;
                                
                            case 0: // SALIR
                                System.out.println(quitarAcentos("MODIFICACION CANCELADA.\n").toUpperCase());
                                continuar = false;
                                break;
                                
                            default:
                                System.out.println(quitarAcentos("OPCION INVALIDA. INTENTE NUEVAMENTE.\n").toUpperCase());
                                break;
                        }
                        
                        if (modificacionExitosa)
                        {
                            System.out.println(quitarAcentos("CLIENTE MODIFICADO CORRECTAMENTE.\n").toUpperCase());
                            continuar = false;
                        }
                    }
                }
                else
                {
                    System.out.println(quitarAcentos("MODIFICACION CANCELADA.\n").toUpperCase());
                }
            }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI.\n").toUpperCase());
            }
        }
        catch (Exception e)
        {
            System.out.println(quitarAcentos("ERROR AL MODIFICAR CLIENTE: " + e.getMessage()).toUpperCase());
        }
    }

    // Método auxiliar para leer enteros con validación
    private int leerEntero()
    {
        try
        {
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e)
        {
            System.out.println(quitarAcentos("ERROR: DEBE INGRESAR UN NUMERO VALIDO.").toUpperCase());
            return -1;
        }
    }

    // Métodos auxiliares para modificar cada campo
    private boolean modificarTelefono(Cliente cliente)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
            String nuevoTelefono = scanner.nextLine();
            Principal.Excepciones.validarTelefono(nuevoTelefono);
            cliente.setTelefono(nuevoTelefono);
            return ArchivosCliente.modificarCliente(cliente);
        }
        catch (Principal.Excepciones.TelefonoInvalidoException e)
        {
            System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
            return false;
        }
    }

    private boolean modificarDireccion(Cliente cliente)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
            String nuevaDireccion = scanner.nextLine();
            Principal.Excepciones.validarDireccion(nuevaDireccion);
            cliente.setDireccion(quitarAcentos(nuevaDireccion).toUpperCase());
            return ArchivosCliente.modificarCliente(cliente);
        }
        catch (Principal.Excepciones.DireccionInvalidaException e)
        {
            System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
            return false;
        }
    }

    private boolean modificarLocalidad(Cliente cliente)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
            String nuevaLocalidad = scanner.nextLine();
            Principal.Excepciones.validarLocalidad(nuevaLocalidad);
            cliente.setLocalidad(quitarAcentos(nuevaLocalidad).toUpperCase());
            return ArchivosCliente.modificarCliente(cliente);
        }
        catch (Principal.Excepciones.LocalidadInvalidaException e)
        {
            System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
            return false;
        }
    }

    private boolean modificarProvincia(Cliente cliente)
    {
        try
        {
            Provincia nuevaProvincia = Provincia.seleccionarProvincia();
            cliente.setProvincia(nuevaProvincia);
            return ArchivosCliente.modificarCliente(cliente);
        }
        catch (Exception e)
        {
            System.out.println(quitarAcentos("ERROR AL MODIFICAR PROVINCIA: " + e.getMessage()).toUpperCase());
            return false;
        }
    }

    private boolean modificarTodosLosCampos(Cliente cliente)
    {
        try
        {
            // Modificar teléfono
            System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
            String nuevoTelefono = scanner.nextLine();
            Principal.Excepciones.validarTelefono(nuevoTelefono);
            cliente.setTelefono(nuevoTelefono);

            // Modificar dirección
            System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
            String nuevaDireccion = scanner.nextLine();
            Principal.Excepciones.validarDireccion(nuevaDireccion);
            cliente.setDireccion(quitarAcentos(nuevaDireccion).toUpperCase());

            // Modificar localidad
            System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
            String nuevaLocalidad = scanner.nextLine();
            Principal.Excepciones.validarLocalidad(nuevaLocalidad);
            cliente.setLocalidad(quitarAcentos(nuevaLocalidad).toUpperCase());

            // Modificar provincia
            Provincia nuevaProvincia = Provincia.seleccionarProvincia();
            cliente.setProvincia(nuevaProvincia);

            return ArchivosCliente.modificarCliente(cliente);
        }
        catch (Exception e)
        {
            System.out.println(quitarAcentos("ERROR AL MODIFICAR TODOS LOS CAMPOS: " + e.getMessage()).toUpperCase());
            return false;
        }
    }

    // Baja de cliente con confirmación
    public void bajaPersona()
    {
        try
        {
            // Primero mostrar todos los clientes disponibles
            ArrayList<Cliente> lista = ArchivosCliente.leerClientes();
            if (lista.isEmpty())
            {
                System.out.println(quitarAcentos("NO HAY CLIENTES REGISTRADOS.\n").toUpperCase());
                return;
            }
            
            System.out.println(quitarAcentos("CLIENTES DISPONIBLES:").toUpperCase());
            mostrarEncabezadoClientes();
            for (int i = 0; i < lista.size(); i++)
            {
                if (i > 0) {
                    mostrarLineaSeparadora();
                }
                System.out.println(lista.get(i));
            }
            mostrarPieClientes();
            
            System.out.print(quitarAcentos("\nINGRESE DNI DEL CLIENTE A DAR DE BAJA (0 PARA CANCELAR): ").toUpperCase());
            int dni = leerEntero();
            if (dni == 0)
            {
                System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                return;
            }
            
            Cliente clienteBaja = ArchivosCliente.buscarPorDni(dni);
            if (clienteBaja != null)
            {
                System.out.println(quitarAcentos("\nCLIENTE SELECCIONADO PARA DAR DE BAJA:").toUpperCase());
                mostrarEncabezadoClientes();
                System.out.println(clienteBaja);
                mostrarPieClientes();
                
                System.out.print(quitarAcentos("\n¿CONFIRMA ELIMINACION? (S/N): ").toUpperCase());
                String confirmacion = scanner.nextLine().toUpperCase();
                if (confirmacion.equals("S"))
                {
                    boolean eliminado = ArchivosCliente.eliminarCliente(dni);
                    if (eliminado)
                    {
                        System.out.println(quitarAcentos("CLIENTE DADO DE BAJA CORRECTAMENTE.").toUpperCase());
                    }
                        else
                        {
                            System.out.println(quitarAcentos("ERROR AL DAR DE BAJA EL CLIENTE.").toUpperCase());
                        }
                }
                    else
                    {
                        System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                    }
            }
                else
                {
                    System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON DNI: ").toUpperCase() + dni);
                }
        }
            catch (Exception e)
            {
                System.out.println(quitarAcentos("ERROR AL DAR DE BAJA CLIENTE: ").toUpperCase() + e.getMessage());
            }
    }

    // Baja de cliente (método original para compatibilidad)
    @Override
    public void bajaPersona(Persona persona)
    {
        if (persona instanceof Cliente)
        {
            int dni = ((Cliente) persona).getDni();
            boolean eliminado = ArchivosCliente.eliminarCliente(dni);
            if (eliminado)
            {
                System.out.println(quitarAcentos("CLIENTE DADO DE BAJA CORRECTAMENTE.").toUpperCase());
            }
                else
                {
                    System.out.println(quitarAcentos("NO SE ENCONTRO EL CLIENTE PARA DAR DE BAJA.").toUpperCase());
                }
        }
            else
            {
                System.out.println(quitarAcentos("LA PERSONA NO ES UN CLIENTE.").toUpperCase());
            }
    }

    // Mostrar datos del cliente
    @Override
    public void datosPersona()
    {
        System.out.println(this);
    }

    @Override
    public String toString() // Metodo para mostrar los datos del cliente de forma entendible
    {
        // Formatear fecha como dd-mm-yyyy
        String fechaFormateada = String.format("%02d-%02d-%04d", 
                                getFechaNacimiento().getDayOfMonth(),
                                getFechaNacimiento().getMonthValue(),
                                getFechaNacimiento().getYear());
        
        return String.format("│ %8s │ %25s │ %10s │ %18s │ %15s │ %15s │ %15s │ %12s │",
                centrarTexto(String.valueOf(getDni()), 8),
                centrarTexto(quitarAcentos(getNombres()).toUpperCase() + " " + quitarAcentos(getApellidos()).toUpperCase(), 25),
                centrarTexto(getSexo() != null ? getSexo().toString().toUpperCase() : "", 10),
                centrarTexto(quitarAcentos(getDireccion()).toUpperCase(), 18),
                centrarTexto(quitarAcentos(getLocalidad()).toUpperCase(), 15),
                centrarTexto(getProvincia() != null ? getProvincia().toString().toUpperCase() : "", 15),
                centrarTexto(fechaFormateada, 15),
                centrarTexto(getTelefono(), 12)
        );
    }

    // Método auxiliar para centrar texto en un ancho específico
    private String centrarTexto(String texto, int ancho)
    {
        if (texto == null) texto = "";
        if (texto.length() >= ancho) return texto.substring(0, ancho);
        
        int espacios = ancho - texto.length();
        int espaciosIzquierda = espacios / 2;
        int espaciosDerecha = espacios - espaciosIzquierda;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < espaciosIzquierda; i++) sb.append(" ");
        sb.append(texto);
        for (int i = 0; i < espaciosDerecha; i++) sb.append(" ");
        
        return sb.toString();
    }

    // Método para mostrar el encabezado de la tabla de clientes
    public static void mostrarEncabezadoClientes()
    {
        System.out.println("┌──────────┬───────────────────────────┬────────────┬────────────────────┬─────────────────┬─────────────────┬─────────────────┬──────────────┐");
        System.out.println("│   DNI    │       NOMBRE/APELLIDO     │    SEXO    │     DIRECCION      │    LOCALIDAD    │    PROVINCIA    │   NACIMIENTO    │   TELEFONO   │");
        System.out.println("├──────────┼───────────────────────────┼────────────┼────────────────────┼─────────────────┼─────────────────┼─────────────────┼──────────────┤");
    }

    // Método para mostrar línea separadora entre clientes
    public static void mostrarLineaSeparadora()
    {
        System.out.println("├──────────┼───────────────────────────┼────────────┼────────────────────┼─────────────────┼─────────────────┼─────────────────┼──────────────┤");
    }

    // Método para mostrar el pie de la tabla
    public static void mostrarPieClientes()
    {
        System.out.println("└──────────┴───────────────────────────┴────────────┴────────────────────┴─────────────────┴─────────────────┴─────────────────┴──────────────┘");
    }

    // Método para mostrar un cliente individual en formato tabla
    public void mostrarClienteIndividual()
    {
        System.out.println(quitarAcentos("=== DATOS DEL CLIENTE ===\n").toUpperCase());
        mostrarEncabezadoClientes();
        System.out.println(this);
        mostrarPieClientes();
        System.out.println();
    }
}