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
    /*
    Lo de arriba, "detallePago" es private para encapsular la información
    y "final" para asegurar que el detalle asociado a cada constante del enum
    sea INMUTABLE. Una vez asignado en el constructor, su valor no puede cambiar.
    Es crucial para que la DEFINICIÓN de la forma de pago sea fija,
    aunque la SELECCIÓN del usuario pueda variar.
    */
    private static int castearEntero (String mensaje, String dato) //Castear un dato es el proceso de convertir un valor de un tipo de dato a otro tipo de dato.
    {
        int num = 0;
        boolean flag = true;
        Scanner datoAceptado = new Scanner(System.in);

        do
        {
            System.out.print(mensaje);
            dato = datoAceptado.nextLine();

            if(Excepciones.verificarEntero(dato))
            {
                num = Integer.parseInt(dato); // Convertir el String a un entero (funciona algo asi como el ASCCII, pero con numeros)
                flag = true;
            }
                else
                {
                    flag = false;
                }
        }
        while(flag == false);

        return num;
    }

    FormaPago (String detallePago)
    {
        this.detallePago = detallePago;
    }

    public String getFormaPago()
    {
        return this.detallePago;
    }

    public static FormaPago escogerFormaPago ()
    {
        int seleccion;
        String seleccionAux = null;

        do
        {
            System.out.println("Formas de pago disponibles:");

            for(FormaPago forma : FormaPago.values())
            {
                System.out.println(forma.ordinal() + 1 + "." + forma.getFormaPago());
            }
            seleccion = castearEntero("Seleccione alguna forma de pago: ", seleccionAux);
        }
        while(seleccion < 1 || seleccion > 5);

        return FormaPago.values() [seleccion - 1];
    }
}