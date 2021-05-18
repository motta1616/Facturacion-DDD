package co.com.aquiles.facturacion.domain.factura.events;

import co.com.sofka.domain.generic.DomainEvent;

public class ProductosEnviado extends DomainEvent {

    public ProductosEnviado() {
        super("facturacion.factura.productoenviado");
    }
}
