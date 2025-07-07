import Clases.*;
import Enumerados.*;

public class Main
{
    public static void main(String[] args)
    {
        
        //Producto P1 = new Producto(4, "Collar", "xl", 2, "ardidas", "cuero de perro", Categoria.ACCESORIO, 0);
        Menus menu = new Menus();

        FormaPago credito = FormaPago.escogerFomaPago();
        System.out.println(credito);
    }
}