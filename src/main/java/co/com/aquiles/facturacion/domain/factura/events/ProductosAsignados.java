package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.domain.generic.DomainEvent;



public class ProductosAsignados extends DomainEvent {
    private final FacturaId facturaId;
    private final ProductosFactory factory;

    public ProductosAsignados(FacturaId facturaId, ProductosFactory factory) {
        super("facturacion.facttura.ProductosAsignados");
        this.facturaId = facturaId;
        this.factory = factory;
    }

    public FacturaId facturaId() {
        return facturaId;
    }

    public ProductosFactory factory() {
        return factory;
    }
}
