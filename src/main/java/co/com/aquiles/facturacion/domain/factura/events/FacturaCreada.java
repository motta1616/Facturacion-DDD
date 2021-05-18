package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.domain.generic.DomainEvent;

public class FacturaCreada extends DomainEvent {
    private final FacturaId facturaId;

    public FacturaCreada(FacturaId facturaId) {
        super("facturacion.factura.facturacreda");
        this.facturaId = facturaId;
    }

    public FacturaId facturaId() {
        return facturaId;
    }
}
