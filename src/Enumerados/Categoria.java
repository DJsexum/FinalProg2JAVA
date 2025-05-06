package Enumerados;

public enum Categoria
{
    INDUMENTARIA,
    CALZADO,
    ACCESORIO;

    public String obtenerCategoria()
    {
        return this.name();
    }
}