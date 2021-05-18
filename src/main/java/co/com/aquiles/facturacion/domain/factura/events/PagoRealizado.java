package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.values.PrecioTotal;
import co.com.sofka.domain.generic.DomainEvent;

public class PagoRealizado extends DomainEvent {
    private final  Double descuento;
    private final PrecioTotal precioTotal;

    public PagoRealizado(Double descuento, PrecioTotal precioTotal) {
        super("facturacion.factura.pagorealizado");
        this.descuento = descuento;
        this.precioTotal = precioTotal;

    }

    public Double descuento() {
        return descuento;
    }

    public PrecioTotal precioTotal() {
        return precioTotal;
    }
}
