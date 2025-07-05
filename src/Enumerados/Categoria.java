package Enumerados;

public enum Categoria
{
    INDUMENTARIA,
    CALZADO,
    ACCESORIO;

    public String getCategoria()
    {
        return this.name();
    }
}