package Principal;

public class Excepciones
{
    public static class ProductoInvalidoException extends IllegalArgumentException
    {
        public ProductoInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class ListaProductosNulaException extends IllegalArgumentException
    {
        public ListaProductosNulaException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class ProductoNoEncontradoException extends RuntimeException
    {
        public ProductoNoEncontradoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class ProductoArchivoException extends Exception
    {
        public ProductoArchivoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class DatoInvalidoException extends RuntimeException
    {
        public DatoInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class FechaInvalidaException extends RuntimeException
    {
        public FechaInvalidaException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class SalarioInvalidoException extends RuntimeException
    {
        public SalarioInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class LegajoInvalidoException extends RuntimeException
    {
        public LegajoInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class LegajoDuplicadoException extends RuntimeException
    {
        public LegajoDuplicadoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class DniDuplicadoException extends RuntimeException
    {
        public DniDuplicadoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class NumeroInvalidoException extends RuntimeException
    {
        public NumeroInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class DniInvalidoException extends RuntimeException
    {
        public DniInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    // Nuevas excepciones para validación de datos de entrada
    public static class NombreInvalidoException extends RuntimeException
    {
        public NombreInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class TelefonoInvalidoException extends RuntimeException
    {
        public TelefonoInvalidoException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class LocalidadInvalidaException extends RuntimeException
    {
        public LocalidadInvalidaException(String mensaje)
        {
            super(mensaje);
        }
    }

    public static class DireccionInvalidaException extends RuntimeException
    {
        public DireccionInvalidaException(String mensaje)
        {
            super(mensaje);
        }
    }

    // Métodos de validación
    public static void validarNombre(String texto, String campo) throws NombreInvalidoException
    {
        if (texto == null || texto.trim().isEmpty())
        {
            throw new NombreInvalidoException("EL " + campo + " NO PUEDE ESTAR VACIO");
        }
        
        // Verificar que no contenga números
        if (texto.matches(".*\\d.*"))
        {
            throw new NombreInvalidoException("EL " + campo + " NO PUEDE CONTENER NUMEROS");
        }
        
        // Verificar que no contenga caracteres especiales (excepto espacios, tildes y ñ)
        if (!texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"))
        {
            throw new NombreInvalidoException("EL " + campo + " SOLO PUEDE CONTENER LETRAS Y ESPACIOS");
        }
    }

    public static void validarTelefono(String telefono) throws TelefonoInvalidoException
    {
        if (telefono == null || telefono.trim().isEmpty())
        {
            throw new TelefonoInvalidoException("EL TELEFONO NO PUEDE ESTAR VACIO");
        }
        
        // Verificar que solo contenga números, espacios, guiones y paréntesis
        if (!telefono.matches("^[0-9\\s\\-\\(\\)\\+]+$"))
        {
            throw new TelefonoInvalidoException("EL TELEFONO SOLO PUEDE CONTENER NUMEROS, ESPACIOS, GUIONES Y PARENTESIS");
        }
        
        // Verificar que tenga al menos 6 dígitos
        String soloNumeros = telefono.replaceAll("[^0-9]", "");
        if (soloNumeros.length() < 6)
        {
            throw new TelefonoInvalidoException("EL TELEFONO DEBE TENER AL MENOS 6 DIGITOS");
        }
    }

    public static void validarLocalidad(String localidad) throws LocalidadInvalidaException // (aca tambien hice trampita jiji)
    {
        if (localidad == null || localidad.trim().isEmpty())
        {
            throw new LocalidadInvalidaException("LA LOCALIDAD NO PUEDE ESTAR VACIA");
        }
        
        // Verificar que no contenga números
        if (localidad.matches(".*\\d.*"))
        {
            throw new LocalidadInvalidaException("LA LOCALIDAD NO PUEDE CONTENER NUMEROS");
        }
        
        // Verificar que solo contenga letras, espacios y algunos caracteres especiales permitidos
        if (!localidad.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s\\.\\-]+$"))
        {
            throw new LocalidadInvalidaException("LA LOCALIDAD SOLO PUEDE CONTENER LETRAS, ESPACIOS, PUNTOS Y GUIONES");
        }
    }

    public static void validarDireccion(String direccion) throws DireccionInvalidaException
    {
        if (direccion == null || direccion.trim().isEmpty())
        {
            throw new DireccionInvalidaException("LA DIRECCION NO PUEDE ESTAR VACIA");
        }
        
        // La dirección puede contener letras, números, espacios y algunos caracteres especiales
        if (!direccion.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s\\.\\-\\,\\#\\°]+$"))
        {
            throw new DireccionInvalidaException("LA DIRECCION CONTIENE CARACTERES NO VALIDOS");
        }
    }

    public static boolean verificarEntero (String numero)
    {
        try
        {
            Integer.parseInt(numero);
            return true;
        }
        catch (NumberFormatException error)
        {
            System.out.println("ERROR: " + error.getMessage());
            return false;
        }
    }
}