import Enumerados.Sexo;

public class Main
{
    public static void main(String[] args)
    {
        Sexo sexo = Sexo.obtenerSexoDesdeConsola();
        System.out.println("Sexo seleccionado: " + sexo);
    }
}