package Clases;

import Enumerados.Provincia;
import Enumerados.Sexo;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

// No podemos generar personas asi porque si, por eso es una clase abstracta
// Podrias hacer dos contstructores, 1 para el empleado, el cual deberia tener todas las cosas de la clase Clases.Persona
// Otro para cliente, de la clase Clases.Persona, en donde solo se pida, por ejemplo nombre, apellido y dni o algo asi (ya que para un cliente no es necesario las demás cosas)
// Después hacer los getters y setters
// Mejora las cosas haciendo paquetes para las cosas
// Podríamos hacer un constructor o funcion para que solo sean printf de los datos de lo que se pida, por ejemplo empleado sería todos los datos
// Intentar no usar tantos Try Cach para que no sea tanto el uso de memoria, en cambio, usar un if else

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
    private Scanner scanner = new Scanner(System.in);

    // Constructor de la clase Clases.Persona
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

    // @Override
    // public String toString()
    // {
    //     return "Clases.Persona\n" +
    //             "{\n" +
    //             "DNI = " + Dni + ",\n" +
    //             "Nombre = " + Nombres + " " + Apellidos + ",\n" +
    //             "Teléfono = " + Telefono + ",\n" +
    //             "Dirección = " + Direccion + ",\n" +
    //             "Localidad = " + Localidad + ",\n" +
    //             "Provincia = " + Provincia + ",\n" +
    //             "Sexo = " + Sexo + ",\n" +
    //             "Fecha de Nacimiento = " + FechaNacimiento + "\n" +
    //             "}";
    // }

    public void VerResumenPersona()
    {
        System.out.println("Resumen de la Persona:");
        System.out.println("DNI: " + Dni);
        System.out.println("Nombre: " + Nombres + " " + Apellidos);
        System.out.println("Teléfono: " + Telefono);
        System.out.println("Dirección: " + Direccion);
        System.out.println("Localidad: " + Localidad);
        System.out.println("Provincia: " + Provincia);
        System.out.println("Sexo: " + Sexo);
        System.out.println("Fecha de Nacimiento: " + FechaNacimiento);
    }

    // Metodos Getters y Setters
    public int getDni()
    {
        return Dni;
    }

    public void setDni()
    {
        System.out.println("Ingrese los datos del nuevo cliente:");
        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        this.Dni = dni;
    }

    public boolean getActivo()
    {
        return Activo;
    }

    public void setActivo(boolean activo)
    {
        this.Activo = activo;
    }

    public String getNombres()
    {
        return Nombres;
    }

    public void setNombres()
    {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        this.Nombres = nombre;
    }

    public String getApellidos()
    {
        return Apellidos;
    }

    public void setApellidos()
    {   System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        this.Apellidos = apellido;
    }

    public String getTelefono()
    {  
        return Telefono;
    }

    public void setTelefono()
    {
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        this.Telefono = telefono;
    }

    public String getDireccion()
    {
        return Direccion;
    }

    public void setDireccion()
    {
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        this.Direccion = direccion;
    }

    public String getLocalidad()
    {
        return Localidad;
    }

    public void setLocalidad()
    {
        System.out.print("Localidad: ");
        String localidad = scanner.nextLine();
        this.Localidad = localidad;
    }

    public Provincia getProvincia()
    {
        return Provincia;
    }

    public void setProvincia()
    {
        System.out.print("Provincia (seleccione por código): ");
        this.Provincia = Enumerados.Provincia.seleccionarProvincia();
    }

    public Sexo getSexo()
    {
        return Sexo;
    }

    public void setSexo()
    {
        System.out.print("Sexo: ");
        this.Sexo = Enumerados.Sexo.seleccionarSexo();
    }

    public LocalDate getFechaNacimiento()
    {
        return FechaNacimiento;
    }

    public void setFechaNacimiento() {
    int dia = -1, mes = -1, anio = -1;
    LocalDate fechaValida = null;

        while (fechaValida == null) {
            try {
                System.out.print("Año (YYYY): ");
                anio = Integer.parseInt(scanner.nextLine());

                System.out.print("Mes (1-12): ");
                mes = Integer.parseInt(scanner.nextLine());

                System.out.print("Día (1-31): ");
                dia = Integer.parseInt(scanner.nextLine());

                // Intenta crear la fecha, lanza excepción si no es válida
                fechaValida = LocalDate.of(anio, mes, dia);
            } catch (NumberFormatException e) {
                System.out.println("Ingresaste un número no válido. Intentá de nuevo.");
            } catch (DateTimeException e) {
                System.out.println("Fecha inválida. Verificá si el día, mes o año son correctos.");
            }
        }

        this.FechaNacimiento = fechaValida;
    }

}