package Clases;

import Enumerados.*;
import Archivos.ArchivosUsuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/*
Hereda de Persona y agrega los atributos usuario, clave y propietario. 
*/
public class Usuario extends Persona
{
    // Atributos propios de Usuario
    private String usuario; // Nombre de usuario para iniciar sesion
    private String clave; // Clave de acceso del usuario
    private Persona propietario; // Persona asociada a este usuario (puede ser null)

    // Scanner unico y estatico para toda la clase para evitar fugas de recursos
    private static final Scanner scanner = new Scanner(System.in);

    /*
    Constructor vacio: necesario para poder crear instancias sin argumentos.
    */
    public Usuario() 
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.usuario = "";
        this.clave = "";
        this.propietario = null;
    }

    /*
    Constructor completo: para crear un usuario con todos los datos de Persona y los propios.
    */
    public Usuario(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, String usuario, String clave, Persona propietario)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);
        this.usuario = usuario;
        this.clave = clave;
        this.propietario = propietario;
    }

    /*
    Constructor simple: para persistencia, solo usuario y clave.
    El resto de los datos de Persona se inicializan vacios o en null.
    */
    public Usuario(String usuario, String clave)
    {
        super(0, false, "", "", "", "", "", null, null, null);
        this.usuario = usuario;
        this.clave = clave;
        this.propietario = null;
    }

    // Getters y setters

    /* Devuelve el nombre de usuario. */
    public String getUsuario() {
        return usuario;
    }

    /* Asigna el nombre de usuario. */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /* Devuelve la clave del usuario. */
    public String getClave() {
        return clave;
    }

    /* Asigna la clave del usuario. */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /* Devuelve la persona propietaria asociada. */
    public Persona getPropietario() {
        return propietario;
    }

    /* Asigna la persona propietaria asociada. */
    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    /*
    Alta de usuario: pide datos por consola y guarda el usuario en el archivo.
    */
    public Usuario altaUsuario()
    {
        System.out.print("INGRESE NOMBRE DE USUARIO: ");
        this.usuario = scanner.nextLine();
        System.out.print("INGRESE CLAVE: ");
        this.clave = scanner.nextLine();
        // Si quieres pedir propietario, puedes agregarlo aqui

        ArchivosUsuario.guardarUsuario(this); // Guarda el usuario en el archivo
        System.out.println("USUARIO DADO DE ALTA CORRECTAMENTE.");
        return this;
    }

    /*
    Baja de usuario: elimina el usuario del archivo por su nombre de usuario.
    */
    public boolean bajaUsuario()
    {
        boolean eliminado = ArchivosUsuario.eliminarUsuario(this.usuario);
        if (eliminado)
            System.out.println("USUARIO ELIMINADO CORRECTAMENTE.");
            else
                System.out.println("NO SE ENCONTRO EL USUARIO PARA ELIMINAR.");
            return eliminado;
    }

    /*
    Modificar usuario: permite cambiar la clave y guarda el cambio en el archivo.
    */
    public boolean modificarUsuario()
    {
        System.out.print("NUEVA CLAVE: ");
        this.clave = scanner.nextLine();
        // Puedes agregar mas campos a modificar si lo deseas

        boolean modificado = ArchivosUsuario.modificarUsuario(this);
        if (modificado)
            System.out.println("USUARIO MODIFICADO CORRECTAMENTE.");
            else
                System.out.println("NO SE PUDO MODIFICAR EL USUARIO.");
            return modificado;
    }

    /*
    Buscar usuario por nombre de usuario (estatico).
    Solo retorna el usuario, no imprime mensajes.
    */
    public static Usuario buscarUsuario(String usuario)
    {
        return ArchivosUsuario.buscarPorNombreUsuario(usuario);
    }

    /*
    Listar todos los usuarios registrados (estatico).
    */
    public static ArrayList<Usuario> listarUsuarios()
    {
        ArrayList<Usuario> lista = ArchivosUsuario.leerUsuarios();
        if (lista.isEmpty())
            System.out.println("NO HAY USUARIOS REGISTRADOS.");
                else
                    for (Usuario u : lista)
                        System.out.println(u);
                return lista;
    }

    @Override
    public Persona buscaPersona(int dni)
    {
        System.out.println("BUSCAR USUARIO POR DNI NO IMPLEMENTADO.");
        return null;
    }

    /*
    Muestra los datos del usuario.
    */
    @Override
    public void datosPersona()
    {
        System.out.println(this);
    }

    /*
    Lista todos los usuarios.
    */
    @Override
    public void listarPersona()
    {
        listarUsuarios();
    }

    /*
    Alta de usuario (llama a altaUsuario).
    */
    @Override
    public Persona altaPersona()
    {
        return altaUsuario();
    }

    /*
    Baja de usuario (llama a bajaUsuario).
    */
    @Override
    public void bajaPersona(Persona persona)
    {
        if (persona instanceof Usuario)
            ((Usuario) persona).bajaUsuario();
    }

    /*
    Modificar usuario (llama a modificarUsuario).
    */
    @Override
    public void modificarPersona()
    {
        modificarUsuario();
    }

    /*
    Devuelve una representacion en texto del usuario.
    */
    @Override
    public String toString()
    {
        return "USUARIO: " + usuario +
               "\nNOMBRE: " + getNombres() +
               "\nAPELLIDO: " + getApellidos() +
               "\nDNI: " + getDni() +
               "\nTELEFONO: " + getTelefono() +
               "\nDIRECCION: " + getDireccion() +
               "\nLOCALIDAD: " + getLocalidad() +
               "\nPROVINCIA: " + getProvincia() +
               "\nSEXO: " + getSexo() +
               "\nFECHA NACIMIENTO: " + getFechaNacimiento();
    }

    /*
    Muestra los datos completos del usuario (para el menu).
    */
    public void mostrarDatos()
    {
        System.out.println(this);
    }
}