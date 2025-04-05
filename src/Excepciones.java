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
}