import java.time.LocalDate;
import Enumerados.Sexo;
import Enumerados.Provincia;

// No podemos generar personas asi porque si, por eso es una clase abstracta
// Podrias hacer dos contstructores, 1 para el empleado, el cual deberia tener todas las cosas de la clase Persona
// Otro para cliente, de la clase Persona, en donde solo se pida, por ejemplo nombre, apellido y dni o algo asi (ya que para un cliente no es necesario las demás cosas)
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

    // Constructor de la clase Persona
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

    // Metodo para ver los datos de la persona
    public void DatosPersona()
    {
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
    public int ObtenerDni()
    {
        return Dni;
    }

    public void EstablecerDni(int dni)
    {
        this.Dni = dni;
    }

    public boolean ObtenerActivo()
    {
        return Activo;
    }

    public void EstablecerActivo(boolean activo)
    {
        this.Activo = activo;
    }

    public String ObtenerNombres()
    {
        return Nombres;
    }

    public void EstablecerNombres(String nombres)
    {
        this.Nombres = nombres;
    }

    public String ObtenerApellidos()
    {
        return Apellidos;
    }

    public void EstablecerApellidos(String apellidos)
    {
        this.Apellidos = apellidos;
    }

    public String ObtenerTelefono()
    {
        return Telefono;
    }

    public void EstablecerTelefono(String telefono)
    {
        this.Telefono = telefono;
    }

    public String ObtenerDireccion()
    {
        return Direccion;
    }

    public void EstablecerDireccion(String direccion)
    {
        this.Direccion = direccion;
    }

    public String ObtenerLocalidad()
    {
        return Localidad;
    }

    public void EstablecerLocalidad(String localidad)
    {
        this.Localidad = localidad;
    }

    public Provincia ObtenerProvincia()
    {
        return Provincia;
    }

    public void EstablecerProvincia(Provincia provincia)
    {
        this.Provincia = provincia;
    }

    public Sexo ObtenerSexo()
    {
        return Sexo;
    }

    public void EstablecerSexo(Sexo sexo)
    {
        this.Sexo = sexo;
    }

    public LocalDate ObtenerFechaNacimiento()
    {
        return FechaNacimiento;
    }

    public void EstablecerFechaNacimiento(LocalDate fechaNacimiento)
    {
        this.FechaNacimiento = fechaNacimiento;
    }
}