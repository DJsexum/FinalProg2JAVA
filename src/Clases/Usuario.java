package Clases;

import Enumerados.Provincia;
import Enumerados.Sexo;
import java.time.LocalDate;
import java.util.ArrayList;

public class Usuario extends Persona
{
    private String usuario;
    private String clave;
    private Persona propietario;

    public Usuario(int dni, boolean activo, String nombres, String apellidos, String telefono, String direccion, String localidad, Provincia provincia, Sexo sexo, LocalDate fechaNacimiento, String usuario, String clave, Persona propietario)
    {
        super(dni, activo, nombres, apellidos, telefono, direccion, localidad, provincia, sexo, fechaNacimiento);

        this.usuario = usuario;
        this.clave = clave;
        this.propietario = propietario;
    }

    // Metodos de Clases.Usuario
    public Usuario altaUsuario() { /* Implementación */ return null; } //
    public void bajaUsuario() { /* Implementación */ } // Deberia ser un boolean, ya que solo devuelve true o false
    public void modificarUsuario(Usuario usuario) { /* Implementación */ } // Basicamente funciona como la funcion del fuxture de modificar un dato, pero con los datos de usuario o cliente (hacer controles de excepciones para evitar errores)
    public Usuario buscarUsuario() { /* Implementación */ return null; } // Podemos hacer que el usuario se de las opciones de buscar por dni y decir si es empleado o cliente
    public ArrayList<Usuario> listarUsuarios() { /* Implementación */ return null; } // Mostrar los usuarios por separado, por un lado empleados, y por otro lado los clientes
}