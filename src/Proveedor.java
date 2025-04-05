import java.util.ArrayList;
import java.time.LocalDate;
import Enumerados.Sexo;
import Enumerados.Provincia;

// Dijo Guille que podria ser que si queremos sacar una clase, podria ser Proveedor, pero solo esta clase

public class Proveedor extends Persona
{
    private CuentaCorriente CtaCte;

    public Proveedor(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.CtaCte = ctaCte;
    }

    public void verCtaCte() { /* Implementación */ }
}