package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;
import co.com.sofka.domain.generic.DomainEvent;

public class ProductoEliminado extends DomainEvent {
    private final FacturaId facturaId;
    private final ProductoId productoId;

    public ProductoEliminado(FacturaId facturaId, ProductoId productoId) {
        super("facturacion.factura.productoeliminado");
        this.facturaId = facturaId;
        this.productoId = productoId;
    }

    public FacturaId facturaId() {
        return facturaId;
    }

    public ProductoId productoId() {
        return productoId;
    }
}
