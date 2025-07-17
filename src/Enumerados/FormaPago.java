package Enumerados;

import java.util.Scanner;
import Principal.*;

public enum FormaPago
{
    EFECTIVO("EFECTIVO"),
    TARJETA_DEBITO("TARJETA DEBITO"),
    TARJETA_CREDITO("TARJETA CREDITO"),
    TRANSFERENCIA("TRANSFERENCIA"),
    CUENTA_CORRIENTE("CUENTA CORRIENTE");

    private final String detallePago;

    // Scanner único y estático para toda la clase (opción recomendada)
    private static final Scanner scanner = new Scanner(System.in);

    private static int castearEntero(String mensaje, String dato)
    {
        int num = 0;
        boolean flag = true;

        do
        {
            System.out.print(mensaje);
            dato = scanner.nextLine();

            if (Excepciones.verificarEntero(dato))
            {
                num = Integer.parseInt(dato);
                flag = true;
            }
                else
                {
                    flag = false;
                }
        }
        while (flag == false);

        return num;
    }

    FormaPago(String detallePago)
    {
        this.detallePago = detallePago;
    }

    public String getFormaPago()
    {
        return this.detallePago;
    }

    public static FormaPago escogerFormaPago()
    {
        int seleccion;
        String seleccionAux = null;

        do
        {
            System.out.println("FORMAS DE PAGO DISPONIBLES:");

            for (FormaPago forma : FormaPago.values())
            {
                System.out.println((forma.ordinal() + 1) + ". " + forma.getFormaPago());
            }
            seleccion = castearEntero("SELECCIONE ALGUNA FORMA DE PAGO: ", seleccionAux);
        }
        while (seleccion < 1 || seleccion > 5);

        return FormaPago.values()[seleccion - 1];
    }
}