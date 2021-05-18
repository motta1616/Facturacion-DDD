package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class ClienteId extends Identity {

    private ClienteId(String uid) {
        super(uid);
    }

    public static ClienteId of(String uid) {
        return new ClienteId(uid);
    }
}
