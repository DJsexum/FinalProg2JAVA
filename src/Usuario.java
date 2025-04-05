import java.util.ArrayList;

public class Usuario
{
    private String Usuario;
    private String Clave;
    private Persona Propietario;

    public Usuario(String usuario, String clave, Persona propietario)
    {
        this.Usuario = usuario;
        this.Clave = clave;
        this.Propietario = propietario;
    }

    // Metodos de Usuario
    public Usuario altaUsuario() { /* Implementación */ return null; } //
    public void bajaUsuario() { /* Implementación */ } // Deberia ser un boolean, ya que solo devuelve true o false
    public void modificarUsuario(Usuario usuario) { /* Implementación */ } // Basicamente funciona como la funcion del fuxture de modificar un dato, pero con los datos de usuario o cliente (hacer controles de excepciones para evitar errores)
    public Usuario buscarUsuario() { /* Implementación */ return null; } // Podemos hacer que el usuario se de las opciones de buscar por dni y decir si es empleado o cliente
    public ArrayList<Usuario> listarUsuarios() { /* Implementación */ return null; } // Mostrar los usuarios por separado, por un lado empleados, y por otro lado los clientes
}