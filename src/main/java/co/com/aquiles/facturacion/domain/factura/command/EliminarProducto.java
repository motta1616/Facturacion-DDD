package co.com.aquiles.facturacion.domain.factura.command;

import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;
import co.com.sofka.domain.generic.Command;

public class EliminarProducto implements Command {
    private final FacturaId facturaId;
    private final ProductoId productoId;

    public EliminarProducto(FacturaId facturaId, ProductoId productoId) {
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
