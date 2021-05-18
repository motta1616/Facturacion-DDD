package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.Producto;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;

public class PagoCalculado extends DomainEvent {
    Map<ProductoId, Producto> productos;

    public PagoCalculado(Map<ProductoId, Producto> productos) {
        super("facturacion.factura.Pagocalculado");
        this.productos = productos;
    }

    public Map<ProductoId, Producto> getProductos() {
        return productos;
    }
}
