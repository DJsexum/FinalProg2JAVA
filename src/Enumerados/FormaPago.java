package Enumerados;

import java.util.Scanner;
import Clases.*;

public enum FormaPago
{

    EFECTIVO("Efectivo."),
    TARJETA_DEBITO("Tarjeta de debito."),
    TARJETA_CREDITO("Tarjeta de credito."),
    TRANSFERENCIA("Transferencia."),
    CUENTA_CORRIENTE("Cuenta corriente.");

    private final String detallePago;

    private static int castearEntero (String mensaje, String dato) {
        int num = 0; boolean flag = true;
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.print(mensaje);
            dato = teclado.nextLine();
            if(Excepciones.verificarEntero(dato)) {
                num = Integer.parseInt(dato);
                flag = true;
            } else {
                flag = false;
            }
        }while(flag == false);

        return num;
    }

    FormaPago (String detallePago)
    {
        this.detallePago = detallePago;
    }

    public String obetenerFormaPago()
    {
        return this.detallePago;
    }

    public static FormaPago escogerFomaPago ()
    {
        int seleccion; String seleccionAux = null;

        do
        {
            System.out.println("Formas de pago disponibles:");
            for(FormaPago forma: FormaPago.values())
            {
                System.out.println(forma.ordinal() + 1 + "." + forma.obetenerFormaPago());
            }
            seleccion = castearEntero("Seleccione alguna forma de pago: ", seleccionAux);
        }
        while(seleccion < 1 || seleccion > 5);

        return FormaPago.values()[seleccion - 1];
    }
}