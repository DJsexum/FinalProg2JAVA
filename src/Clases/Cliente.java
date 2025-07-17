package Clases;

import Enumerados.*;
import Archivos.ArchivosCliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Persona
{
    private CuentaCorriente ctaCte;
    private Scanner scanner = new Scanner(System.in);
    private int codigo;

    // Constructor completo
    public Cliente(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, CuentaCorriente ctaCte)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.ctaCte = ctaCte;
        this.codigo = dni;
    }

    // Constructor vacío
    public Cliente()
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.ctaCte = null;
        this.codigo = 0;
    }

    public int getCodigo()
    {
        return this.codigo;
    }

    // Métodos de cuenta corriente
    public void verCtaCte()
    {
        if (ctaCte != null)
        {
            System.out.println(quitarAcentos("DETALLES DE LA CUENTA CORRIENTE:").toUpperCase());
            System.out.println(quitarAcentos("SALDO ACTUAL: ").toUpperCase() + ctaCte.getSaldo());
            ctaCte.verMovimientos();
        }
            else
            {
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void depositarEnCtaCte(double monto, String descripcion)
    {
        // Primero se verifica si el cliente tiene una cuenta corriente asociada
        if (ctaCte != null)
        {
            // Crea un nuevo movimiento de tipo HABER (deposito)
            Movimiento movimiento = new Movimiento
            (
                1, // Codigo del movimiento
                "HABER", // Tipo de movimiento: HABER indica ingreso (aca no lo entendi, pero me djieron que lo deje asi)
                descripcion, // Detalle o descripción del deposito
                0.0, // Monto debe
                monto, // Monto haber (el valor depositado)
                ctaCte.getSaldo() + monto, // Saldo resultante despues del deposito (resultante?? quien so)
                LocalDate.now() // Fecha actual del movimiento
            );
            // Agrega el movimiento a la cuenta corriente del cliente
            ctaCte.agregarMovimiento(movimiento);
            // Muestra mensaje de confirmación del deposito
            System.out.println(quitarAcentos("DEPOSITO REALIZADO: ").toUpperCase() + monto + " (" + quitarAcentos(descripcion).toUpperCase() + ")");
        }
            else
            {
                // Si no tiene cuenta corriente, muestra el mensajito
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void retirarDeCtaCte(double monto, String descripcion)
    {
        // Verifica si el cliente tiene una cuenta corriente asociada
        if (ctaCte != null)
        {
            // Verifica si el saldo es suficiente para realizar el retiro
            if (ctaCte.getSaldo() >= monto)
            {
                // Crea un nuevo movimiento de tipo DEBE (retiro) (lo mismo de lo de arriba, no me explicaron como funciona, asi que dios sabra)
                Movimiento movimiento = new Movimiento
                (
                    2, // Código del movimiento
                    "DEBE", // Tipo de movimiento: DEBE indica egreso
                    descripcion, // Detalle o descripción del retiro
                    monto, // Monto debe (el valor retirado)
                    0.0, // Monto haber (no se usa en un retiro)
                    ctaCte.getSaldo() - monto, // Saldo resultante después del retiro
                    LocalDate.now() // Fecha actual del movimiento
                );
                // Agrega el movimiento a la cuenta corriente del cliente
                ctaCte.agregarMovimiento(movimiento);
                // Muestra mensaje de confirmación del retiro
                System.out.println(quitarAcentos("RETIRO REALIZADO: ").toUpperCase() + monto + " (" + quitarAcentos(descripcion).toUpperCase() + ")");
            }
                else
                {
                    // Si el saldo es insuficiente, muestra mensajito de advertencia
                    System.out.println(quitarAcentos("SALDO INSUFICIENTE PARA REALIZAR EL RETIRO.").toUpperCase());
                }
        }
            else
            {
                // Si no tiene cuenta corriente, muestra mensaje de advertencia
                System.out.println(quitarAcentos("ESTE CLIENTE NO TIENE CUENTA CORRIENTE ASOCIADA.").toUpperCase());
            }
    }

    public void verResumenCliente()
    {
        System.out.println(this);
        verCtaCte();
    }

    public void setCtaCte(CuentaCorriente ctaCte)
    {
        this.ctaCte = ctaCte;
    }

    public CuentaCorriente getCtaCte()
    {
        return this.ctaCte;
    }

    // Método estático para buscar cliente por código (dni)
    public static Cliente buscarClientePorCodigo(int codigo)
    {
        ArrayList<Cliente> lista = ArchivosCliente.leerClientes();
        for (Cliente c : lista)
        {
            if (c.getCodigo() == codigo)
            {
                return c;
            }
        }
        return null;
    }

    // Alta de cliente
    @Override
    public Persona altaPersona()
    {
        System.out.println(quitarAcentos("=== ALTA DE NUEVO CLIENTE ===").toUpperCase());
        System.out.print(quitarAcentos("INGRESE DNI: ").toUpperCase());
        int dni = scanner.nextInt();
        scanner.nextLine();

        // Verificar si ya existe un cliente con ese DNI en archivo
        if (ArchivosCliente.buscarPorDni(dni) != null)
        {
            System.out.println(quitarAcentos("YA EXISTE UN CLIENTE CON ESE DNI.").toUpperCase());
            return null;
        }

        System.out.print(quitarAcentos("INGRESE NOMBRES: ").toUpperCase());
        String nombres = scanner.nextLine();

        System.out.print(quitarAcentos("INGRESE APELLIDOS: ").toUpperCase());
        String apellidos = scanner.nextLine();

        System.out.print(quitarAcentos("INGRESE TELEFONO: ").toUpperCase());
        String telefono = scanner.nextLine();

        System.out.print(quitarAcentos("INGRESE DIRECCION: ").toUpperCase());
        String direccion = scanner.nextLine();

        System.out.print(quitarAcentos("INGRESE LOCALIDAD: ").toUpperCase());
        String localidad = scanner.nextLine();

        Provincia provincia = Provincia.seleccionarProvincia();
        Sexo sexo = Sexo.seleccionarSexo();

        System.out.print(quitarAcentos("INGRESE FECHA DE NACIMIENTO (YYYY-MM-DD): ").toUpperCase());
        String fechaStr = scanner.nextLine();
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr);

        Cliente nuevo = new Cliente(dni, true, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento, null);
        ArchivosCliente.guardarCliente(nuevo);

        System.out.println(quitarAcentos("CLIENTE DADO DE ALTA CORRECTAMENTE.").toUpperCase());
        return nuevo;
    }

    // Listar clientes
    @Override
    public void listarPersona()
    {
        System.out.println(quitarAcentos("=== LISTADO DE CLIENTES ===").toUpperCase());
        ArrayList<Cliente> clientes = ArchivosCliente.leerClientes();
        if (clientes.isEmpty())
        {
            System.out.println(quitarAcentos("NO HAY CLIENTES REGISTRADOS.").toUpperCase());
        }
            else
            {
                for (Cliente c : clientes)
                {
                    System.out.println(c);
                }
            }
    }

    // Buscar cliente por DNI
    @Override
    public Persona buscaPersona(int dni)
    {
        Cliente encontrado = ArchivosCliente.buscarPorDni(dni);
        if (encontrado != null)
        {
            System.out.println(quitarAcentos("CLIENTE ENCONTRADO:").toUpperCase());
            System.out.println(encontrado);
        }
            else
            {
                System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI.").toUpperCase());
            }
            return encontrado;
    }

    // Modificar cliente
    @Override
    public void modificarPersona()
    {
        System.out.println(quitarAcentos("=== MODIFICAR DATOS DEL CLIENTE ===").toUpperCase());
        System.out.print(quitarAcentos("INGRESE DNI DEL CLIENTE A MODIFICAR: ").toUpperCase());
        int dni = scanner.nextInt();
        scanner.nextLine();

        Cliente clienteMod = ArchivosCliente.buscarPorDni(dni);
        if (clienteMod == null)
        {
            System.out.println(quitarAcentos("NO SE ENCONTRO CLIENTE CON ESE DNI.").toUpperCase());
            return;
        }

        System.out.print(quitarAcentos("NUEVO TELEFONO: ").toUpperCase());
        clienteMod.setTelefono(scanner.nextLine());

        System.out.print(quitarAcentos("NUEVA DIRECCION: ").toUpperCase());
        clienteMod.setDireccion(scanner.nextLine());

        System.out.print(quitarAcentos("NUEVA LOCALIDAD: ").toUpperCase());
        clienteMod.setLocalidad(scanner.nextLine());

        // Puedes agregar más campos si lo deseas

        boolean modificado = ArchivosCliente.modificarCliente(clienteMod);
        if (modificado)
        {
            System.out.println(quitarAcentos("DATOS MODIFICADOS CORRECTAMENTE.").toUpperCase());
        }
            else
            {
                System.out.println(quitarAcentos("NO SE PUDO MODIFICAR EL CLIENTE.").toUpperCase());
            }
    }

    // Baja de cliente
    @Override
    public void bajaPersona(Persona persona)
    {
        if (persona instanceof Cliente)
        {
            int dni = ((Cliente) persona).getDni();
            boolean eliminado = ArchivosCliente.eliminarCliente(dni);
            if (eliminado)
            {
                System.out.println(quitarAcentos("CLIENTE DADO DE BAJA CORRECTAMENTE.").toUpperCase());
            }
                else
                {
                    System.out.println(quitarAcentos("NO SE ENCONTRO EL CLIENTE PARA DAR DE BAJA.").toUpperCase());
                }
        }
            else
            {
                System.out.println(quitarAcentos("LA PERSONA NO ES UN CLIENTE.").toUpperCase());
            }
    }

    // Mostrar datos del cliente
    @Override
    public void datosPersona()
    {
        System.out.println(this);
    }

    // Sobrescribe toString para mostrar los datos del cliente
    @Override
    public String toString()
    {
        return "CLIENTE{" +
                "DNI=" + getDni() +
                ", NOMBRE='" + quitarAcentos(getNombres().toUpperCase()) + " " + quitarAcentos(getApellidos().toUpperCase()) + '\'' +
                ", TELEFONO='" + quitarAcentos(getTelefono().toUpperCase()) + '\'' +
                ", DIRECCION='" + quitarAcentos(getDireccion().toUpperCase()) + '\'' +
                ", LOCALIDAD='" + quitarAcentos(getLocalidad().toUpperCase()) + '\'' +
                ", PROVINCIA=" + (getProvincia() != null ? getProvincia().toString().toUpperCase() : "") +
                ", SEXO=" + (getSexo() != null ? getSexo().toString().toUpperCase() : "") +
                ", FECHA DE NACIMIENTO=" + getFechaNacimiento() +
                '}';
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