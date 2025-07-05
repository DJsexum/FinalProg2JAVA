package Enumerados;

public enum FormaPago
{
    EFECTIVO,
    TARJETA_DEBITO,
    TARJETA_CREDITO,
    TRANSFERENCIA,
    CUENTA_CORRIENTE;

    public String getFormaPago()
    {
        return this.name();
    }
}