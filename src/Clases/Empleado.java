package Clases;

import Enumerados.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Empleado extends Persona
{
    private LocalDate FechaIngreso;
    private LocalDate FechaEgreso;
    private int Legajo;
    private double Salario;
    private Scanner scanner = new Scanner(System.in);

    // Constructor completo
    public Empleado(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaIngreso, LocalDate fechaEgreso, int legajo, double salario)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.FechaIngreso = fechaIngreso;
        this.FechaEgreso = fechaEgreso;
        this.Legajo = legajo;
        this.Salario = salario;
    }

    // Constructor vacio
    public Empleado()
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.FechaIngreso = null;
        this.FechaEgreso = null;
        this.Legajo = 0;
        this.Salario = 0.0;
    }

    // Getters y setters simples
    public LocalDate getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.FechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso() {
        return FechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.FechaEgreso = fechaEgreso;
    }

    public int getLegajo() {
        return Legajo;
    }

    public void setLegajo(int legajo) {
        this.Legajo = legajo;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double salario) {
        this.Salario = salario;
    }

    // Setters interactivos para carga por consola
    public void setFechaIngresoInteractivo()
    {
        System.out.print(quitarAcentos("FECHA DE INGRESO (YYYY-MM-DD): ").toUpperCase());
        this.FechaIngreso = LocalDate.parse(scanner.nextLine());
    }

    public void setFechaEgresoInteractivo()
    {
        System.out.print(quitarAcentos("FECHA DE EGRESO (YYYY-MM-DD, ENTER SI SIGUE ACTIVO): ").toUpperCase());
        String input = scanner.nextLine();
        if (input.isEmpty()) 
        {
            this.FechaEgreso = null;
        }
            else 
            {
                this.FechaEgreso = LocalDate.parse(input);
            }
    }

    public void setLegajoInteractivo()
    {
        System.out.print(quitarAcentos("LEGAJO: ").toUpperCase());
        this.Legajo = Integer.parseInt(scanner.nextLine());
    }

    public void setSalarioInteractivo()
    {
        System.out.print(quitarAcentos("SALARIO: ").toUpperCase());
        this.Salario = Double.parseDouble(scanner.nextLine());
    }

    // Métodos obligatorios de Persona

    @Override
    public Persona altaPersona()
    {
        System.out.println(quitarAcentos("=== ALTA DE NUEVO EMPLEADO ===").toUpperCase());
        setDniInteractivo();
        setNombresInteractivo();
        setApellidosInteractivo();
        setTelefonoInteractivo();
        setDireccionInteractivo();
        setLocalidadInteractivo();
        setProvinciaInteractivo();
        setSexoInteractivo();
        setFechaNacimientoInteractivo();
        setFechaIngresoInteractivo();
        setFechaEgresoInteractivo();
        setLegajoInteractivo();
        setSalarioInteractivo();

        Empleado nuevo = new Empleado(getDni(), true, getNombres(), getApellidos(), getTelefono(), getDireccion(), getLocalidad(), getProvincia(), getSexo(), getFechaNacimiento(), getFechaIngreso(), getFechaEgreso(), getLegajo(), getSalario());

        Archivos.ArchivosEmpleado.guardarEmpleado(nuevo);

        System.out.println(quitarAcentos("EMPLEADO DADO DE ALTA CORRECTAMENTE.").toUpperCase());
        return nuevo;
    }

    @Override
    public void listarPersona()
    {
        System.out.println(quitarAcentos("=== LISTADO DE EMPLEADOS ===\n").toUpperCase());
        ArrayList<Empleado> empleados = Archivos.ArchivosEmpleado.leerEmpleados();
        if (empleados.isEmpty())
        {
            System.out.println(quitarAcentos("NO HAY EMPLEADOS REGISTRADOS.\n").toUpperCase());
        }
        else
        {
            mostrarEncabezadoEmpleados();
            for (int i = 0; i < empleados.size(); i++)
            {
                if (i > 0) {
                    mostrarLineaSeparadoraEmpleados();
                }
                System.out.println(empleados.get(i));
            }
            mostrarPieEmpleados();
            System.out.println();
        }
    }

    @Override
    public Persona buscaPersona(int legajo)
    {
        Empleado encontrado = Archivos.ArchivosEmpleado.buscarPorLegajo(legajo);
        if (encontrado != null)
        {
            encontrado.mostrarEmpleadoIndividual();
        }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO NINGUN EMPLEADO CON ESE LEGAJO.\n").toUpperCase());
            }
            return encontrado;
    }

    @Override
    public void modificarPersona()
    {
        try
        {
            // Primero mostrar todos los empleados disponibles
            ArrayList<Empleado> lista = Archivos.ArchivosEmpleado.leerEmpleados();
            if (lista.isEmpty())
            {
                System.out.println(quitarAcentos("NO HAY EMPLEADOS REGISTRADOS.\n").toUpperCase());
                return;
            }
            
            System.out.println(quitarAcentos("EMPLEADOS DISPONIBLES:").toUpperCase());
            mostrarEncabezadoEmpleados();
            for (int i = 0; i < lista.size(); i++)
            {
                if (i > 0) {
                    mostrarLineaSeparadoraEmpleados();
                }
                System.out.println(lista.get(i));
            }
            mostrarPieEmpleados();
            
            System.out.print(quitarAcentos("\nINGRESE LEGAJO DEL EMPLEADO A MODIFICAR (0 PARA CANCELAR): ").toUpperCase());
            int legajo = leerEntero();
            if (legajo == 0)
            {
                System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                return;
            }
            
            Empleado empleadoMod = Archivos.ArchivosEmpleado.buscarPorLegajo(legajo);
            if (empleadoMod != null)
            {
                System.out.println(quitarAcentos("\nEMPLEADO SELECCIONADO PARA MODIFICAR:").toUpperCase());
                mostrarEncabezadoEmpleados();
                System.out.println(empleadoMod);
                mostrarPieEmpleados();
                
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
                        System.out.println("│          [5] SALARIO                          │");
                        System.out.println("│          [6] MODIFICAR TODO                   │");
                        System.out.println("│          [0] SALIR SIN MODIFICAR              │");
                        System.out.println("└───────────────────────────────────────────────┘");
                        System.out.print(quitarAcentos("OPCION: ").toUpperCase());
                        
                        int opcionCampo = leerEntero();
                        boolean modificacionExitosa = false;
                        
                        switch (opcionCampo)
                        {
                            case 1: // MODIFICAR TELEFONO
                                modificacionExitosa = modificarTelefonoEmpleado(empleadoMod);
                                break;
                                
                            case 2: // MODIFICAR DIRECCION
                                modificacionExitosa = modificarDireccionEmpleado(empleadoMod);
                                break;
                                
                            case 3: // MODIFICAR LOCALIDAD
                                modificacionExitosa = modificarLocalidadEmpleado(empleadoMod);
                                break;
                                
                            case 4: // MODIFICAR PROVINCIA
                                modificacionExitosa = modificarProvinciaEmpleado(empleadoMod);
                                break;
                                
                            case 5: // MODIFICAR SALARIO
                                modificacionExitosa = modificarSalarioEmpleado(empleadoMod);
                                break;
                                
                            case 6: // MODIFICAR TODO
                                modificacionExitosa = modificarTodosLosCamposEmpleado(empleadoMod);
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
                            System.out.println(quitarAcentos("EMPLEADO MODIFICADO CORRECTAMENTE.\n").toUpperCase());
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
                    System.out.println(quitarAcentos("NO SE ENCONTRO EMPLEADO CON ESE LEGAJO.\n").toUpperCase());
                }
        }
            catch (Exception e)
            {
                System.out.println(quitarAcentos("ERROR AL MODIFICAR EMPLEADO: " + e.getMessage()).toUpperCase());
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

    // Métodos auxiliares para modificar cada campo del empleado
    private boolean modificarTelefonoEmpleado(Empleado empleado)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
            String nuevoTelefono = scanner.nextLine();
            Principal.Excepciones.validarTelefono(nuevoTelefono);
            empleado.setTelefono(nuevoTelefono);
            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (Principal.Excepciones.TelefonoInvalidoException e)
            {
                System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
                return false;
            }
    }

    private boolean modificarDireccionEmpleado(Empleado empleado)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
            String nuevaDireccion = scanner.nextLine();
            Principal.Excepciones.validarDireccion(nuevaDireccion);
            empleado.setDireccion(quitarAcentos(nuevaDireccion).toUpperCase());
            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (Principal.Excepciones.DireccionInvalidaException e)
            {
                System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
                return false;
            }
    }

    private boolean modificarLocalidadEmpleado(Empleado empleado)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
            String nuevaLocalidad = scanner.nextLine();
            Principal.Excepciones.validarLocalidad(nuevaLocalidad);
            empleado.setLocalidad(quitarAcentos(nuevaLocalidad).toUpperCase());
            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (Principal.Excepciones.LocalidadInvalidaException e)
            {
                System.out.println(quitarAcentos("ERROR: " + e.getMessage()).toUpperCase());
                return false;
            }
    }

    private boolean modificarProvinciaEmpleado(Empleado empleado)
    {
        try
        {
            Provincia nuevaProvincia = Provincia.seleccionarProvincia();
            empleado.setProvincia(nuevaProvincia);
            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (Exception e)
            {
                System.out.println(quitarAcentos("ERROR AL MODIFICAR PROVINCIA: " + e.getMessage()).toUpperCase());
                return false;
            }
    }

    private boolean modificarSalarioEmpleado(Empleado empleado)
    {
        try
        {
            System.out.print(quitarAcentos("NUEVO SALARIO: ").toUpperCase());
            double nuevoSalario = Double.parseDouble(scanner.nextLine());
            if (nuevoSalario <= 0)
            {
                System.out.println(quitarAcentos("ERROR: EL SALARIO DEBE SER MAYOR A CERO.").toUpperCase());
                return false;
            }
            empleado.setSalario(nuevoSalario);
            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (NumberFormatException e)
            {
                System.out.println(quitarAcentos("ERROR: DEBE INGRESAR UN NUMERO VALIDO PARA EL SALARIO.").toUpperCase());
                return false;
            }
                catch (Exception e)
                {
                    System.out.println(quitarAcentos("ERROR AL MODIFICAR SALARIO: " + e.getMessage()).toUpperCase());
                    return false;
                }
    }

    private boolean modificarTodosLosCamposEmpleado(Empleado empleado)
    {
        try
        {
            // Modificar teléfono
            System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
            String nuevoTelefono = scanner.nextLine();
            Principal.Excepciones.validarTelefono(nuevoTelefono);
            empleado.setTelefono(nuevoTelefono);

            // Modificar dirección
            System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
            String nuevaDireccion = scanner.nextLine();
            Principal.Excepciones.validarDireccion(nuevaDireccion);
            empleado.setDireccion(quitarAcentos(nuevaDireccion).toUpperCase());

            // Modificar localidad
            System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
            String nuevaLocalidad = scanner.nextLine();
            Principal.Excepciones.validarLocalidad(nuevaLocalidad);
            empleado.setLocalidad(quitarAcentos(nuevaLocalidad).toUpperCase());

            // Modificar provincia
            Provincia nuevaProvincia = Provincia.seleccionarProvincia();
            empleado.setProvincia(nuevaProvincia);

            // Modificar salario
            System.out.print(quitarAcentos("NUEVO SALARIO: ").toUpperCase());
            double nuevoSalario = Double.parseDouble(scanner.nextLine());
            if (nuevoSalario <= 0)
            {
                System.out.println(quitarAcentos("ERROR: EL SALARIO DEBE SER MAYOR A CERO.").toUpperCase());
                return false;
            }
            empleado.setSalario(nuevoSalario);

            return Archivos.ArchivosEmpleado.modificarEmpleado(empleado);
        }
            catch (Exception e)
            {
                System.out.println(quitarAcentos("ERROR AL MODIFICAR TODOS LOS CAMPOS: " + e.getMessage()).toUpperCase());
                return false;
            }
    }

    // Baja de empleado con confirmación
    public void bajaPersona()
    {
        try
        {
            // Primero mostrar todos los empleados disponibles
            ArrayList<Empleado> lista = Archivos.ArchivosEmpleado.leerEmpleados();
            if (lista.isEmpty())
            {
                System.out.println(quitarAcentos("NO HAY EMPLEADOS REGISTRADOS.\n").toUpperCase());
                return;
            }
            
            System.out.println(quitarAcentos("EMPLEADOS DISPONIBLES:").toUpperCase());
            mostrarEncabezadoEmpleados();
            for (int i = 0; i < lista.size(); i++)
            {
                if (i > 0) {
                    mostrarLineaSeparadoraEmpleados();
                }
                System.out.println(lista.get(i));
            }
            mostrarPieEmpleados();
            
            System.out.print(quitarAcentos("\nINGRESE LEGAJO DEL EMPLEADO A DAR DE BAJA (0 PARA CANCELAR): ").toUpperCase());
            int legajo = leerEntero();
            if (legajo == 0)
            {
                System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                return;
            }
            
            Empleado empleadoBaja = Archivos.ArchivosEmpleado.buscarPorLegajo(legajo);
            if (empleadoBaja != null)
            {
                System.out.println(quitarAcentos("\nEMPLEADO SELECCIONADO PARA DAR DE BAJA:").toUpperCase());
                mostrarEncabezadoEmpleados();
                System.out.println(empleadoBaja);
                mostrarPieEmpleados();
                
                System.out.print(quitarAcentos("\n¿CONFIRMA ELIMINACION? (S/N): ").toUpperCase());
                String confirmacion = scanner.nextLine().toUpperCase();
                if (confirmacion.equals("S"))
                {
                    boolean eliminado = Archivos.ArchivosEmpleado.eliminarEmpleado(legajo);
                    if (eliminado)
                    {
                        System.out.println(quitarAcentos("EMPLEADO DADO DE BAJA CORRECTAMENTE.").toUpperCase());
                    }
                        else
                        {
                            System.out.println(quitarAcentos("ERROR AL DAR DE BAJA EL EMPLEADO.").toUpperCase());
                        }
                }
                    else
                    {
                        System.out.println(quitarAcentos("OPERACION CANCELADA.\n").toUpperCase());
                    }
            }
                else
                {
                    System.out.println(quitarAcentos("NO SE ENCONTRO EMPLEADO CON LEGAJO: ").toUpperCase() + legajo);
                }
        }
            catch (Exception e)
            {
                System.out.println(quitarAcentos("ERROR AL DAR DE BAJA EMPLEADO: ").toUpperCase() + e.getMessage());
            }
    }

    // Baja de empleado (método original para compatibilidad)
    @Override
    public void bajaPersona(Persona persona)
    {
        if (persona instanceof Empleado)
        {
            int legajo = ((Empleado) persona).getLegajo();
            boolean eliminado = Archivos.ArchivosEmpleado.eliminarEmpleado(legajo);
            if (eliminado)
            {
                System.out.println(quitarAcentos("EMPLEADO DADO DE BAJA CORRECTAMENTE.").toUpperCase());
            }
                else
                {
                    System.out.println(quitarAcentos("NO SE ENCONTRO EL EMPLEADO PARA DAR DE BAJA.").toUpperCase());
                }
        }
            else
            {
                System.out.println(quitarAcentos("LA PERSONA NO ES UN EMPLEADO.").toUpperCase());
            }
    }

    @Override
    public void datosPersona()
    {
        System.out.println(this);
    }

    @Override
    public String toString()
    {
        // Formatear fecha de nacimiento como dd-mm-yyyy
        String fechaNacimientoFormateada = String.format("%02d-%02d-%04d", 
                                          getFechaNacimiento().getDayOfMonth(),
                                          getFechaNacimiento().getMonthValue(),
                                          getFechaNacimiento().getYear());
        
        // Formatear fecha de ingreso como dd-mm-yyyy
        String fechaIngresoFormateada = getFechaIngreso() != null ? 
                                       String.format("%02d-%02d-%04d", 
                                                    getFechaIngreso().getDayOfMonth(),
                                                    getFechaIngreso().getMonthValue(),
                                                    getFechaIngreso().getYear()) : "";
        
        // Formatear fecha de egreso como dd-mm-yyyy o "ACTIVO"
        String fechaEgresoFormateada = getFechaEgreso() != null ? 
                                      String.format
                                                ("%02d-%02d-%04d", 
                                                    getFechaEgreso().getDayOfMonth(),
                                                    getFechaEgreso().getMonthValue(),
                                                    getFechaEgreso().getYear()
                                                ) : "ACTIVO";
        
        return String.format
        ("│ %8s │ %6s │ %25s │ %10s │ %18s │ %15s │ %15s │ %15s │ %12s │ %15s │ %15s │ %10s │",
                centrarTexto(String.valueOf(getDni()), 8),
                centrarTexto(String.valueOf(getLegajo()), 6),
                centrarTexto(quitarAcentos(getNombres()).toUpperCase() + " " + quitarAcentos(getApellidos()).toUpperCase(), 25),
                centrarTexto(getSexo() != null ? getSexo().toString().toUpperCase() : "", 10),
                centrarTexto(quitarAcentos(getDireccion()).toUpperCase(), 18),
                centrarTexto(quitarAcentos(getLocalidad()).toUpperCase(), 15),
                centrarTexto(getProvincia() != null ? getProvincia().toString().toUpperCase() : "", 15),
                centrarTexto(fechaNacimientoFormateada, 15),
                centrarTexto(getTelefono(), 12),
                centrarTexto(fechaIngresoFormateada, 15),
                centrarTexto(fechaEgresoFormateada, 15),
                centrarTexto(String.format("%.2f", getSalario()), 10)
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

    // Método para mostrar el encabezado de la tabla de empleados
    public static void mostrarEncabezadoEmpleados()
    {
        System.out.println("┌──────────┬────────┬───────────────────────────┬────────────┬────────────────────┬─────────────────┬─────────────────┬─────────────────┬──────────────┬─────────────────┬─────────────────┬────────────┐");
        System.out.println("│   DNI    │ LEGAJO │       NOMBRE/APELLIDO     │    SEXO    │     DIRECCION      │    LOCALIDAD    │    PROVINCIA    │   NACIMIENTO    │   TELEFONO   │     INGRESO     │     EGRESO      │   SALARIO  │");
        System.out.println("├──────────┼────────┼───────────────────────────┼────────────┼────────────────────┼─────────────────┼─────────────────┼─────────────────┼──────────────┼─────────────────┼─────────────────┼────────────┤");
    }

    // Método para mostrar línea separadora entre empleados
    public static void mostrarLineaSeparadoraEmpleados()
    {
        System.out.println("├──────────┼────────┼───────────────────────────┼────────────┼────────────────────┼─────────────────┼─────────────────┼─────────────────┼──────────────┼─────────────────┼─────────────────┼────────────┤");
    }

    // Método para mostrar el pie de la tabla
    public static void mostrarPieEmpleados()
    {
        System.out.println("└──────────┴────────┴───────────────────────────┴────────────┴────────────────────┴─────────────────┴─────────────────┴─────────────────┴──────────────┴─────────────────┴─────────────────┴────────────┘");
    }

    // Método para mostrar un empleado individual en formato tabla
    public void mostrarEmpleadoIndividual()
    {
        System.out.println(quitarAcentos("=== DATOS DEL EMPLEADO ===\n").toUpperCase());
        mostrarEncabezadoEmpleados();
        System.out.println(this);
        mostrarPieEmpleados();
        System.out.println();
    }
}