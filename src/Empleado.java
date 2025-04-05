import java.util.ArrayList;
import java.time.LocalDate;
import Enumerados.Sexo;
import Enumerados.Provincia;

public class Empleado extends Persona
{
    private LocalDate FechaIngreso;
    private LocalDate FechaEgreso;
    private int Legajo;
    private double Salario;

    public Empleado(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaIngreso, LocalDate fechaEgreso, int legajo, double salario)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.FechaIngreso = fechaIngreso;
        this.FechaEgreso = fechaEgreso;
        this.Legajo = legajo;
        this.Salario = salario;
    }

    public LocalDate getFechaIngreso()
    {
        return FechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso)
    {
        this.FechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgreso()
    {
        return FechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso)
    {
        this.FechaEgreso = fechaEgreso;
    }

    public int getLegajo()
    {
        return Legajo;
    }

    public void setLegajo(int legajo)
    {
        this.Legajo = legajo;
    }

    public double getSalario()
    {
        return Salario;
    }

    public void setSalario(double salario)
    {
        this.Salario = salario;
    }

    // Métodos de Empleado
    public Empleado altaEmpleado()
    {
        return null;
    }

    public void bajaEmpleado()
    {}

    public void modificarEmpleado(Empleado empleado)
    {}

    public Empleado buscarEmpleado(int legajo)
    {return null;}

    public ArrayList<Empleado> listarEmpleados()
    {return null;}
}