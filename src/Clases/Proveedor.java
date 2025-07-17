package Clases;

import java.time.LocalDate;
import Enumerados.*;
import Archivos.ArchivosProveedor;
import java.util.ArrayList;
import java.util.Scanner;

/*
Clase Proveedor: representa un proveedor del sistema.
Hereda de Persona y agrega el atributo CuentaCorriente.
*/
public class Proveedor extends Persona
{
    private CuentaCorriente CtaCte;

    // Scanner unico y estatico para toda la clase
    private static final Scanner scanner = new Scanner(System.in);

    // Constructor completo
    public Proveedor(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.CtaCte = ctaCte;
    }

    // Constructor vacío
    public Proveedor() 
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.CtaCte = null;
    }

    // Getter y setter para CuentaCorriente
    public CuentaCorriente getCtaCte() { return CtaCte; }
    public void setCtaCte(CuentaCorriente ctaCte) { this.CtaCte = ctaCte; }

    // Método para ver la cuenta corriente
    public void verCtaCte()
    {
        if (CtaCte != null) 
        {
            System.out.println(CtaCte);
        } 
            else 
            {
                System.out.println("NO TIENE CUENTA CORRIENTE ASOCIADA.");
            }
    }

    // Métodos abstractos de Persona implementados usando ArchivosProveedor

    @Override
    public Persona buscaPersona(int dni)
    {
        Proveedor proveedor = ArchivosProveedor.buscarProveedorPorDni(dni);
        if (proveedor != null) 
        {
            System.out.println(proveedor);
        } 
            else 
            {
                System.out.println("NO SE ENCONTRO PROVEEDOR CON ESE DNI.");
            }
            return proveedor;
    }

    @Override
    public void datosPersona()
    {
        System.out.println(this);
    }

    @Override
    public void listarPersona()
    {
        ArrayList<Proveedor> lista = ArchivosProveedor.leerProveedores();
        if (lista.isEmpty()) 
        {
            System.out.println("NO HAY PROVEEDORES REGISTRADOS.");
        } 
            else 
            {
                for (Proveedor p : lista) 
                {
                    System.out.println(p);
                    System.out.println("----------------------------------------");
                }
            }
    }

    @Override
    public Persona altaPersona()
    {
        System.out.print("INGRESE DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("INGRESE NOMBRES: ");
        String nombres = scanner.nextLine();
        System.out.print("INGRESE APELLIDOS: ");
        String apellidos = scanner.nextLine();
        System.out.print("INGRESE TELEFONO: ");
        String telefono = scanner.nextLine();
        System.out.print("INGRESE DIRECCION: ");
        String direccion = scanner.nextLine();
        System.out.print("INGRESE LOCALIDAD: ");
        String localidad = scanner.nextLine();
        System.out.print("INGRESE PROVINCIA: ");
        String provinciaStr = scanner.nextLine();
        Provincia provincia = Provincia.valueOf(provinciaStr.toUpperCase());
        System.out.print("INGRESE SEXO: ");
        String sexoStr = scanner.nextLine();
        Sexo sexo = Sexo.valueOf(sexoStr.toUpperCase());
        System.out.print("INGRESE FECHA DE NACIMIENTO (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());

        // Para la cuenta corriente puedes pedir datos o dejarla en null
        CuentaCorriente ctaCte = null;

        Proveedor proveedor = new Proveedor(dni, true, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento, ctaCte);
        ArchivosProveedor.guardarProveedor(proveedor);
        System.out.println("PROVEEDOR DADO DE ALTA CORRECTAMENTE.");
        return proveedor;
    }

    @Override
    public void bajaPersona(Persona persona)
    {
        if (persona instanceof Proveedor) 
        {
            boolean eliminado = ArchivosProveedor.eliminarProveedor(((Proveedor) persona).getDni());
            if (eliminado) 
            {
                System.out.println("PROVEEDOR ELIMINADO CORRECTAMENTE.");
            } 
                else 
                {
                    System.out.println("NO SE ENCONTRO PROVEEDOR PARA ELIMINAR.");
                }
        }
    }

    @Override
    public void modificarPersona()
    {
        System.out.print("INGRESE DNI DEL PROVEEDOR A MODIFICAR: ");
        int dni = Integer.parseInt(scanner.nextLine());
        Proveedor proveedor = ArchivosProveedor.buscarProveedorPorDni(dni);
        if (proveedor != null) 
        {
            System.out.print("NUEVOS NOMBRES (" + proveedor.getNombres() + "): ");
            String nombres = scanner.nextLine();
            if (!nombres.isEmpty()) proveedor.setNombres(nombres);

            System.out.print("NUEVOS APELLIDOS (" + proveedor.getApellidos() + "): ");
            String apellidos = scanner.nextLine();
            if (!apellidos.isEmpty()) proveedor.setApellidos(apellidos);

            boolean modificado = ArchivosProveedor.modificarProveedor(proveedor);
            if (modificado) 
            {
                System.out.println("PROVEEDOR MODIFICADO CORRECTAMENTE.");
            } 
                else 
                {
                    System.out.println("NO SE PUDO MODIFICAR EL PROVEEDOR.");
                }
        } 
            else 
            {
                System.out.println("NO SE ENCONTRO PROVEEDOR CON ESE DNI.");
            }
    }

    @Override
    public String toString()
    {
        return "PROVEEDOR: " + getNombres() + " " + getApellidos() +
               "\nDNI: " + getDni() +
               "\nTELEFONO: " + getTelefono() +
               "\nDIRECCION: " + getDireccion() +
               "\nLOCALIDAD: " + getLocalidad() +
               "\nPROVINCIA: " + getProvincia() +
               "\nSEXO: " + getSexo() +
               "\nFECHA NACIMIENTO: " + getFechaNacimiento() +
               "\nCUENTA CORRIENTE: " + (CtaCte != null ? CtaCte : "NINGUNA");
    }
}