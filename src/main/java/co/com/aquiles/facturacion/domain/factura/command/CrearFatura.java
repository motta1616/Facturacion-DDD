package co.com.aquiles.facturacion.domain.factura.command;

import co.com.aquiles.facturacion.domain.factura.values.Descuento;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.TiendaId;
import co.com.sofka.domain.generic.Command;

public class CrearFatura implements Command {
    private final FacturaId facturaId;
    private final TiendaId tiendaId;
    private final Descuento descuento;

    public CrearFatura(FacturaId facturaId, TiendaId tiendaId, Descuento descuento) {
        this.facturaId = facturaId;
        this.tiendaId = tiendaId;
        this.descuento = descuento;
    }

    public Descuento descuento() {
        return descuento;
    }

    public TiendaId TiendaId() {
        return tiendaId;
    }
}
