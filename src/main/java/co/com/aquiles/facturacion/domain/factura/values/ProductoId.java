package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ProductoId extends Identity {
    private ProductoId(String uid) {
        super(uid);
    }

    public ProductoId() {}

    public static ProductoId of(String uid) {
        return new ProductoId(uid);
    }
}
