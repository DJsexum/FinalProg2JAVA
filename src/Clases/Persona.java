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
        int dni = -1;
        while (dni <= 0)
        {
            try 
            {
                System.out.print("DNI: ");
                String input = scanner.nextLine();
                dni = Integer.parseInt(input);
                
                if (dni <= 0)
                {
                    throw new Principal.Excepciones.DniInvalidoException("EL DNI DEBE SER UN NUMERO VALIDO");
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
        this.Dni = dni;
    }

    public void setNombresInteractivo()
    {
        String nombres = null;
        while (nombres == null)
        {
            try
            {
                System.out.print("NOMBRE: ");
                String inputNombres = scanner.nextLine();
                Principal.Excepciones.validarNombre(inputNombres, "NOMBRE");
                nombres = quitarAcentos(inputNombres).toUpperCase();
            }
                catch (Principal.Excepciones.NombreInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
        this.Nombres = nombres;
    }

    public void setApellidosInteractivo()
    {
        String apellidos = null;
        while (apellidos == null)
        {
            try
            {
                System.out.print("APELLIDO: ");
                String inputApellidos = scanner.nextLine();
                Principal.Excepciones.validarNombre(inputApellidos, "APELLIDO");
                apellidos = quitarAcentos(inputApellidos).toUpperCase();
            }
                catch (Principal.Excepciones.NombreInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
        this.Apellidos = apellidos;
    }

    public void setTelefonoInteractivo()
    {
        String telefono = null;
        while (telefono == null)
        {
            try
            {
                System.out.print("TELEFONO: ");
                String inputTelefono = scanner.nextLine();
                Principal.Excepciones.validarTelefono(inputTelefono);
                telefono = inputTelefono;
            }
                catch (Principal.Excepciones.TelefonoInvalidoException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
        this.Telefono = telefono;
    }

    public void setDireccionInteractivo()
    {
        String direccion = null;
        while (direccion == null)
        {
            try
            {
                System.out.print("DIRECCION: ");
                String inputDireccion = scanner.nextLine();
                Principal.Excepciones.validarDireccion(inputDireccion);
                direccion = quitarAcentos(inputDireccion).toUpperCase();
            }
                catch (Principal.Excepciones.DireccionInvalidaException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
        this.Direccion = direccion;
    }

    public void setLocalidadInteractivo()
    {
        String localidad = null;
        while (localidad == null)
        {
            try
            {
                System.out.print("LOCALIDAD: ");
                String inputLocalidad = scanner.nextLine();
                Principal.Excepciones.validarLocalidad(inputLocalidad);
                localidad = quitarAcentos(inputLocalidad).toUpperCase();
            }
                catch (Principal.Excepciones.LocalidadInvalidaException e)
                {
                    System.out.println("ERROR: " + e.getMessage());
                }
        }
        this.Localidad = localidad;
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
        
        // Validar DIA individualmente
        while (dia < 1 || dia > 31)
        {
            try 
            {
                System.out.print("FECHA (DD): ");
                String inputDia = scanner.nextLine();
                dia = Integer.parseInt(inputDia);
                
                if (dia < 1 || dia > 31) 
                {
                    System.out.println("ERROR: EL DIA DEBE ESTAR ENTRE 1 Y 31.");
                }
            }
                catch (NumberFormatException e) 
                {
                    System.out.println("ERROR: DEBE INGRESAR SOLO NUMEROS PARA EL DIA.");
                    dia = -1; // Resetear para continuar el bucle
                }
        }
        
        // Validar MES individualmente  
        while (mes < 1 || mes > 12)
        {
            try 
            {
                System.out.print("MES (MM): ");
                String inputMes = scanner.nextLine();
                mes = Integer.parseInt(inputMes);
                
                if (mes < 1 || mes > 12) 
                {
                    System.out.println("ERROR: EL MES DEBE ESTAR ENTRE 1 Y 12.");
                }
            }
                catch (NumberFormatException e) 
                {
                    System.out.println("ERROR: DEBE INGRESAR SOLO NUMEROS PARA EL MES.");
                    mes = -1; // Resetear para continuar el bucle
                }
        }
        
        // Validar ANIO individualmente
        while (anio < 1900 || anio > 2024)
        {
            try 
            {
                System.out.print("AÑO (YYYY): ");
                String inputAnio = scanner.nextLine();
                anio = Integer.parseInt(inputAnio);
                
                if (anio < 1900 || anio > 2024) 
                {
                    System.out.println("ERROR: EL AÑO DEBE SER VALIDO.");
                }
            }
                catch (NumberFormatException e) 
                {
                    System.out.println("ERROR: DEBE INGRESAR SOLO NUMEROS PARA EL AÑO.");
                    anio = -1; // Resetear para continuar el bucle
                }
        }
        
        // Validar que la fecha sea válida en el calendario
        try 
        {
            LocalDate fechaValida = LocalDate.of(anio, mes, dia);
            this.FechaNacimiento = fechaValida;
        } 
            catch (DateTimeException e) 
            {
                System.out.println("ERROR: LA FECHA " + dia + "/" + mes + "/" + anio + " NO EXISTE EN EL CALENDARIO.");
                System.out.println("INTENTE NUEVAMENTE:");
                setFechaNacimientoInteractivo(); // Llamada recursiva para reiniciar
            }
    }

    // Metodo para quitar solo acentos (no modifica ñ/Ñ)
    protected String quitarAcentos(String texto)
    {
        if (texto == null) return null;
        return texto
            .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
    }
}

/*
Correccion, ahora no se, por que cambie un monton de cosas, asi que a la hora de ver esto, puede ser que esten, como puede que no, todo depende de como haya hecho las cosas a estas alturas de la vida.
Pero por las dudas dejo estos datitos:

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