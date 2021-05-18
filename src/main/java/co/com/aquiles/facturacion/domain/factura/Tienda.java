package co.com.aquiles.facturacion.domain.factura;

import co.com.aquiles.facturacion.domain.factura.values.Descuento;
import co.com.aquiles.facturacion.domain.factura.values.TiendaId;
import co.com.sofka.domain.generic.Entity;

public class Tienda extends Entity<TiendaId> {
    private final Descuento descuento;

    public Tienda(TiendaId entityId, Descuento descuento) {
        super(entityId);
        this.descuento = descuento;
    }

    public Descuento descuento() {
        return descuento;
    }
}
