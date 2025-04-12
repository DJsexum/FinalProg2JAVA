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

  public static class ProductoNoEncontradoExcepcion extends RuntimeException //Esto es una excepcion personalizada no comprobada, osea una RuntimeException (me dijo un pajarito)
  {
    public ProductoNoEncontradoExcepcion(String mensaje)
    {
      super(mensaje);
    }
  }
}