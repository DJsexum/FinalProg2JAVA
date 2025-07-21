package Clases;

import Archivos.ArchivosUsuario;
import java.util.ArrayList;
import java.util.Scanner;

/*
Clase Usuario simple con solo nombre de usuario y contraseña.
*/
public class Usuario
{
    // Atributos
    private String usuario; // Nombre de usuario (solo letras)
    private String clave; // Clave de acceso (cualquier caracter, case-sensitive)

    // Scanner unico y estatico para toda la clase
    private static final Scanner scanner = new Scanner(System.in);

    /*
    Constructor vacio
    */
    public Usuario() 
    {
        this.usuario = "";
        this.clave = "";
    }

    /*
    Constructor con parametros
    */
    public Usuario(String usuario, String clave)
    {
        this.usuario = usuario;
        this.clave = clave;
    }

    // Getters y setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    /*
    Valida que el nombre de usuario solo contenga letras y espacios (aca hice trampita jiji)
    */
    private static boolean esNombreValido(String nombre)
    {
        if (nombre == null || nombre.trim().isEmpty()) 
        {
            return false;
        }
        
        // Solo permite letras, espacios y acentos
        return nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    /*
    Alta de usuario: pide datos por consola y guarda el usuario
    */
    public Usuario altaPersona()
    {
        String nombreUsuario;
        
        // Validar nombre de usuario
        do {
            System.out.print("INGRESE NOMBRE DE USUARIO (SOLO LETRAS): ");
            nombreUsuario = scanner.nextLine().trim();
            
            if (!esNombreValido(nombreUsuario)) {
                System.out.println("ERROR: EL NOMBRE SOLO PUEDE CONTENER LETRAS Y ESPACIOS.");
                continue;
            }
            
            // Verificar que no exista
            if (buscarUsuario(nombreUsuario) != null) {
                System.out.println("YA EXISTE UN USUARIO CON ESE NOMBRE.");
                continue;
            }
            
            break;
            
        } while (true);
        
        this.usuario = nombreUsuario;
        
        // Solicitar clave
        System.out.print("INGRESE CLAVE: ");
        this.clave = scanner.nextLine();
        
        if (this.clave.trim().isEmpty()) {
            System.out.println("ERROR: LA CLAVE NO PUEDE ESTAR VACIA.");
            return null;
        }

        ArchivosUsuario.guardarUsuario(this);
        System.out.println("USUARIO CREADO CORRECTAMENTE.");
        return this;
    }

    /*
    Baja de usuario
    */
    public void bajaPersona(Usuario usuario)
    {
        boolean eliminado = ArchivosUsuario.eliminarUsuario(usuario.getUsuario());
        if (eliminado)
            System.out.println("USUARIO ELIMINADO CORRECTAMENTE.");
        else
            System.out.println("NO SE ENCONTRO EL USUARIO PARA ELIMINAR.");
    }

    /*
    Modificar usuario
    */
    public void modificarPersona()
    {
        System.out.print("INGRESE NOMBRE DE USUARIO A MODIFICAR: ");
        String nombreMod = scanner.nextLine();
        Usuario usuarioMod = buscarUsuario(nombreMod);
        
        if (usuarioMod != null)
        {
            System.out.print("NUEVA CLAVE: ");
            String nuevaClave = scanner.nextLine();
            
            if (nuevaClave.trim().isEmpty()) {
                System.out.println("ERROR: LA CLAVE NO PUEDE ESTAR VACIA.");
                return;
            }
            
            usuarioMod.setClave(nuevaClave);
            
            boolean modificado = ArchivosUsuario.modificarUsuario(usuarioMod);
            if (modificado)
                System.out.println("USUARIO MODIFICADO CORRECTAMENTE.");
            else
                System.out.println("NO SE PUDO MODIFICAR EL USUARIO.");
        }
        else
        {
            System.out.println("NO SE ENCONTRO USUARIO CON ESE NOMBRE.");
        }
    }

    /*
    Buscar usuario por nombre
    */
    public static Usuario buscarUsuario(String usuario)
    {
        return ArchivosUsuario.buscarPorNombreUsuario(usuario);
    }

    /*
    Buscar usuario con mensaje
    */
    public Usuario buscaPersona(String nombreUsuario)
    {
        Usuario encontrado = buscarUsuario(nombreUsuario);
        if (encontrado != null)
        {
            encontrado.mostrarDatos();
        }
        else
        {
            System.out.println("NO SE ENCONTRO USUARIO CON ESE NOMBRE.");
        }
        return encontrado;
    }

    /*
    Listar usuarios
    */
    public void listarPersona()
    {
        ArrayList<Usuario> lista = ArchivosUsuario.leerUsuarios();
        if (lista.isEmpty())
        {
            System.out.println("NO HAY USUARIOS REGISTRADOS.");
        }
        else
        {
            System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
            System.out.println("│                       LISTA DE USUARIOS                      │");
            System.out.println("├─────────────────────────┬────────────────────────────────────┤");
            System.out.println("│       USUARIO           │              CLAVE                 │");
            System.out.println("├─────────────────────────┼────────────────────────────────────┤");
            
            for (Usuario u : lista)
            {
                String claveOculta = "*".repeat(u.getClave().length());
                System.out.printf("│ %-23s │ %-34s │\n", u.getUsuario(), claveOculta);
            }
            
            System.out.println("└─────────────────────────┴────────────────────────────────────┘");
        }
    }

    /*
    Verificar si hay usuarios registrados
    */
    public static boolean hayUsuarios()
    {
        ArrayList<Usuario> usuarios = ArchivosUsuario.leerUsuarios();
        return !usuarios.isEmpty();
    }

    /*
    Verificar credenciales de usuario
    */
    public static boolean verificarCredenciales(String nombreUsuario, String clave)
    {
        Usuario usuario = buscarUsuario(nombreUsuario);
        if (usuario != null)
        {
            // Comparacion case-sensitive exacta
            return usuario.getClave().equals(clave);
        }
        return false;
    }

    /*
    Mostrar datos del usuario
    */
    public void mostrarDatos()
    {
        System.out.println("\n┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                     DATOS DEL USUARIO                        │");
        System.out.println("├──────────────────────────────────────────────────────────────┤");
        System.out.printf("│ USUARIO: %-47s │\n", this.usuario);
        System.out.printf("│ CLAVE: %-49s │\n", "*".repeat(this.clave.length()));
        System.out.println("└──────────────────────────────────────────────────────────────┘");
    }

    @Override
    public String toString()
    {
        return "Usuario: " + usuario + " | Clave: " + "*".repeat(clave.length());
    }
}