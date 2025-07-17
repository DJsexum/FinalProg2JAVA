package Clases;

import Enumerados.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Persona
{
    private int Dni;
    private boolean Activo;
    private String Nombres;
    private String Apellidos;
    private String Telefono;
    private String Direccion;
    private String Localidad;
    private Provincia Provincia;
    private Sexo Sexo;
    private LocalDate FechaNacimiento;
    protected Scanner scanner = new Scanner(System.in);

    // Constructor
    public Persona(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento)
    {
        this.Dni = dni;
        this.Activo = activo;
        this.Nombres = nombres;
        this.Apellidos = apellidos;
        this.Telefono = telefono;
        this.Direccion = direccion;
        this.Localidad = localidad;
        this.Provincia = provincia;
        this.Sexo = sexo;
        this.FechaNacimiento = fechaNacimiento;
    }

    // Metodos abstractos
    public abstract Persona buscaPersona(int dni);
    public abstract void datosPersona();
    public abstract void listarPersona();
    public abstract Persona altaPersona();
    public abstract void bajaPersona(Persona persona);
    public abstract void modificarPersona();

    // Resumen de la persona
    public void VerResumenPersona()
    {
        System.out.println("\nRESUMEN DE LA PERSONA:");
        System.out.println("DNI: " + Dni);
        System.out.println("NOMBRE: " + Nombres.toUpperCase() + " " + Apellidos.toUpperCase());
        System.out.println("TELEFONO: " + Telefono.toUpperCase());
        System.out.println("DIRECCION: " + Direccion.toUpperCase());
        System.out.println("LOCALIDAD: " + Localidad.toUpperCase());
        System.out.println("PROVINCIA: " + (Provincia != null ? Provincia.toString().toUpperCase() : ""));
        System.out.println("SEXO: " + (Sexo != null ? Sexo.toString().toUpperCase() : ""));
        System.out.println("FECHA DE NACIMIENTO: " + FechaNacimiento);
        System.out.println("\n");
    }

    // GETTERS
    public int getDni() {
        return Dni;
    }

    public boolean getActivo() {
        return Activo;
    }

    public String getNombres() {
        return Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public Provincia getProvincia() {
        return Provincia;
    }

    public Sexo getSexo() {
        return Sexo;
    }

    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }

    // SETTERS SIMPLES
    public void setDni(int dni) {
        this.Dni = dni;
    }

    public void setActivo(boolean activo) {
        this.Activo = activo;
    }

    public void setNombres(String nombres) {
        this.Nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.Telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.Direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.Localidad = localidad;
    }

    public void setProvincia(Provincia provincia) {
        this.Provincia = provincia;
    }

    public void setSexo(Sexo sexo) {
        this.Sexo = sexo;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.FechaNacimiento = fechaNacimiento;
    }

    // SETTERS INTERACTIVOS (por consola)
    public void setDniInteractivo()
    {
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();
        this.Dni = dni;
    }

    public void setNombresInteractivo()
    {
        System.out.print("NOMBRE: ");
        String nombre = scanner.nextLine();
        this.Nombres = nombre.toUpperCase();
    }

    public void setApellidosInteractivo()
    {
        System.out.print("APELLIDO: ");
        String apellido = scanner.nextLine();
        this.Apellidos = apellido.toUpperCase();
    }

    public void setTelefonoInteractivo()
    {
        System.out.print("TELEFONO: ");
        String telefono = scanner.nextLine();
        this.Telefono = telefono.toUpperCase();
    }

    public void setDireccionInteractivo()
    {
        System.out.print("DIRECCION: ");
        String direccion = scanner.nextLine();
        this.Direccion = direccion.toUpperCase();
    }

    public void setLocalidadInteractivo()
    {
        System.out.print("LOCALIDAD: ");
        String localidad = scanner.nextLine();
        this.Localidad = localidad.toUpperCase();
    }

    public void setProvinciaInteractivo()
    {
        System.out.print("PROVINCIA (SELECCIONE POR CODIGO): ");
        this.Provincia = Enumerados.Provincia.seleccionarProvincia();
    }

    public void setSexoInteractivo()
    {
        System.out.print("SEXO: ");
        this.Sexo = Enumerados.Sexo.seleccionarSexo();
    }

    public void setFechaNacimientoInteractivo()
    {
        int dia = -1, mes = -1, anio = -1;
        LocalDate fechaValida = null;

        while (fechaValida == null) 
        {
            try 
            {
                System.out.print("ANIO (YYYY): ");
                anio = Integer.parseInt(scanner.nextLine());

                System.out.print("MES (1-12): ");
                mes = Integer.parseInt(scanner.nextLine());

                System.out.print("DIA (1-31): ");
                dia = Integer.parseInt(scanner.nextLine());

                fechaValida = LocalDate.of(anio, mes, dia);
            } 
                catch (NumberFormatException e) 
                {
                    System.out.println("INGRESASTE UN NUMERO NO VALIDO. INTENTA DE NUEVO.");
                } 
                    catch (DateTimeException e) 
                    {
                        System.out.println("FECHA INVALIDA. VERIFICA SI EL DIA, MES O ANIO SON CORRECTOS.");
                    }
        }
        this.FechaNacimiento = fechaValida;
    }
}

/*
En esta clase hay dos tipos de setters:

1. Setters simples:
- Ejemplo: setTelefono(String telefono)
- Se usan para asignar un valor directamente a un atributo desde el código.
- Son útiles cuando los datos vienen de archivos, de otros métodos, o cuando se modifica un objeto desde el programa.

2. Setters interactivos (por consola):
- Ejemplo: setTelefonoInteractivo()
- Piden el dato al usuario por consola y lo asignan al atributo.
- Se usan cuando se quiere que el usuario ingrese los datos manualmente durante la ejecución del programa.

Diferencia:
- Los setters simples NO piden datos al usuario, solo asignan el valor recibido.
- Los setters interactivos SÍ piden el dato al usuario usando System.in (consola).

Entonces, el programa puede cargar datos tanto automáticamente (desde archivos o código) como manualmente (por consola)
Todo esto es segun lo que entendi que me explicaron, pero bueno, no se nada yo
*/