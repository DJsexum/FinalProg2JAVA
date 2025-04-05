package Enumerados;

public enum Provincia
{
    BUENOS_AIRES (1, "BUENOS AIRES"),
    CATAMARCA (2, "CATAMARCA"),
    CHACO (3, "CHACO"),
    CHUBUT (4, "CHUBUT"),
    CORDOBA (5, "CORDOBA"),
    CORRIENTES (6, "CORRIENTES"),
    ENTRE_RIOS (7, "ENTRE RIOS"),
    FORMOSA (8, "FORMOSA"),
    JUJUY (9, "JUJUY"),
    LA_PAMPA (10, "LA PAMPA"),
    LA_RIOJA (11, "LA RIOJA"),
    MENDOZA (12, "MENDOZA"),
    MISIONES (13, "MISIONES"),
    NEUQUEN (14, "NEUQUEN"),
    RIO_NEGRO (15, "RIO NEGRO"),
    SALTA (16, "SALTA"),
    SAN_JUAN (17, "SAN JUAN"),
    SAN_LUIS (18, "SAN LUIS"),
    SANTA_CRUZ (19, "SANTA CRUZ"),
    SANTA_FE (20, "SANTA FE"),
    SANTIAGO_DEL_ESTERO (21, "SANTIAGO DEL ESTERO"),
    TIERRA_DEL_FUEGO (22, "TIERRA DEL FUEGO"),
    TUCUMAN (23, "TUCUMAN"),;

    Provincia(int i, String s)
    {}

    public String obtenerProvincia(int i, String s)
    {
        return this.name();
    }

    public String obtenerProvinciaPorCodigo(int codigo)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.ordinal() + 1 == codigo)
            {
                return provincia.name();
            }
        }
        return null; // O lanzar una exception si el codigo no existe
    }

    public String obtenerProvinciaPorNombre(String nombre)
    {
        for (Provincia provincia : Provincia.values())
        {
            if (provincia.name().equalsIgnoreCase(nombre))
            {
                return provincia.name();
            }
        }
        return null; // O lanzar una excepcion si el nombre no existe
    }
}

// Fijate que chota tengo que hacer aca para que me quede bien, por que no se si esta bien esto que hice, es para buscar por nombre o por numero