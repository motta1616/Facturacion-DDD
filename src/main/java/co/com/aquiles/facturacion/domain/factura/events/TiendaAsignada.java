package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.values.Descuento;
import co.com.aquiles.facturacion.domain.factura.values.TiendaId;
import co.com.sofka.domain.generic.DomainEvent;

public class TiendaAsignada extends DomainEvent {
    private final TiendaId tiendaId;
    private final Descuento descuento;

    public TiendaAsignada(TiendaId tiendaId, Descuento descuento) {
        super("facturacion.factura.tiendaasignada");
        this.tiendaId = tiendaId;
        this.descuento = descuento;
    }

    public TiendaId tiendaId() {
        return tiendaId;
    }

    public Descuento descuento() {
        return descuento;
    }
}
