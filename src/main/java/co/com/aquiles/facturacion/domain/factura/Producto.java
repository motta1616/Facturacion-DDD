package co.com.aquiles.facturacion.domain.factura;

import co.com.aquiles.facturacion.domain.factura.values.Cantidad;
import co.com.aquiles.facturacion.domain.factura.values.Precio;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;
import co.com.sofka.domain.generic.Entity;

public class Producto extends Entity<ProductoId> {
    private final Precio precio;
    private final Cantidad cantidad;

    public Producto(ProductoId entityId, Precio precio, Cantidad cantidad) {
        super(entityId);
        this.precio = precio;
        this.cantidad  = cantidad;
    }

    public Precio precio() {
        return precio;
    }

    public Cantidad cantidad() {
        return cantidad;
    }
}
