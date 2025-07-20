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