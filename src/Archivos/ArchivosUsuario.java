package Archivos;

import Clases.Usuario;
import java.io.*;
import java.util.ArrayList;

/*
Clase utilitaria para gestionar la persistencia de usuarios en un archivo CSV.
*/
public class ArchivosUsuario
{
    // Nombre del archivo donde se guardan los usuarios
    private static final String ARCHIVO = "usuarios.csv";

    /*
    Guarda un usuario en el archivo CSV (agrega al final).
    */
    public static void guardarUsuario(Usuario usuario)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            // Escribe usuario y clave separados por coma
            bw.write
            (
                usuario.getUsuario() + "," +
                usuario.getClave()
            );
            bw.newLine();
        }
            catch (IOException e)
            {
                System.out.println("Error al guardar usuario: " + e.getMessage());
            }
    }

    /*
    Lee todos los usuarios del archivo y los devuelve en una lista.
    */
    public static ArrayList<Usuario> leerUsuarios()
    {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                String[] datos = linea.split(",");
                // Crea el usuario usando el constructor simple (usuario, clave)
                Usuario u = new Usuario
                (
                    datos[0], // usuario
                    datos[1]  // clave
                );
                lista.add(u);
            }
        }
            catch (FileNotFoundException e)
            {
                // Si el archivo no existe, retorna lista vacía
            }
                catch (IOException ex)
                {
                    System.out.println("Error al leer usuarios: " + ex.getMessage());
                }
                return lista;
    }

    /*
    Busca un usuario por nombre de usuario.
    */
    public static Usuario buscarPorNombreUsuario(String usuario)
    {
        for (Usuario u : leerUsuarios())
        {
            if (u.getUsuario().equalsIgnoreCase(usuario))
            {
                return u;
            }
        }
        return null;
    }

    /*
    Modifica un usuario existente (por nombre de usuario).
    */
    public static boolean modificarUsuario(Usuario usuarioModificado)
    {
        ArrayList<Usuario> lista = leerUsuarios();
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getUsuario().equalsIgnoreCase(usuarioModificado.getUsuario()))
            {
                lista.set(i, usuarioModificado);
                encontrado = true;
                break;
            }
        }
            if (encontrado)
            {
                escribirListaCompleta(lista);
            }
            return encontrado;
    }

    /*
    Elimina un usuario por nombre de usuario.
    */
    public static boolean eliminarUsuario(String usuario)
    {
        ArrayList<Usuario> lista = leerUsuarios();
        boolean eliminado = lista.removeIf(u -> u.getUsuario().equalsIgnoreCase(usuario));
        if (eliminado)
        {
            escribirListaCompleta(lista);
        }
        return eliminado;
    }

    /*
    Método auxiliar para sobrescribir el archivo completo con la lista de usuarios.
    */
    private static void escribirListaCompleta(ArrayList<Usuario> lista)
    {
        try (FileWriter fw = new FileWriter(ARCHIVO, false);
            BufferedWriter bw = new BufferedWriter(fw))
        {
            for (Usuario usuario : lista)
            {
                bw.write
                (
                    usuario.getUsuario() + "," +
                    usuario.getClave()
                );
                bw.newLine();
            }
        }
            catch (IOException e)
            {
                System.out.println("Error al escribir usuarios: " + e.getMessage());
            }
    }
}