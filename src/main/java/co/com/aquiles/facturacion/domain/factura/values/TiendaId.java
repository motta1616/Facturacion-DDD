package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class TiendaId extends Identity {
    private TiendaId(String uid) {
        super(uid);
    }

    public TiendaId() {}

    public static TiendaId of(String uid) {
        return new TiendaId(uid);
    }
}
