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
        System.out.println(quitarAcentos("=== LISTADO DE EMPLEADOS ===").toUpperCase());
        ArrayList<Empleado> empleados = Archivos.ArchivosEmpleado.leerEmpleados();
        if (empleados.isEmpty())
        {
            System.out.println(quitarAcentos("NO HAY EMPLEADOS REGISTRADOS.").toUpperCase());
        }
            else
            {
                for (Empleado e : empleados)
                {
                    System.out.println(e);
                }
            }
    }

    @Override
    public Persona buscaPersona(int legajo)
    {
        Empleado encontrado = Archivos.ArchivosEmpleado.buscarPorLegajo(legajo);
        if (encontrado != null)
        {
            System.out.println(quitarAcentos("EMPLEADO ENCONTRADO:").toUpperCase());
            System.out.println(encontrado);
        }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO NINGUN EMPLEADO CON ESE LEGAJO.").toUpperCase());
            }
            return encontrado;
    }

    @Override
    public void modificarPersona()
    {
        System.out.print(quitarAcentos("INGRESE LEGAJO DEL EMPLEADO A MODIFICAR: ").toUpperCase());
        int legajo = Integer.parseInt(scanner.nextLine());
        Empleado emp = Archivos.ArchivosEmpleado.buscarPorLegajo(legajo);
        if (emp == null)
        {
            System.out.println(quitarAcentos("NO SE ENCONTRO EMPLEADO CON ESE LEGAJO.").toUpperCase());
            return;
        }
        System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
        emp.setTelefono(scanner.nextLine());
        System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
        emp.setDireccion(scanner.nextLine());
        System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
        emp.setLocalidad(scanner.nextLine());
        System.out.print(quitarAcentos("NUEVO SALARIO: ").toUpperCase());
        emp.setSalario(Double.parseDouble(scanner.nextLine()));

        boolean modificado = Archivos.ArchivosEmpleado.modificarEmpleado(emp);
        if (modificado)
        {
            System.out.println(quitarAcentos("DATOS MODIFICADOS CORRECTAMENTE.").toUpperCase());
        }
            else
            {
                System.out.println(quitarAcentos("NO SE PUDO MODIFICAR EL EMPLEADO.").toUpperCase());
            }
    }

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
        return "EMPLEADOS:\n" +
                "DNI = " + getDni() +
                ", NOMBRE = '" + quitarAcentos(getNombres().toUpperCase()) + " " + quitarAcentos(getApellidos().toUpperCase()) + '\'' +
                ", TELEFONO = '" + quitarAcentos(getTelefono().toUpperCase()) + '\'' +
                ", DIRECCION = '" + quitarAcentos(getDireccion().toUpperCase()) + '\'' +
                ", LOCALIDAD = '" + quitarAcentos(getLocalidad().toUpperCase()) + '\'' +
                ", PROVINCIA = " + (getProvincia() != null ? getProvincia().toString().toUpperCase() : "") +
                ", SEXO = " + (getSexo() != null ? getSexo().toString().toUpperCase() : "") +
                ", FECHA DE NACIMIENTO = " + getFechaNacimiento() +
                ", LEGAJO = " + getLegajo() +
                ", FECHA INGRESO = " + getFechaIngreso() +
                ", FECHA EGRESO = " + getFechaEgreso() +
                ", SALARIO = " + getSalario() + "\n";
    }

    // Método utilitario para quitar solo acentos (no modifica ñ/Ñ)
    private String quitarAcentos(String texto)
    {
        if (texto == null) return null;
        return texto
            .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
    }
}