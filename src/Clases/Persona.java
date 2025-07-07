package Clases;

import Enumerados.Provincia;
import Enumerados.Sexo;
import java.time.LocalDate;

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

    @Override
    public String toString()
    {
        return "Clases.Persona\n" +
                "{\n" +
                "DNI = " + Dni + ",\n" +
                "Nombre = " + Nombres + " " + Apellidos + ",\n" +
                "Teléfono = " + Telefono + ",\n" +
                "Dirección = " + Direccion + ",\n" +
                "Localidad = " + Localidad + ",\n" +
                "Provincia = " + Provincia + ",\n" +
                "Sexo = " + Sexo + ",\n" +
                "Fecha de Nacimiento = " + FechaNacimiento + "\n" +
                "}";
    }

    // Metodos Getters y Setters
    public int getDni()
    {
        return Dni;
    }

    public void setDni(int dni)
    {
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

    public void setNombres(String nombres)
    {
        this.Nombres = nombres;
    }

    public String getApellidos()
    {
        return Apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.Apellidos = apellidos;
    }

    public String getTelefono()
    {
        return Telefono;
    }

    public void setTelefono(String telefono)
    {
        this.Telefono = telefono;
    }

    public String getDireccion()
    {
        return Direccion;
    }

    public void setDireccion(String direccion)
    {
        this.Direccion = direccion;
    }

    public String getLocalidad()
    {
        return Localidad;
    }

    public void setLocalidad(String localidad)
    {
        this.Localidad = localidad;
    }

    public Provincia getProvincia()
    {
        return Provincia;
    }

    public void setProvincia()
    {
        this.Provincia = Enumerados.Provincia.seleccionarProvincia();
    }

    public Sexo getSexo()
    {
        return Sexo;
    }

    public void setSexo()
    {
        this.Sexo = Enumerados.Sexo.getSexoDesdeConsola();
    }

    public LocalDate getFechaNacimiento()
    {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.FechaNacimiento = fechaNacimiento;
    }
}